<template>
  <ModalOperator :footerHide="true" type="info" text="审批步骤" title="审批步骤" width="800">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <WatchFlowChartInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {queryOptidea} from '@/api/workflow/flowApprovalModule';
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';

  import WatchFlowChartInfo from './WatchFlowChartInfo';

  export default {
  name: 'WatchFlowChartOperator',

  mixins: [ModalOperatorMixin],

  data() {
    return {
      data: {}
    };
  },

  components: {
    WatchFlowChartInfo
  },

  methods: {
    async beforeOpen() {
      this.data = await queryOptidea(this.value.flowInstId);
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    }
  }
};
</script>
