<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="业务名称" v-model="params.optName"/>
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="220"
      :columns="flowBusinessColumns"
      :query="queryFlowBusiness"
    >
      <template slot="updateDate" slot-scope="{value}">
        {{value | timeType}}
      </template>

      <template slot="fab">
        <AddFlowBusinessOperator @success="search"/>
      </template>

      <template slot-scope="props">
        <EditFlowBusinessOperator v-model="props.row" @success="search"/>
        <CreateFlowBusinessOperator v-model="props.row" @success="search"/>
        <!--<Button ghost type="primary" size="small" :disabled="props.row.optUrl === ''" @click="openForm(props.row)">创建流程</Button>-->
        <RemoveFlowBusinessOperator v-model="props.row" @success="search"/>
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
  import {queryFlowBusiness,} from '@/api/workflow/flowBusiness'

  import flowBusinessColumns from './flowBusinessColumns'

  import AddFlowBusinessOperator from './operators/AddFlowBusinessOperator'
  import EditFlowBusinessOperator from './operators/EditFlowBusinessOperator'
  import CreateFlowBusinessOperator from './operators/CreateFlowBusinessOperator'
  import RemoveFlowBusinessOperator from './operators/RemoveFlowBusinessOperator'

  export default {
  name: 'PageFlowBusiness',

  components: {
    AddFlowBusinessOperator,
    EditFlowBusinessOperator,
    CreateFlowBusinessOperator,
    RemoveFlowBusinessOperator,
  },

  data() {
    return {
      flowBusinessColumns,

      params: {},
    }
  },

  methods: {
    queryFlowBusiness,

    search() {
      const params = this.params

      return this.$refs['Table'].load({params})
    },

    openForm(row) {
      if (!row.optUrl || !row.defaultFlow) {
        return
      }

      window.open(`http://ceshi.centit.com/platform/#/models/review/${row.optUrl}?flowCode=${row.defaultFlow}`)
    },
  },

  mounted() {
    this.search()
  },
}
</script>
