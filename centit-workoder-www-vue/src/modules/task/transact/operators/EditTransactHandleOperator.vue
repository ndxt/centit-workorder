<template>
  <ModalOperator ref="ModalOperator" text="办理" title="流程办理" width="auto" :className="size" :styles="modalStyles" :mask="false" :draggable="false" customButton>

    <TransactFlowChart
      id="Flow"
      ref="Flow"
      v-model="value"
    />

    <TabList :tabData="tabs" :height="height" @click="getParentHeight">
      <TransactCensorLog
        slot="log1"
        ref="TransactLog"
        v-model="value"
        @setParentHeight="setParentHeight"
      />
      <AttentionMaterialList
        slot="log2"
        ref="AttentionLog"
        v-model="value"
        @setParentHeight="setParentHeight"
      />
    </TabList>

    <TransactCensor
      ref="TransactCensor"
      v-if="sp.length"
      v-model="value"
      :ideaCodes="ideaCodes"
    />

    <TransactFileUpload
      v-if="hasStuff==='Y'"
      v-model="value"/>

    <div slot="customBtn">
      <Button type="primary" @click="submit">提交</Button>
      <Button type="primary" @click="save">保存</Button>
      <Button type="text" @click="close">取消</Button>
    </div>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import TransactFlowChart from '../model/TransactFlowChart'
  import TransactCensorLog from '../model/TransactCensorLog'
  import TransactCensor from '../model/TransactCensor'
  import TransactFileUpload from '../model/TransactFileUpload'
  import AttentionMaterialList from '@/modules/task/attention/model/AttentionMaterialList'

  import {mapState} from 'vuex'
  import {businessFormSubmit, queryTransactInit, queryTransactSave, queryTransactSubmit,} from '@/api/task/transact'

  export default {
  name: 'EditTransactHandleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    value: Object,
  },

  data() {
    return {
      tabs: [
        {
          name: '审批记录',
          component: 'log1',
        },
        {
          name: '材料列表',
          component: 'log2',
        },
      ],
      height: 'auto',
      ideaCodes: [],
      hasStuff: [],
      sp: []
    }
  },

  components: {
    TransactFlowChart,
    TransactCensorLog,
    TransactCensor,
    TransactFileUpload,
    AttentionMaterialList,
  },

  computed: {
    ...mapState({
      size: 'size',
    }),

    modalStyles() {
      return {
        position: 'absolute',
        top: 0,
        right: 0,
        bottom: 0,
        left: 0,
      }
    },
  },

  methods: {
    getUrlParam(href, name) {
      href = href.substring(href.indexOf('?'))

      const reg = new RegExp(`(^|&)${name}=([^&]*)(&|$)`)
      const r = href.substr(1).match(reg)

      if (r !== null) {
        return unescape(r[2])
      }

      return ''
    },

    async beforeOpen() {
      const {
        flowInstId,
        nodeInstId,
        flowOptTag,
        nodeOptUrl,
      } = this.value

      const params = {
        flowInstId,
        nodeInstId,
        flowOptTag,
        ideaCode: 'T',
        moduleCode: this.getUrlParam(nodeOptUrl, 'moduleCode'),
      }
      const res = await queryTransactInit(params)
      this.hasStuff = res.moduleParam.hasStuff
      this.sp = 'sp'
      this.value.initData = res
      this.value.config = {
        fileUploadId: '',
        fileUploadName: '',
        flowInstId: res.flowInstId,
        formId: res.formId,
        moduleCode: res.moduleParam.moduleCode,
        nodeInstId: res.nodeInstId,
      }
      if (res.proc) {
        this.value.config.ideaCode = res.proc.ideaCode || 'T'
        this.value.config.transContent = res.proc.transContent
      }
      this.ideaCodes = res.ideaCatalogData.map(m => {
        return {
          value: m.dataCode,
          text: m.dataValue,
        }
      })
      this.$refs['Flow'].reload()
      this.$refs['TransactLog'].reload()
      this.$refs['AttentionLog'].reload()
    },

    beforeSubmit() {
      return this.$refs['TransactCensor'].$refs['Form'].validate()
    },

    async submit() {
      const vm = this
      const result = await vm.beforeSubmit()

      if (!result) {
        return false
      }

      vm.$Modal.confirm({
        title: '提交',
        content: `确认提交？`,
        onOk() {
          if (vm.value.isSubmit === 'Y' && vm.value.subUrl !== '') {
            const optFormData = vm.$refs['Flow'].$refs['iFrame'].contentWindow.getData()
            businessFormSubmit(vm.value.subUrl, optFormData);
          }
          queryTransactSubmit(vm.value.config)
            .then(() => {
              vm.$refs['ModalOperator'].close()
              vm.$Message.success('提交成功')
            }).catch(({message}) => {
              vm.$refs['ModalOperator'].close()
              vm.$Message.error(message)
            })
        },
      })
    },

    async save() {
      const result = await this.beforeSubmit()

      if (!result) {
        return false
      }

      if (this.value.isSubmit === 'Y' && this.value.subUrl !== '') {
        const optFormData = this.$refs['Flow'].$refs['iFrame'].contentWindow.getData()
        await businessFormSubmit(this.value.subUrl, optFormData);
      }
      await queryTransactSave(this.value.config)
      this.$refs['ModalOperator'].close()
    },

    close() {
      this.$refs['ModalOperator'].close()
    },

    getParentHeight(index) {
      if (index === 0) {
        this.$refs['TransactLog'].setHeight()
      } else {
        this.$refs['AttentionLog'].setHeight()
      }
    },

    setParentHeight(data) {
      this.height = `${data}`
    },
  },

  mounted() {
    this.value.initData = {}
    this.value.config = {}
  },
}
</script>
