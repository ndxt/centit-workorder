<template>
  <ModalOperator fab title="添加用户角色">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="用户名"
          v-model="data.userName"
          :max="100"
          required
          readonly
        />
        <RoleSelect
          label="角色名"
          v-model="data.roleCode"
          :department="top.dept"
          required
        />
        <zpa-date-range-input
          label="生效日期"
          v-model="data"
          startField="obtainDate"
          endField="secedeDate"
          required
        />
        <zpa-textarea
          label="备注"
          :max="85"
          v-model="data.changeDesc"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import { addUserRoleOwn } from '@/api/admin/user'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'AddUserRoleOwnOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  inject: ['top'],

  methods: {
    initialize() {
      this.data = {
        isPrimary: 'T',
        userName: this.value.userInfo.userName,
        userCode: this.value.userInfo.userCode,
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addUserRoleOwn(this.data)
    },
  },
}
</script>
