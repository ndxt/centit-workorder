<template>
  <DataList
    index
    checkbox
    ref="Table"
    :operWidth="150"
    :columns="flowTaskTransferColumns"
    :query="queryFlowTask"
    @onSelectionChange="getChecked"
  >
    <template slot="fab">
      <zpa-row style="flex: none; height: 46px;">
        <UserFlowTaskTransferOperator
          v-model="value"
          :type="'all'"
          @success="reload"
        />
        <UserFlowTaskTransferOperator
          v-model="value"
          :type="'batch'"
          :checkedList="checkedList"
          @success="reload"
          style="margin-left: 8px;"
        />
      </zpa-row>
    </template>

    <template slot-scope="props">
      <TransactFlowTaskTransferOperator v-model="props.row" @success="reload"/>
      <UserFlowTaskTransferOperator
        v-model="props.row"
        :type="'single'"
        @success="reload"
      />
    </template>
  </DataList>
</template>

<script>
import {
  queryFlowTask,
} from '@/api/workflow/flowTask'

import flowTaskTransferColumns from './flowTaskTransferColumns'

import TransactFlowTaskTransferOperator from './operators/transfer/TransactFlowTaskTransferOperator'
import UserFlowTaskTransferOperator from './operators/transfer/UserFlowTaskTransferOperator'

export default {
  name: 'FlowTaskTransferList',

  components: {
    TransactFlowTaskTransferOperator,
    UserFlowTaskTransferOperator,
  },

  data() {
    return {
      flowTaskTransferColumns,

      checkedList: [],
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryFlowTask,

    reload() {
      const params = this.value

      this.$refs.Table.load({params})
    },

    getChecked(row) {
      this.checkedList = row
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
