<template>
  <ModalOperator fab title="新增办件角色">
    <zpa-form ref="Form">
      <FlowDefineRoleBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowDefineRole,} from '@/api/workflow/flowDefine'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowDefineRoleBaseInfo from './FlowDefineRoleBaseInfo'

  export default {
  name: 'AddFlowDefineRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowDefineRoleBaseInfo,
  },

  methods: {
    beforeSubmit() {
      if (this.value.flowTeamRoles.filter(d => this.data.roleCode === d.roleCode).length) {
        this.$Message.error('角色已存在！')

        return false
      }

      return this.$refs['Form'].validate()
    },

    async onSubmit() {
      this.value.flowTeamRoles.push({
        roleCode: this.data.roleCode,
        roleName: this.data.roleName,
      })

      const {
        flowCode,
        flowTeamRoles,
      } = this.value

      return addFlowDefineRole({
        flowCode,
        flowTeamRoles,
      })
    },
  },
}
</script>
