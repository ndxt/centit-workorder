<template>
  <DataList
    index
    ref="Table"
    :operWidth="80"
    :columns="flowDefineRoleColumns"
    :query="query"
  >
    <template slot="fab">
      <AddFlowDefineRoleOperator v-model="value" @success="reload"/>
    </template>

    <template slot-scope="props">
      <RemoveFlowDefineRoleOperator v-model="props.row" :obj="value" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
import flowDefineRoleColumns from './flowDefineRoleColumns'

import AddFlowDefineRoleOperator from './AddFlowDefineRoleOperator'
import RemoveFlowDefineRoleOperator from './RemoveFlowDefineRoleOperator'

export default {
  name: 'FlowDefineRoleList',

  components: {
    AddFlowDefineRoleOperator,
    RemoveFlowDefineRoleOperator,
  },

  data() {
    return {
      flowDefineRoleColumns,
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
      return this.value.flowTeamRoles || []
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
