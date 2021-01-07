<template>
  <ModalOperator type="info" text="编辑" title="公文编辑" width="1000">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <FlowDocumentInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {getFlowDocument, saveFlowDocument} from '@/api/workflow/flowDocument';

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
  import FlowDocumentInfo from './FlowDocumentInfo';

  export default {
  name: 'EditFlowDocumentOperator',

  mixins: [ModalOperatorMixin],

  data() {
    return {
      data: {}
    };
  },

  components: {
    FlowDocumentInfo
  },

  methods: {
    initialize() {},

    async beforeOpen() {
      this.data = await getFlowDocument(this.value.documentCode);
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      return saveFlowDocument(this.data);
    }
  }
};
</script>
