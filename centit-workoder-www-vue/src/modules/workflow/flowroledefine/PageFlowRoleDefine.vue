<template>
  <SearchLayout>

    <SearchBox slot="search" :params="params" @search="search">
      <zpa-text-input label="角色名称" v-model="params.roleName"/>
    </SearchBox>

    <DataList
      ref="Table"
      index
      :columns="columns"
      :query="queryFlowRoleList"
      @on-selected-change="SelectedChange"
    >
      <template slot="updateDate" slot-scope="{value}">
        {{value | timeType}}
      </template>
      <template slot="fab">
        <zpa-row height="auto" class="top-btns">
          <AddFlowRoleOperator @success="search" />
          <EditFlowRoleOperator v-model="SelectRowData" @success="search" />
          <FlowRoleDefineViewList v-model="SelectRowData" @success="search"/>
          <RemoveFlowRoleOperator v-model="SelectRowData" @success="search" />
          <SyncFlowRoleOperator />
        </zpa-row>
      </template>

    </DataList>

  </SearchLayout>
</template>
<script>
  import columns from './flowRoleDefineColumns'
  import AddFlowRoleOperator from './operators/AddFlowRoleOperator'
  import EditFlowRoleOperator from './operators/EditFlowRoleOperator'
  import RemoveFlowRoleOperator from './operators/RemoveFlowRoleOperator'
  import SyncFlowRoleOperator from './operators/SyncFlowRoleOperator'
  import FlowRoleDefineViewList from './operators/viewflowroledefineoperator/FlowRoleDefineViewList'
  import {queryFlowRoleList,} from '@/api/workflow/flowroledefine'

  export default {
  name: 'PageFlowRoleDefine',
  data() {
    return {
      columns,
      params: {},
      SelectRowData: {},
    }
  },
  components: {
    AddFlowRoleOperator,
    EditFlowRoleOperator,
    SyncFlowRoleOperator,
    FlowRoleDefineViewList,
    RemoveFlowRoleOperator,
  },
  methods: {
    queryFlowRoleList,
    search() {
      const params = this.params
      return this.$refs['Table'].load({params})
    },
    SelectedChange(data) {
      this.SelectRowData = data
    },
  },
  mounted() {
    this.search()
  },

}
</script>
