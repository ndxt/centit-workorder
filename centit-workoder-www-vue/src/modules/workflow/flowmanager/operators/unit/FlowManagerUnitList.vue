<template>
  <DataList
    index
    ref="Table"
    :operWidth="80"
    :columns="flowManagerUnitColumns"
    :query="queryFlowManagerUnit"
  >
    <template slot="fab">
      <AddFlowManagerUnitOperator v-model="value" @success="reload"/>
    </template>

    <template slot-scope="props">
      <RemoveFlowManagerUnitOperator v-model="props.row" :flowInstId="value.flowInstId" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
import {
  queryFlowManagerUnit,
} from '@/api/workflow/flowManager'

import flowManagerUnitColumns from './flowManagerUnitColumns'

import AddFlowManagerUnitOperator from './AddFlowManagerUnitOperator'
import RemoveFlowManagerUnitOperator from './RemoveFlowManagerUnitOperator'

export default {
  name: 'FlowManagerUnitList',

  components: {
    AddFlowManagerUnitOperator,
    RemoveFlowManagerUnitOperator,
  },

  data() {
    return {
      flowManagerUnitColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryFlowManagerUnit,

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
