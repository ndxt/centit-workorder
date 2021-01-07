<template>
  <DataList
    index
    isHidePage
    ref="Table"
    :height="250"
    :columns="columns"
    :query="query"
  >
    <!--<template slot="createDate" slot-scope="{value}">-->
      <!--{{value | timeType}}-->
    <!--</template>-->
  </DataList>
</template>

<script>
  import {viewFlowFormulaUser,} from '@/api/workflow/flowFormula'

  export default {
  name: 'FlowFormulaUser',

  props: {
    formula: String,
  },

  data() {
    return {
      columns: [
        {
          title: '用户编码',
          key: 'userCode',
          sortable: 'custom',
        },
        {
          title: '用户名',
          key: 'userName',
          sortable: 'custom',
        },
        {
          title: '登陆名',
          key: 'loginName',
          sortable: 'custom',
        },
      ],
    }
  },

  methods: {
    query() {
      return viewFlowFormulaUser({
        formula: this.formula,
      })
    },

    reload() {
      this.$refs.Table.load()
    },
  },
}
</script>
