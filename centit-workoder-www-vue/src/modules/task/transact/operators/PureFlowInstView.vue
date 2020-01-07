<template>
  <Box style="height: 100vh; overflow: auto;">
    <FlowFrame
      ref="Flow"
      v-model="value"
    />

    <TabList noFlex :tabData="tabs" :height="height" @click="getParentHeight">
      <TransactCensorLog
        slot="log1"
        ref="Log"
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
  </Box>
</template>

<script>
import FlowFrame from '../model/FlowFrame'
import TransactCensorLog from '../model/TransactCensorLog'
import AttentionMaterialList from '@/modules/task/attention/model/AttentionMaterialList'

import {
  mapState
} from 'vuex'
import {
  queryFlowView,
} from '@/api/task/transact'

export default {
  name: 'PureFlowInstView',

  data() {
    return {
      value: {},
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
    }
  },

  components: {
    TransactCensorLog,
    FlowFrame,
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
    async beforeOpen() {
      const {
        flowInstId,
      } = this.value

      // const res = await queryFlowView(flowInstId)

      console.log(flowInstId)
      console.log(this.value.flowInstId)
      // this.value.initData = res
      this.$nextTick(() => {
        this.$refs['Flow'].reload()
        this.$refs['Log'].reload()
        this.$refs['AttentionLog'].reload()
      })
    },

    getParentHeight(index) {
      if (index === 0) {
        this.$refs['Log'].setHeight()
      } else {
        this.$refs['AttentionLog'].setHeight()
      }
    },

    setParentHeight(data) {
      this.height = `${data}`
    },
  },

  async mounted() {
    const {
      id,
    } = this.$route.params

    if (id) {
      await queryFlowView(
        id
      ).then(res => {
        if (res) {
          this.value = res
          this.value.flowInstId = id
          this.beforeOpen()
        }
      }).catch(({message}) => {
        this.$Message.error(message)
      })
    }
  },
}
</script>
