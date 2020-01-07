<template>
  <SearchLayout>

    <SearchBox slot="search" @search="reload">
      <zpa-text-input label="业务名称" v-model="params.flowOptName"/>
    </SearchBox>

    <DataList

      ref="Table"
      index
      :operWidth="170"
      :columns="transactColumns"
      :query="queryTransact"
    >
      <!--<template slot="createTime" slot-scope="{value}">
        {{value | day}}
      </template>-->
      <template slot-scope="props">
        <!--<component v-bind:is="props.row.optUrl" v-model="props.row" @success="reload"/>-->
        <EditTransactHandleOperator v-model="props.row" @success="reload"/>
        <button class="viewFlow" type="button" @click="viewInfo(props.row)">查看</button>
        <button class="viewFlow" type="button" @click="viewFlow(props.row)">查看流程图</button>
      </template>
    </DataList>

  </SearchLayout>
</template>

<script>
import transactColumns from './columns/transactColumns'
import EditTransactHandleOperator from './operators/EditTransactHandleOperator'
import PureFlowInstView from './operators/PureFlowInstView'

import {
  queryTransact,
} from '@/api/task/transact'
import {
  openlink,
  openInfo,
} from '@/api/workflow/flowManager'

export default {
  name: 'PageTransact',

  data() {
    return {
      transactColumns,

      params: {},

    }
  },

  components: {
    EditTransactHandleOperator,
    PureFlowInstView,
  },

  methods: {
    queryTransact,

    reload() {
      const params = this.params

      this.$refs['Table'].load({params})
    },

    viewFlow(data) {
      openlink(data.flowInstId)
    },

    viewInfo(data) {
      openInfo(data.flowInstId)
    },

  },

  mounted() {
    this.$nextTick(() => {
      this.reload()
    })
  },
}
</script>

<style lang="less">
  .viewFlow {
    border: 1px solid #2db7f5;
    color: #2db7f5;
    padding: 2px 5px;
    background: rgba(0, 0, 0, 0);
    border-radius: 3px;
    cursor: pointer;
    vertical-align: middle;
  }
</style>
