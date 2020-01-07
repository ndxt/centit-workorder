<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search" @reset="search">
      <zpa-text-input label="编码" v-model="params.catalogCode"/>
      <zpa-text-input label="名称" v-model="params.catalogName"/>
      <MenuTreeSelect label="业务模块" v-model="params.optId"/>
      <DictionarySelect label="类型" v-model="params.catalogStyle" code="CatalogStyle" />
      <DictionaryRadio label="结构" v-model="params.catalogType" code="CatalogType" />
    </SearchBox>
    <DataList
      ref="Table"
      index
      :columns="columns"
      :query="queryDictionary"
    >
      <template slot="fab">
        <AddDictionaryOperator @success="search"/>
      </template>
      <template slot-scope="props">
        <EditDictionaryOperator v-model="props.row" @success="search" />
        <RemoveDictionaryOperator v-model="props.row" @success="search" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import columns from './columns'
import AddDictionaryOperator from './operators/AddDictionaryOperator'
import RemoveDictionaryOperator from './operators/RemoveDictionaryOperator'
import EditDictionaryOperator from './operators/EditDictionaryOperator'

import {
  queryDictionary
} from '@/api/admin/dictionary'

export default {
  name: 'PageDictionary',

  data() {
    return {
      params: {},
      columns,
    }
  },

  props: {
    admin: Boolean,
  },

  provide() {
    return {
      top: this,
    }
  },

  components: {
    EditDictionaryOperator,
    AddDictionaryOperator,
    RemoveDictionaryOperator,
  },

  methods: {
    queryDictionary,
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
