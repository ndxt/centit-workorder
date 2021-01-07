<template>
  <ModalOperator type="info" :text="type==1?'编辑创建流程':'编辑审批过程'" title="编辑通用审批模块" width="1000">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <ApprovalModuleInfo v-if="data.templateJson" :type="type" v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {getApprovalModule, saveApprovalModule} from '@/api/workflow/flowApprovalModule';

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
  import ApprovalModuleInfo from './ApprovalModuleInfo';

  export default {
  name: 'EditApprovalModuleOperator',

  mixins: [ModalOperatorMixin],

  components: {
    ApprovalModuleInfo
  },

  props: {
    type: Number | String
  },

  methods: {
    async beforeOpen() {
      this.data = await getApprovalModule(this.value.moduleCode);
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
      return saveApprovalModule(this.data);
    }
  }
};
</script>
