<template>
  <DataList
    ref="Table"
    :height="300"
    :columns="attentionHandleLogColumns"
    :query="queryAttentionHandleLog"
  >
    <template slot="transDate" slot-scope="{value}">
      {{value | timeType}}
    </template>
  </DataList>
</template>

<script>
import attentionHandleLogColumns from '../columns/attentionHandleLogColumns'

import {
  queryAttentionHandleLog,
} from '@/api/task/attention'

export default {
  name: 'AttentionHandleLogList',

  props: {
    value: Object,
  },

  data() {
    return {
      attentionHandleLogColumns,
    }
  },

  methods: {
    queryAttentionHandleLog,

    reload() {
      const params = {
        flowInstId: this.value.flowInstId,
      }

      this.$refs['Table'].load({params})
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
