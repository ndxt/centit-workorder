<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {removeFlowRoleDefineList, } from '@/api/workflow/flowroledefine'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowRoleDefineOperator',

  mixins: [
    ModalOperatorMixin,
  ],
  computed: {
    content() {
      console.log(this.value)
      return `确认[${this.value.relatedType}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeFlowRoleDefineList(this.value.id)
    },
  },
}
</script>
