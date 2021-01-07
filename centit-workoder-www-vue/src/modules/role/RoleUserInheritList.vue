<template>
  <DataList
    ref="Table"
    index
    :columns="roleUserInheritColumns"
    :query="queryRoleUserInherit"
  ></DataList>
</template>

<script>
  import roleUserInheritColumns from './columns/roleUserInheritColumns'
  import {queryRoleUserInherit,} from '@/api/admin/role'

  export default {
  name: 'PageRoleUserInherit',

  data() {
    return {
      roleUserInheritColumns,
      params: {},
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryRoleUserInherit,

    reload() {
      const params = {
        roleCode: this.value.roleCode
      }

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
