<template>
  <ModalOperator text="新建角色" title="新建角色" :width="600" :ghost="false">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="角色编码"
          v-model="data.roleCode"
          :max="100"
          required
        />
        <zpa-text-input
          label="角色名称"
          v-model="data.roleName"
          :max="100"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import {
  addFlowRole,
} from '@/api/workflow/flowroledefine'

export default {
  name: 'AddFlowRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addFlowRole(this.data)
    },
  },
}
</script>
