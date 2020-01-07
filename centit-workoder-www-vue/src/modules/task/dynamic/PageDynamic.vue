<template>
  <SearchLayout>

    <SearchBox slot="search" @search="reload">
      <zpa-text-input label="业务名称" v-model="params.flowOptName"/>
    </SearchBox>

    <DataList
      ref="Table"
      index
      :operWidth="80"
      :columns="dynamicColumns"
      :query="queryDynamic"
    >
      <template slot="lastUpdateTime" slot-scope="{value}">
        {{value | timeType}}
      </template>
      <template slot-scope="props">
        <EditDynamicHandleOperator v-model="props.row" @success="reload"/>
      </template>
    </DataList>

  </SearchLayout>
</template>

<script>
import dynamicColumns from './columns/dynamicColumns'

import EditDynamicHandleOperator from './operators/EditDynamicHandleOperator'

import {
  queryDynamic,
} from '@/api/task/dynamic'

export default {
  name: 'PageDynamic',

  data() {
    return {
      dynamicColumns,

      params: {},

    }
  },

  components: {
    EditDynamicHandleOperator,
  },

  methods: {
    queryDynamic,

    reload() {
      const params = this.params
      this.$refs['Table'].load({params})
    },
  },

  mounted() {
    this.$nextTick(() => {
      this.reload()
    })
  },
}
</script>
