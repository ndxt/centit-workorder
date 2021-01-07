<template>
  <ModalOperator fab title="新增角色用户">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="角色名"
          v-model="data.roleName"
          required
          readonly
        />
        <UserSelect
          label="用户名"
          v-model="data.userCode"
          :department="top.dept"
          filterable
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
          v-model="data.changeDesc"
          :max="85"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {addRoleUser,} from '@/api/admin/role'

  export default {
  name: 'AddRoleUserOperator',

  inject: ['top'],

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    initialize() {
      this.data = {
        isPrimary: 'T',
        roleCode: this.value.roleCode,
        roleName: this.value.roleName,
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addRoleUser(this.data)
    },
  },
}
</script>
