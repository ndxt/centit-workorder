<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search" @reset="reset">
      <UserSelect
        :filterable="true"
        label="操作用户"
        v-model="params.userCode"
        />
      <zpa-text-input label="操作模块" v-model="params.optId"/>
      <zpa-text-input label="操作方法" v-model="params.optMethod"/>
      <zpa-date-range-input
        label="操作时间"
        v-model="params"
        start-field="optTimeBegin"
        end-field="optTimeEnd"
      />
      <DictionaryRadio label="日志级别" v-model="params.logLevel" code="LogLevel"/>
    </SearchBox>
    <DataList
      ref="Table"
      index
      :columns="columns"
      :query="queryLog"
      :oper-width="100"
    >
      <template slot-scope="{row}">
        <ViewLogOperator :value="row" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
  import {queryLog} from '@/api/admin/log'
  import ViewLogOperator from './ViewLogOperator'
  import columns from './columns'

  export default {
  name: 'PageLog',

  components: {
    ViewLogOperator,
  },

  data() {
    return {
      params: {},
      columns,
    }
  },

  mounted() {
    this.search()
  },

  methods: {
    queryLog,
    reset() {
      this.params = {}
      this.search()
    },
    search() {
      const params = this.params
      return this.$refs['Table'].load({params})
    },
  }
}
</script>
