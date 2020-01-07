<template>
  <ModalOperator :text="type==1?'新增创建流程':'新增审批过程'" title="新增通用审批模块" width="1000">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <ApprovalModuleInfo v-model="data" :type="type" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import { addApprovalModule } from '@/api/workflow/flowApprovalModule';

import ApprovalModuleInfo from './ApprovalModuleInfo';

export default {
  name: 'AddApprovalModuleOperator',

  data() {
    return {
      data: {
        templateJson: {
          formOrDocument: 'F',
          formInfo: {},
          documentInfo: {},
          ideaInfo: {},
          stuffInfo: {
            stuffGroupConfig: {}
          },
          flowInfo: {
            relatedFlowType: 'A'
          },
          flowVariables: [],
          flowRoles: []
        }
      }
    };
  },

  props: {
    type: Number | String
  },

  provide() {
    return {
      operator: this
    };
  },

  components: {
    ApprovalModuleInfo
  },

  methods: {
    onClose() {
      this.$emit('success', this.data);
    },

    onCancel() {
      this.$emit('cancel');
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      if (+this.type === 1) {
        this.data.moduleType = 'C';
      } else {
        this.data.moduleType = 'P';
      }
      return addApprovalModule(this.data);
    }
  }
};
</script>
