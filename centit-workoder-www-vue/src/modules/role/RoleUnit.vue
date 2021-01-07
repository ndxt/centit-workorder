<template>
  <Box>
      <DataList
        ref="UnitTable"
        index
        :operWidth="80"
        :columns="roleUnitColumns"
        :query="!top.dept ? queryRoleUnit : queryDeptRoleUnit"
      >
        <template slot="obtainDate" slot-scope="{value}">
          {{value | day}}
        </template>
        <template slot="secedeDate" slot-scope="{value}">
          {{value | day}}
        </template>
        <template slot="fab">
          <AddRoleUnitOperator v-model="value" @success="reload"/>
        </template>
        <template slot-scope="props">
          <RemoveRoleUnitOperator v-model="props.row" @success="reload"/>
        </template>
      </DataList>

      <DataList
        ref="RoleTable"
        index
        :columns="roleUserInheritColumns"
        :query="queryRoleUserInherit"
      />
  </Box>
</template>

<script>
  import roleUnitColumns from './columns/roleUnitColumns'
  import roleUserInheritColumns from './columns/roleUserInheritColumns'
  import AddRoleUnitOperator from './operators/AddRoleUnitOperator'
  import RemoveRoleUnitOperator from './operators/RemoveRoleUnitOperator'
  import {queryDeptRoleUnit, queryRoleUnit, queryRoleUserInherit,} from '@/api/admin/role'

  export default {
  name: 'RoleUnit',

  inject: ['top'],

  data() {
    return {
      roleUnitColumns,
      roleUserInheritColumns,
      params: {},
    }
  },

  computed: {
    unitTable() {
      return this.$refs['UnitTable']
    },
    roleTable() {
      return this.$refs['RoleTable']
    },
  },

  props: {
    value: Object,
  },

  components: {
    AddRoleUnitOperator,
    RemoveRoleUnitOperator,
  },

  methods: {
    queryRoleUnit,
    queryDeptRoleUnit,
    queryRoleUserInherit,

    reload() {
      const params = {
        roleCode: this.value.roleCode
      }
      this.unitTable.load({ params })
      this.roleTable.load({ params })
    },
  },

  mounted() {
    this.reload()
  },

  watch: {
    value() {
      this.reload()
    },
  },
}
</script>
