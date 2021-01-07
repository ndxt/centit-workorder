<template>
  <Box>
    <DataList
      ref="Table"
      index
      :operWidth="80"
      :columns="columns"
      :query="top.dept? queryCurrentUnitRole :queryUnitRole"
    >
      <template slot="obtainDate" slot-scope="{value}">
        {{value | day}}
      </template>
      <template slot="secedeDate" slot-scope="{value}">
        {{value | day}}
      </template>
      <template slot="fab">
        <AddRoleOperator v-model="value" @success="search" />
      </template>
      <template slot-scope="props">
        <RemoveRoleOperator v-model="props.row" @success="search" />
      </template>
    </DataList>
  </Box>
</template>

<script>
  import {queryCurrentUnitRole, queryUnitRole} from '@/api/admin/unit'
  import AddRoleOperator from '../rightOperators/AddRoleOperator'
  import RemoveRoleOperator from '../rightOperators/RemoveRoleOperator'

  import columns from './roleColumns'

  export default {
  name: 'UnitRole',

  data() {
    return {
      params: {},

      columns,
    }
  },
  inject: ['top'],
  props: {
    value: Object,
  },
  components: {
    AddRoleOperator,
    RemoveRoleOperator,
  },

  watch: {
    value(news, old) {
      this.params.id = this.value.unitCode
      const params = this.params
      return this.$refs['Table'].load({params})
    }
  },

  methods: {
    queryUnitRole,
    queryCurrentUnitRole,
    search() {
      const params = this.params
      this.params.id = this.value.unitCode
      return this.$refs['Table'].load({params})
    },
  },

  mounted() {
    this.search()
  },
}
</script>
