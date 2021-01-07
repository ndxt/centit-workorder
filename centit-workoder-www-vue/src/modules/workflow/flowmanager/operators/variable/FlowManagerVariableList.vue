<template>
  <DataList
    index
    ref="Table"
    :operWidth="80"
    :columns="flowManagerVariableColumns"
    :query="queryFlowManagerVariable"
  >
    <template slot="fab">
      <AddFlowManagerVariableOperator v-model="value" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
  import {queryFlowManagerVariable,} from '@/api/workflow/flowManager'

  import flowManagerVariableColumns from './flowManagerVariableColumns'

  import AddFlowManagerVariableOperator from './AddFlowManagerVariableOperator'

  export default {
  name: 'FlowManagerVariableList',

  components: {
    AddFlowManagerVariableOperator,
  },

  data() {
    return {
      flowManagerVariableColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryFlowManagerVariable,

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
