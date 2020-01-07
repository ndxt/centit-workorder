<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="表单id" v-model="params.optFormId"/>
    </SearchBox>

    <DataList
      index
      ref="Table"
      :columns="approvalBusinessColumns"
      :query="queryApprovalBusiness"
    >
      <template slot="fab">
        <AddApprovalBusinessOperator @success="search" />
      </template>

      <template slot-scope="props">
        <EditApprovalBusinessOperator v-model="props.row" @success="search" />
        <RemoveApprovalBusinessOperator v-model="props.row" @success="search" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import {
  queryApprovalBusiness,
} from '@/api/workflow/flowApprovalBusiness'

import approvalBusinessColumns from './approvalBusinessColumns'

import AddApprovalBusinessOperator from './operators/AddApprovalBusinessOperator'
import EditApprovalBusinessOperator from './operators/EditApprovalBusinessOperator'
import RemoveApprovalBusinessOperator from './operators/RemoveApprovalBusinessOperator'

export default {
  name: 'PageApprovalBusiness',

  data() {
    return {
      approvalBusinessColumns,

      params: {},
    }
  },

  components: {
    AddApprovalBusinessOperator,
    EditApprovalBusinessOperator,
    RemoveApprovalBusinessOperator,
  },

  methods: {
    queryApprovalBusiness,

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
