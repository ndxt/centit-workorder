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

  </Box>
  <!--</ModalOperator>-->
</template>

<script>
// import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import TransactFlowChart from './model/TransactFlowChart'
import TransactCensorLog from './model/TransactCensorLog'

import {
  mapState
} from 'vuex'
import {
  getTransact,
  queryTransactInit,
} from '@/api/task/transact'

export default {
  name: 'PureTransactView',

  data() {
    return {
      value: {
        initData: {},
        config: {},
      },
      ideaCode: [],
    }
  },

  components: {
    TransactFlowChart,
    TransactCensorLog,
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

      this.value.initData = res
      this.value.config = {
        fileUploadId: '',
        fileUploadName: '',
        flowInstId: res.flowInstId,
        formId: res.formId,
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

  },

  async mounted() {
    const {
      id,
    } = this.$route.params

    if (id) {
      await getTransact({
        s_nodeInstId: id,
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
