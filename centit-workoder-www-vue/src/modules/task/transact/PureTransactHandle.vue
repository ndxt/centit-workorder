<template>
  <!--<ModalOperator text="办理" title="流程办理" :className="size" width :styles="modalStyles" :mask="false" :draggable="false">-->
  <Box style="height: 100vh; overflow: auto;">
    <TransactFlowChart
      ref="Flow"
      v-model="value"
    />

    <TransactCensorLog
      ref="Log"
      v-model="value"
    />

    <TransactCensor
      v-if="sp.length"
      v-model="value"
      :ideaCodes="ideaCodes"
    />

    <div style="margin: 20px auto;">
      <Button type="primary" :loading="resolving" @click="submit">提交</Button>
      <Button type="primary" :loading="resolving" @click="save" style="margin-left: 10px;">保存</Button>
      <!--<Button type="text" @click="close">取消</Button>-->
    </div>
  </Box>
  <!--</ModalOperator>-->
</template>

<script>
// import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import TransactFlowChart from './model/TransactFlowChart'
import TransactCensorLog from './model/TransactCensorLog'
import TransactCensor from './model/TransactCensor'

import {
  mapState
} from 'vuex'
import {
  getTransact,
  queryTransactInit,
  queryTransactSubmit,
  queryTransactSave,
} from '@/api/task/transact'

export default {
  name: 'PureTransactHandle',

  // mixins: [
  //   ModalOperatorMixin,
  // ],

  // props: {
  //   value: Object,
  // },

  data() {
    return {
      value: {
        initData: {},
        config: {},
      },
      ideaCodes: [],
      resolving: false,
      sp: [],
    }
  },

  components: {
    TransactFlowChart,
    TransactCensorLog,
    TransactCensor,
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
        userCode,
      } = this.value

      const params = {
        flowInstId,
        nodeInstId,
        flowOptTag,
        userCode,
        ideaCode: 'T',
        moduleCode: this.getUrlParam(nodeOptUrl, 'moduleCode'),
      }
      const res = await queryTransactInit(params)

      this.sp = 'sp'
      this.value.initData = res
      this.value.config = {
        fileUploadId: '',
        fileUploadName: '',
        flowInstId: res.flowInstId,
        formId: res.formId,
        ideaCode: 'T',
        moduleCode: res.moduleParam.moduleCode,
        nodeInstId: res.nodeInstId,
        userCode: this.value.userCode,
        unitCode: this.value.unitCode,
      }
      this.ideaCodes = res.ideaCatalogData.map(m => {
        return {
          value: m.dataCode,
          text: m.dataValue,
        }
      })

      this.$refs['Flow'].reload()
      this.$refs['Log'].reload()
    },

    async submit() {
      await queryTransactSubmit(this.value.config)
      window.location.href = '/#/task/flowView/' + this.value.flowInstId;
      // close()
    },

    async save() {
      await queryTransactSave(this.value.config)
      alert('保存成功');
      // this.$refs['ModalOperator'].close()
    },

    // close() {
    //   this.$refs['ModalOperator'].close()
    // },

    // onSubmit() {
    //   return queryTransactSubmit(this.value.config)
    // },
  },

  async mounted() {
    const {
      id, userCode
    } = this.$route.params
    if (id) {
      await getTransact({
        s_nodeInstId: id,
        s_userCode: userCode,
      }).then(res => {
        if (res) {
          this.value = res.objList[0]
          this.beforeOpen()
        }
      }).catch(({message}) => {
        this.$Message.error(message)
      })
    }
  },
}
</script>
