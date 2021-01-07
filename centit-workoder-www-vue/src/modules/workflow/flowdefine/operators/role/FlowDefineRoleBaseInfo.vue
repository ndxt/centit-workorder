<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="角色编码"
      v-model="value.roleCode"
    />
    <!--<dictionary-select-->
      <!--required-->
      <!--filterable-->
      <!--allow-create-->
      <!--label="角色名称"-->
      <!--v-model="value.roleName"-->
      <!--code="FlowUserRole"-->
    <!--/>-->
    <zpa-auto-complete
      label="角色名称"
      v-model="value.roleName"
      :query="getRole"
      @on-select="onSelect"
    >
      <zpa-highlight
        slot-scope="{row}"
        :value="row.text"
        :query="value.roleName"
      />
    </zpa-auto-complete>
    <zpa-select
      label="数据范围"
      v-model="value.formulaCode"
      :query="getFormula"
    />
  </zpa-form-group>
</template>

<script>
  import {queryDictionaryDetails,} from '@/api/admin/cp'
  import {queryFlowFormula,} from '@/api/workflow/flowFormula'

  export default {
  name: 'FlowDefineRoleBaseInfo',

  data() {
    return {
      roles: [],
    }
  },

  props: {
    value: Object,
  },

  methods: {
    async getRole() {
      const res = await queryDictionaryDetails('FlowUserRole')

      this.roles = res.map(m => {
        return {
          value: m.dataCode,
          text: m.dataValue,
        }
      })

      return this.roles
    },

    onSelect(val) {
      const res = this.roles.filter(d => val === d.value)

      this.$set(this.value, 'roleCode', val)
      this.$set(this.value, 'roleName', res[0].text)
    },

    async getFormula() {
      const res = await queryFlowFormula()

      return res.map(m => {
        return {
          value: m.formulaCode,
          text: m.formulaName,
        }
      })
    },
  },
}
</script>
