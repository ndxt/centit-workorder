<template>
  <ModalOperator text="同步角色" title="同步角色" :width="400" :ghost="false">
    <zpa-form ref="Form">
      确认同步审批角色到权限表达式吗？
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import {
  syncFlowRole,
} from '@/api/workflow/flowroledefine'

export default {
  name: 'SyncFlowRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    onSubmit() {
      return syncFlowRole()
    },
  },
}
</script>
