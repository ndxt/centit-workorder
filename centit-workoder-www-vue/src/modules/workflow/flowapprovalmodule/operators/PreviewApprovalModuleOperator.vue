<template>
  <ModalOperator :footerHide="true" type="info" text="预览" title="预览通用审批模块" width="1000">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <ApprovalModulePreviewInfo v-if="data.templateJson" v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  getApprovalModule
} from '@/api/workflow/flowApprovalModule';

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
import ApprovalModulePreviewInfo from './ApprovalModulePreviewInfo';

export default {
  name: 'PreviewApprovalModuleOperator',

  mixins: [ModalOperatorMixin],

  components: {
    ApprovalModulePreviewInfo
  },

  methods: {
    async beforeOpen() {
      this.data = await getApprovalModule(this.value.moduleCode);
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {}
  }
};
</script>
