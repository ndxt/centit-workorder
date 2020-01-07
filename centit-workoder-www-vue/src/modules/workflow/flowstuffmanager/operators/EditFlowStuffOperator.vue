<template>
  <ModalOperator type="info" text="编辑" title="材料编辑" width="1000">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <FlowStuffInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import { getFlowStuff, saveFlowStuff } from '@/api/workflow/flowStuffManager';

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
import FlowStuffInfo from './FlowStuffInfo';

export default {
  name: 'EditApprovalModuleOperator',

  mixins: [ModalOperatorMixin],

  data() {
    return {
      data: { stuffJson: { stuffGroups: [] } }
    };
  },

  components: {
    FlowStuffInfo
  },

  methods: {
    initialize() {
      this.data = { stuffJson: { stuffGroups: [] } };
    },

    async beforeOpen() {
      this.data = await getFlowStuff(this.value.stuffCode);
      if (!this.data.stuffJson) {
        this.data.stuffJson = {};
      }
      if (!this.data.stuffJson.stuffGroups) {
        this.data.stuffJson.stuffGroups = [];
      }
      console.log(this.data);
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      return saveFlowStuff(this.data);
    }
  }
};
</script>
