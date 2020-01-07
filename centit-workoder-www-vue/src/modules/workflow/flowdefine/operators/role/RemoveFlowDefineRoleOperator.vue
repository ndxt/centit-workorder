<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
  />
</template>

<script>
import {
  addFlowDefineRole,
} from '@/api/workflow/flowDefine'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowDefineRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    obj: Object,
  },

  computed: {
    content() {
      return `确认删除角色[${this.value.roleName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      if (this.obj.flowTeamRoles && this.obj.flowTeamRoles.length) {
        this.obj.flowTeamRoles = this.obj.flowTeamRoles.filter(d => {
          return this.value.flowTeamRoleId !== d.flowTeamRoleId
        })
      }

      if (this.obj.flowRoles && this.obj.flowRoles.length) {
        this.obj.flowRoles = this.obj.flowRoles.filter(d => {
          return this.value.flowTeamRoleId !== d.flowTeamRoleId
        })
      }

      const {
        flowCode,
        flowRoles,
        flowTeamRoles,
      } = this.obj

      return addFlowDefineRole({
        flowCode,
        flowRoles,
        flowTeamRoles,
      })
    },
  },
}
</script>
