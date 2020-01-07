<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除办件角色"
    :content="content"
  />
</template>

<script>
import {
  removeFlowManagerRole,
} from '@/api/workflow/flowManager'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowManagerRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    flowInstId: String,
  },

  computed: {
    content() {
      return `确认删除办件角色[${this.value.roleCode}]吗？`
    },
  },

  methods: {
    onSubmit() {
      removeFlowManagerRole(this.flowInstId, this.value)
    },
  },
}
</script>
