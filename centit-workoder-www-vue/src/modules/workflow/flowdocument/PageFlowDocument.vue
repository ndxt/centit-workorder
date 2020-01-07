<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="名称" v-model="params.stuffName" />
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="280"
      :columns="flowDocumentColumns"
      :query="queryFlowDocument"
    >
      <template slot="fab">
        <AddFlowDocumentOperator @success="search" />
      </template>

      <template slot-scope="props">
        <EditFlowDocumentOperator v-model="props.row" @success="search" />
        <RemoveFlowDocumentOperator v-model="props.row" @success="search" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import { queryFlowDocument } from '@/api/workflow/flowDocument';
import flowDocumentColumns from './flowDocumentColumns';

import AddFlowDocumentOperator from './operators/AddFlowDocumentOperator';
import EditFlowDocumentOperator from './operators/EditFlowDocumentOperator';
import RemoveFlowDocumentOperator from './operators/RemoveFlowDocumentOperator';

export default {
  name: 'PageFlowStuffManager',

  data() {
    return {
      flowDocumentColumns,

      params: {}
    };
  },

  components: {
    AddFlowDocumentOperator,
    EditFlowDocumentOperator,
    RemoveFlowDocumentOperator
  },

  methods: {
    queryFlowDocument,

    search() {
      const params = this.params;

      return this.$refs.Table.load({ params });
    }
  },

  mounted() {
    this.search();
  }
};
</script>
