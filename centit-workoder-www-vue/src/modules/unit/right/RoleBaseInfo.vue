<template>
  <zpa-form-group>
    <zpa-text-input
      label="机构名"
      v-model="value.unitName"
      readonly
      required
    />
    <RoleSelect
      label="角色名"
      v-model="value.roleCode"
      :department="top.dept"
      required
    />
    <zpa-date-range-input
      label="生效日期"
      v-model="value"
      startField="obtainDate"
      endField="secedeDate"
      required
    />
    <zpa-textarea
      label="备注"
      :max="85"
      v-model="value.changeDesc"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'RoleBaseInfo',

  props: {
    value: Object,
  },

  inject: ['top'],

  computed: {},
}
</script>
