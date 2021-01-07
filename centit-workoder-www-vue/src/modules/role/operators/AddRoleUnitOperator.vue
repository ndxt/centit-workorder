<template>
  <ModalOperator fab title="新增角色机构">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="角色名"
          v-model="data.roleName"
          required
          readonly
        />
        <UnitTreeSelect
          label="机构名"
          v-model="data.unitCode"
          :department="top.dept"
          required
          filterable
          clearable
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
  import {addRoleUnit,} from '@/api/admin/role'

  export default {
  name: 'AddRoleUnitOperator',

  inject: ['top'],

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    initialize() {
      this.data = {
        roleCode: this.value.roleCode,
        roleName: this.value.roleName,
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addRoleUnit(this.data)
    },
  },
}
</script>
