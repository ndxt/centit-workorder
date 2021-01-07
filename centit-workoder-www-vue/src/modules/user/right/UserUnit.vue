<template>
  <Box>
    <DataList
      ref="Table"
      index
      :columns="UserUnitColumns"
      :query="getUserUnitList"
      :isHidePage="true"
      >
      <template slot="fab">
        <AddUserUnitOperator v-model="value" @success="search" />
      </template>
      <template slot-scope="props">
        <EditUserUnitOperator v-model="props.row" @success="search" />
        <RemoveUserUnitOperator v-model="props.row" @success="search" />
      </template>
    </DataList>
  </Box>
</template>
<script>
  import UserUnitColumns from './UserUnitColumns'
  import AddUserUnitOperator from './AddUserUnitOperator'
  import EditUserUnitOperator from './EditUserUnitOperator'
  import RemoveUserUnitOperator from './RemoveUserUnitOperator'
  import {getUserUnitList} from '@/api/admin/user'

  export default {
  name: 'UserUnit',
  data() {
    return {
      params: {},
      UserUnitColumns,
    }
  },
  components: {
    AddUserUnitOperator,
    EditUserUnitOperator,
    RemoveUserUnitOperator,
  },

  props: {
    value: Object,
  },

  methods: {
    getUserUnitList,
    search() {
      const params = this.params
      this.params.id = this.value.userInfo.userCode
      this.$refs['Table'].load({ params })
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
