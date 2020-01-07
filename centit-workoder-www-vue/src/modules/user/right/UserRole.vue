<template>
  <Box>
    <DataList
      index
      ref="TableInheritedRole"
      :columns="InheritedColumns"
      :query="getUserRolesInherited"
      />

    <DataList
      ref="TableOwnRole"
      index
      :operWidth="80"
      :columns="OwnColumns"
      :query="top.dept ? getDeptUserOwn : getUserRolesOwn"
      >
      <template slot="obtainDate" slot-scope="{value}">
        {{value | day}}
      </template>
      <template slot="secedeDate" slot-scope="{value}">
        {{value | day}}
      </template>
      <template slot="fab">
        <AddUserRoleOwnOperator v-model="value" @success="reload"/>
      </template>
      <template slot-scope="props">
        <RemoveUserRoleOwnOperator v-model="props.row" @success="reload"/>
      </template>
    </DataList>
  </Box>
</template>

<script>
import InheritedColumns from './UserRoleInheritedColumns'
import OwnColumns from './UserRoleOwnColumns'
import AddUserRoleOwnOperator from './AddUserRoleOwnOperator'
import RemoveUserRoleOwnOperator from './RemoveUserRoleOwnOperator'
import { getUserRolesInherited, getUserRolesOwn, getDeptUserOwn } from '@/api/admin/user'
export default {
  name: 'UserRole',
  data() {
    return {
      params: {},
      InheritedColumns,
      OwnColumns,
      Opts: [10, 20]
    }
  },
  inject: ['top'],
  props: {
    value: Object,
  },
  components: {
    AddUserRoleOwnOperator,
    RemoveUserRoleOwnOperator,
  },
  computed: {
    tableInherited() {
      return this.$refs['TableInheritedRole']
    },
    tableOwn() {
      return this.$refs['TableOwnRole']
    },
  },
  methods: {
    getUserRolesInherited,
    getUserRolesOwn,
    getDeptUserOwn,
    search() {
      const params = this.params
      this.params.id = this.value.userInfo.userCode
      // 请求获取继承的角色
      this.tableInherited.load({ params })
      // 请求获取自有角色
      this.tableOwn.load({ params })
    },
    reload() {
      const params = this.params
      this.params.id = this.value.userInfo.userCode
      this.tableOwn.load({ params })
    },
  },
  mounted() {
    this.search()
  },
  watch: {
    value() {
      this.search()
    },
  },
}
</script>
