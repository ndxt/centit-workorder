<template>
  <DataList
    index
    ref="Table"
    :columns="flowManagerLogColumns"
    :query="queryFlowManagerLog"
  ></DataList>
</template>

<script>
import {
  queryFlowManagerLog,
} from '@/api/workflow/flowManager'

import flowManagerLogColumns from './flowManagerLogColumns'

export default {
  name: 'FlowManagerLogList',

  data() {
    return {
      flowManagerLogColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryFlowManagerLog,

    reload() {
      const params = {
        flowInstId: this.value.flowInstId
      }

      this.$refs.Table.load({params})
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
