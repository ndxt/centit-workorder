<template>
  <Box>
    <SearchLayout>
      <SearchBox slot="search" @search="search" @reset="search">
        <zpa-text-input label="用户名" v-model="params.userName" />
      </SearchBox>

      <DataList
        ref="Table"
        index
        :operWidth="120"
        :columns="columns"
        :query="queryUnitUser"
      >
        <template slot="fab">
          <AddUserOperator v-model="value" @success="search" />
        </template>
        <template slot-scope="props">
          <EditUserOperator v-model="props.row" @success="search" />
          <RemoveUserOperator v-model="props.row" @success="search" />
        </template>
      </DataList>
    </SearchLayout>
  </Box>
</template>

<script>
import {queryUnitUser} from '@/api/admin/unit'
import AddUserOperator from '../rightOperators/AddUserOperator'
import EditUserOperator from '../rightOperators/EditUserOperator'
import RemoveUserOperator from '../rightOperators/RemoveUserOperator'
import columns from './userColumns'
export default {
  name: 'UnitUser',

  data() {
    return {
      params: {},

      columns,
    }
  },

  components: {
    AddUserOperator,
    EditUserOperator,
    RemoveUserOperator,
  },

  props: {
    value: Object,
  },

  watch: {
    value(news, old) {
      this.$set(this.params, 'userName', '')
      this.params.id = this.value.unitCode
      const params = this.params
      return this.$refs['Table'].load({params})
    }
  },

  methods: {
    queryUnitUser,

    search() {
      this.params.id = this.value.unitCode
      const params = this.params
      return this.$refs['Table'].load({params})
    },
  },

  mounted() {
    this.search()
  },
}
</script>
