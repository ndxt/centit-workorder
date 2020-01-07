<template>
  <DataList
    index
    ref="Table"
    :operWidth="80"
    :columns="flowDefineStageColumns"
    :query="query"
  >
    <template slot="fab">
      <AddFlowDefineStageOperator v-model="value" @success="reload"/>
    </template>

    <template slot-scope="props">
      <RemoveFlowDefineStageOperator v-model="props.row" :obj="value" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
import flowDefineStageColumns from './flowDefineStageColumns'

import AddFlowDefineStageOperator from './AddFlowDefineStageOperator'
import RemoveFlowDefineStageOperator from './RemoveFlowDefineStageOperator'

export default {
  name: 'FlowDefineStageList',

  components: {
    AddFlowDefineStageOperator,
    RemoveFlowDefineStageOperator,
  },

  data() {
    return {
      flowDefineStageColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    reload() {
      return this.$refs.Table.load()
    },

    query() {
      return this.value.flowStages || []
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
