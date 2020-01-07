<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="名称" v-model="params.flowOptName" />
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="280"
      :columns="flowApprovalViewColumns"
      :query="queryWatchList"
    >
      <template
        v-slot:flowOptState="props"
      >{{props.row.flowOptState=='T'?'暂存':props.row.flowOptState==P?'暂停':props.row.flowOptState=='C'?'完成':props.row.flowOptState=='N'?'正常':props.row.flowOptState==F?'非正常终止':''}}</template>
      <template slot-scope="props">
        <WatchFlowChartOperator v-model="props.row" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import { queryWatchList } from '@/api/workflow/flowApprovalModule';
import flowApprovalViewColumns from './flowApprovalViewColumns';
import WatchFlowChartOperator from './operators/WatchFlowChartOperator';

export default {
  name: 'PageFlowApprovalView',

  data() {
    return {
      flowApprovalViewColumns,

      params: {}
    };
  },

  components: {
    WatchFlowChartOperator
  },

  methods: {
    queryWatchList,

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
