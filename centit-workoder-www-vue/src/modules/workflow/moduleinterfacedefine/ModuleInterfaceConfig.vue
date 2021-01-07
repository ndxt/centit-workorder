<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="业务名称" v-model="params.optName"/>
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="200"
      :columns="moduleInterfaceDefineColumns"
      :query="queryModuleInterFaceConfigs"
    >
      <template slot="updateDate" slot-scope="{value}">
        {{value | day}}
      </template>

      <template slot="fab">
        <AddModuleInterfaceConfig @success="search" />
      </template>

      <template slot-scope="props">
        <EditModuleInterfaceConfig v-model="props.row" @success="search" />
        <ModuleInterfaceViewList v-model="props.row" @success="search"/>
        <RemoveModuleInterfaceConfig v-model="props.row" @success="search" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
  import {queryModuleInterFaceConfigs,} from '@/api/workflow/flowBusiness'

  import moduleInterfaceDefineColumns from './moduleInterfaceDefineColumns'

  import AddModuleInterfaceConfig from './operators/AddModuleInterfaceConfig'
  import EditModuleInterfaceConfig from './operators/EditModuleInterfaceConfig'
  import ModuleInterfaceViewList from './operators/viewflowoperatordefineoperator/ModuleInterfaceViewList'
  import RemoveModuleInterfaceConfig from './operators/RemoveModuleInterfaceConfig'

  export default {
  name: 'ModuleInterfaceConfig',

  data() {
    return {
      moduleInterfaceDefineColumns,

      params: {},
    }
  },

  components: {
    AddModuleInterfaceConfig,
    EditModuleInterfaceConfig,
    ModuleInterfaceViewList,
    RemoveModuleInterfaceConfig,
  },

  methods: {
    queryModuleInterFaceConfigs,

    search() {
      const params = this.params

      return this.$refs['Table'].load({params})
    },
  },

  mounted() {
    this.search()
  },
}
</script>
