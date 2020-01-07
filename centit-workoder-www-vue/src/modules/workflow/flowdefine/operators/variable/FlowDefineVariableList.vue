<template>
  <DataList
    index
    ref="Table"
    :operWidth="80"
    :columns="flowDefineVariableColumns"
    :query="query"
  >
    <template slot="fab">
      <AddFlowDefineVariableOperator v-model="value" @success="reload"/>
    </template>

    <template slot-scope="props">
      <RemoveFlowDefineVariableOperator v-model="props.row" :obj="value" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
import flowDefineVariableColumns from './flowDefineVariableColumns'

import AddFlowDefineVariableOperator from './AddFlowDefineVariableOperator'
import RemoveFlowDefineVariableOperator from './RemoveFlowDefineVariableOperator'

export default {
  name: 'FlowDefineVariableList',

  components: {
    AddFlowDefineVariableOperator,
    RemoveFlowDefineVariableOperator,
  },

  data() {
    return {
      flowDefineVariableColumns,
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
      return this.value.flowVariableDefines || []
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
