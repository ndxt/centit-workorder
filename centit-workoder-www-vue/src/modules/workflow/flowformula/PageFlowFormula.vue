<template>
  <SearchLayout>
    <DataList
      index
      ref="Table"
      :columns="flowFormulaColumns"
      :query="queryFlowFormula"
    >
      <template slot="fab">
        <AddFlowFormulaOperator @success="search"/>
      </template>

      <template slot-scope="props">
        <EditFlowFormulaOperator v-model="props.row" @success="search"/>
        <RemoveFlowFormulaOperator v-model="props.row" @success="search"/>
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
  import {queryFlowFormula,} from '@/api/workflow/flowFormula'

  import flowFormulaColumns from './flowFormulaColumns'

  import AddFlowFormulaOperator from './operators/AddFlowFormulaOperator'
  import EditFlowFormulaOperator from './operators/EditFlowFormulaOperator'
  import RemoveFlowFormulaOperator from './operators/RemoveFlowFormulaOperator'

  export default {
  name: 'PageFlowFormula',

  components: {
    AddFlowFormulaOperator,
    EditFlowFormulaOperator,
    RemoveFlowFormulaOperator,
  },

  data() {
    return {
      flowFormulaColumns,
    }
  },

  methods: {
    queryFlowFormula,

    search() {
      return this.$refs['Table'].load()
    },
  },

  mounted() {
    this.search()
  },
}
</script>
