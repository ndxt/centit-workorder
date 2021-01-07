<template>
  <DataList
    ref="Table"
    index
    :operWidth="80"
    :columns="roleUserColumns"
    :sortRule="sortType"
    :query="!top.dept ? queryRoleUser : queryDeptRoleUser"
  >
    <template slot="obtainDate" slot-scope="{value}">
      {{value | day}}
    </template>
    <template slot="secedeDate" slot-scope="{value}">
      {{value | day}}
    </template>
    <template slot="fab">
      <AddRoleUserOperator v-model="value" @success="reload"/>
    </template>
    <template slot-scope="props">
      <RemoveRoleUserOperator v-model="props.row" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
  import roleUserColumns from './columns/roleUserColumns'
  import AddRoleUserOperator from './operators/AddRoleUserOperator'
  import RemoveRoleUserOperator from './operators/RemoveRoleUserOperator'
  import {queryDeptRoleUser, queryRoleUser,} from '@/api/admin/role'

  export default {
  name: 'PageRoleUser',

  inject: ['top'],

  data() {
    return {
      roleUserColumns,
      params: {},
      sortType: {
        sort: 'createDate',
        order: 'desc',
      }
    }
  },

  props: {
    value: Object,
  },

  components: {
    AddRoleUserOperator,
    RemoveRoleUserOperator,
  },

  methods: {
    queryRoleUser,
    queryDeptRoleUser,

    reload() {
      const params = this.params

      params.roleCode = this.value.roleCode
      this.$refs['Table'].load({params})
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
