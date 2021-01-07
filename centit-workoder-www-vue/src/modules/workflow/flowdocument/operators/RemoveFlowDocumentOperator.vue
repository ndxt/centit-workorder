<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除公文"
    :content="content"
  />
</template>

<script>
  import {removeFlowDocument,} from '@/api/workflow/flowDocument'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveFlowStuffOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.documentName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeFlowDocument(this.value.documentCode)
    },
  },
}
</script>
