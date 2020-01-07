<template>
  <ModalOperator fab title="新增公文" width="1000">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <FlowDocumentInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  addFlowDocument,
} from '@/api/workflow/flowDocument'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import FlowDocumentInfo from './FlowDocumentInfo'

export default {
  name: 'AddFlowDocumentOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      data: {},
    }
  },

  components: {
    FlowDocumentInfo,
  },

  methods: {
    initialize() {
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addFlowDocument(this.data)
    },
  },
}
</script>
