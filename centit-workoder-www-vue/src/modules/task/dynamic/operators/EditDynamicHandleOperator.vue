<template>
  <ModalOperator text="办理" title="流程办理" :className="size" width :styles="modalStyles" :mask="false" :draggable="false">

    <DynamicFlowChart
      ref="Flow"
      v-model="value"
    />

    <DynamicCensorLog
      ref="Log"
      v-model="value"
    />

    <DynamicCensor
      v-if="ideaCode.length"
      v-model="value"
      :ideaCode="ideaCode"
    />

  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import DynamicFlowChart from '../model/DynamicFlowChart'
  import DynamicCensorLog from '../model/DynamicCensorLog'
  import DynamicCensor from '../model/DynamicCensor'

  import {mapState} from 'vuex'
  import {queryDynamicInit, queryDynamicSubmit,} from '@/api/task/dynamic'

  export default {
  name: 'EditDynamicHandleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    value: Object,
  },

  data() {
    return {
      ideaCode: [],
    }
  },

  components: {
    DynamicFlowChart,
    DynamicCensorLog,
    DynamicCensor,
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

      const res = await queryDynamicInit(params)

      this.value.initData = res
      this.value.config = {
        userCode: 'U0000019',
        fileUploadId: '',
        fileUploadName: '',
        flowInstId: res.flowInstId,
        formId: res.flowInstId,
        ideaCode: 'T',
        moduleCode: res.moduleParam.moduleCode,
        nodeInstId: res.nodeInstId,
      }
      this.ideaCode = res.ideaCatalogData.map(m => {
        return {
          value: m.dataCode,
          text: m.dataValue,
        }
      })

      this.$refs['Flow'].reload()
      this.$refs['Log'].reload()
    },

    onSubmit() {
      return queryDynamicSubmit(this.value.config)
    },
  },

  mounted() {
    this.value.initData = {}
    this.value.config = {}
  },
}
</script>
