<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
    :ghost="false"
  />
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {removeFlowRole,} from '@/api/workflow/flowroledefine'

  export default {
  name: 'RemoveFlowRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认[${this.value.roleName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeFlowRole(this.value.roleCode)
    },
  },
}
</script>
