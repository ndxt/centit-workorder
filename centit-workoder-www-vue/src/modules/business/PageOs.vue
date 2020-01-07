<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search" @reset="search" :labWidth="70">
      <zpa-text-input label="业务系统名称" v-model="params.osName"/>
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="170"
      :columns="osColumns"
      :query="queryOs"
    >
      <template slot="fab">
        <zpa-row no-gutter style="flex: none;">
          <AddOsOperator @success="search"/>
          <AllRefreshOsOperator @success="search" style="height: 30px; margin: 8px;"/>
        </zpa-row>
      </template>

      <template slot-scope="props">
        <EditOsOperator v-model="props.row" @success="search"/>
        <SingleRefreshOsOperator v-model="props.row" @success="search"/>
        <RemoveOsOperator v-model="props.row" @success="search"/>
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import {
  queryOs,
} from '@/api/admin/business'

import osColumns from './columns/osColumns'

import AddOsOperator from './operators/AddOsOperator'
import AllRefreshOsOperator from './operators/AllRefreshOsOperator'
import EditOsOperator from './operators/EditOsOperator'
import SingleRefreshOsOperator from './operators/SingleRefreshOsOperator'
import RemoveOsOperator from './operators/RemoveOsOperator'

export default {
  name: 'PageOs',

  components: {
    AddOsOperator,
    AllRefreshOsOperator,
    EditOsOperator,
    SingleRefreshOsOperator,
    RemoveOsOperator,
  },

  provide() {
    return {
      top: this,
    }
  },

  data() {
    return {
      osColumns,

      params: {},
    }
  },

  props: {
    admin: Boolean,
  },

  methods: {
    queryOs,

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
