<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="名称" v-model="params.moduleName" />
      <zpa-text-input label="编码" v-model="params.moduleCode" />
    </SearchBox>

    <DataList
      :operWidth="320"
      ref="Table"
      index
      :columns="approvalModuleColumns"
      :query="queryApprovalModule"
    >
      <template slot="fab">
        <div class="btn_group">
          <AddApprovalModuleOperator @success="search" :type="1" />
          <AddApprovalModuleOperator @success="search" :type="2" />
        </div>
      </template>
      <template v-slot:moduleType="props">{{props.row.moduleType=='C'?'创建流程模块':'审批过程模块'}}</template>
      <template slot-scope="props">
        <PreviewApprovalModuleOperator v-model="props.row" />
        <EditApprovalModuleOperator
          v-if="props.row.moduleType=='C'"
          v-model="props.row"
          :type="1"
          @success="search"
        />
        <EditApprovalModuleOperator
          v-if="props.row.moduleType=='P'"
          v-model="props.row"
          :type="2"
          @success="search"
        />
        <RemoveApprovalModuleOperator v-model="props.row" @success="search" />
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import { queryApprovalModule } from '@/api/workflow/flowApprovalModule';

import approvalModuleColumns from './approvalModuleColumns';

import AddApprovalModuleOperator from './operators/AddApprovalModuleOperator';
import PreviewApprovalModuleOperator from './operators/PreviewApprovalModuleOperator';
import EditApprovalModuleOperator from './operators/EditApprovalModuleOperator';
import RemoveApprovalModuleOperator from './operators/RemoveApprovalModuleOperator';

export default {
  name: 'PageApprovalModule',

  data() {
    return {
      approvalModuleColumns,

      params: {}
    };
  },

  components: {
    AddApprovalModuleOperator,
    EditApprovalModuleOperator,
    RemoveApprovalModuleOperator,
    PreviewApprovalModuleOperator
  },

  methods: {
    queryApprovalModule,

    search() {
      const params = this.params;

      return this.$refs['Table'].load({ params });
    }
  },

  mounted() {
    this.search();
  }
};
</script>
