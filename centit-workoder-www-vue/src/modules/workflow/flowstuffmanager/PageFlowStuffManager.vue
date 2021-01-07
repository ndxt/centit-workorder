<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="材料名称" v-model="params.documentName" />
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="280"
      :columns="flowStuffManagerColumns"
      :query="queryFlowStuff"
    >
      <template slot="fab">
        <AddFlowStuffOperator @success="search" />
      </template>

      <template slot-scope="props">
        <EditFlowStuffOperator v-model="props.row" @success="search" />
        <RemoveFlowStuffOperator v-model="props.row" @success="search" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
  import {queryFlowStuff} from '@/api/workflow/flowStuffManager';

  import flowStuffManagerColumns from './flowStuffManagerColumns';

  import AddFlowStuffOperator from './operators/AddFlowStuffOperator';
  import EditFlowStuffOperator from './operators/EditFlowStuffOperator';
  import RemoveFlowStuffOperator from './operators/RemoveFlowStuffOperator';

  export default {
  name: 'PageFlowStuffManager',

  data() {
    return {
      flowStuffManagerColumns,

      params: {}
    };
  },

  components: {
    AddFlowStuffOperator,
    EditFlowStuffOperator,
    RemoveFlowStuffOperator
  },

  methods: {
    queryFlowStuff,

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
