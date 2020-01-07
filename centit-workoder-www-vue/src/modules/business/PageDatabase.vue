<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="数据库名称" v-model="params.databaseName"/>
    </SearchBox>

    <DataList
      index
      ref="Table"
      :columns="databaseColumns"
      :query="queryDatabase"
    >
      <template slot="fab">
        <AddDatabaseOperator @success="search"/>
      </template>

      <template slot-scope="props">
        <EditDatabaseOperator v-model="props.row" @success="search"/>
        <RemoveDatabaseOperator v-model="props.row" @success="search"/>
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import {
  queryDatabase,
} from '@/api/admin/business'

import databaseColumns from './columns/databaseColumns'

import AddDatabaseOperator from './operators/AddDatabaseOperator'
import EditDatabaseOperator from './operators/EditDatabaseOperator'
import RemoveDatabaseOperator from './operators/RemoveDatabaseOperator'

export default {
  name: 'PageDatabase',

  components: {
    EditDatabaseOperator,
    AddDatabaseOperator,
    RemoveDatabaseOperator,
  },

  provide() {
    return {
      top: this,
    }
  },

  data() {
    return {
      databaseColumns,

      params: {},
    }
  },

  props: {
    admin: Boolean,
  },

  methods: {
    queryDatabase,

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
