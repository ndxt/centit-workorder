<template>
  <DataList
    index
    ref="Table"
    :columns="flowTaskEntrustColumns"
    :query="queryFlowTaskEntrust"
  >
    <template slot="fab">
      <AddFlowTaskEntrustOperator v-model="value" @success="reload"/>
    </template>

    <template slot-scope="props">
      <EditFlowTaskEntrustOperator v-model="props.row" @success="reload"/>
      <RemoveFlowTaskEntrustOperator v-model="props.row" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
import {
  queryFlowTaskEntrust,
} from '@/api/workflow/flowTask'

import flowTaskEntrustColumns from './flowTaskEntrustColumns'

import AddFlowTaskEntrustOperator from './operators/entrust/AddFlowTaskEntrustOperator'
import EditFlowTaskEntrustOperator from './operators/entrust/EditFlowTaskEntrustOperator'
import RemoveFlowTaskEntrustOperator from './operators/entrust/RemoveFlowTaskEntrustOperator'

export default {
  name: 'FlowTaskEntrustList',

  components: {
    AddFlowTaskEntrustOperator,
    EditFlowTaskEntrustOperator,
    RemoveFlowTaskEntrustOperator,
  },

  data() {
    return {
      flowTaskEntrustColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryFlowTaskEntrust,

    reload() {
      const params = this.value

      this.$refs.Table.load({params})
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
