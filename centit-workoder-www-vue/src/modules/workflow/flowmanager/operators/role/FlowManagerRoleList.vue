<template>
  <DataList
    index
    ref="Table"
    :operWidth="80"
    :columns="flowManagerRoleColumns"
    :query="queryFlowManagerRole"
  >
    <template slot="fab">
      <AddFlowManagerRoleOperator v-model="value" @success="reload"/>
    </template>

    <template slot-scope="props">
      <RemoveFlowManagerRoleOperator v-model="props.row" :flowInstId="value.flowInstId" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
  import {queryFlowManagerRole,} from '@/api/workflow/flowManager'

  import flowManagerRoleColumns from './flowManagerRoleColumns'

  import AddFlowManagerRoleOperator from './AddFlowManagerRoleOperator'
  import RemoveFlowManagerRoleOperator from './RemoveFlowManagerRoleOperator'

  export default {
  name: 'FlowManagerRoleList',

  components: {
    AddFlowManagerRoleOperator,
    RemoveFlowManagerRoleOperator,
  },

  data() {
    return {
      flowManagerRoleColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryFlowManagerRole,

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
