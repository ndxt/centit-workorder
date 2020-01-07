<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="名称" v-model="params.flowName"/>
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="300"
      :columns="flowDefineColumns"
      :query="queryFlowDefine"
    >
      <template slot="fab">
        <AddFlowDefineOperator @success="search"/>
      </template>

      <template slot-scope="props">
        <!--<Dropdown transfer>-->
          <!--<Icon type="ios-more" size="30"/>-->
          <!--<DropdownMenu slot="list">-->
            <!--<DropdownItem>-->
              <!--<EditFlowDefineOperator v-model="props.row" @success="search"/>-->
            <!--</DropdownItem>-->
            <!--<DropdownItem>-->
              <!--<CopyFlowDefineOperator v-model="props.row" @success="search"/>-->
            <!--</DropdownItem>-->
            <!--<DropdownItem>-->
              <!--<PublishFlowDefineOperator v-model="props.row" @success="search"/>-->
            <!--</DropdownItem>-->
            <!--<DropdownItem>-->
              <!--<RemoveFlowDefineOperator v-model="props.row" @success="search"/>-->
            <!--</DropdownItem>-->
            <!--<DropdownItem>-->
              <!--<Button ghost type="success" size="small" @click="openFlow(props.row)">编辑流程图</Button>-->
            <!--</DropdownItem>-->
          <!--</DropdownMenu>-->
        <!--</Dropdown>-->
        <EditFlowDefineOperator v-model="props.row" @success="search"/>
        <CopyFlowDefineOperator v-model="props.row" @success="search"/>
        <PublishFlowDefineOperator v-model="props.row" @success="search"/>
        <RemoveFlowDefineOperator v-model="props.row" @success="search"/>
        <Button ghost type="success" size="small" @click="openFlow(props.row)">编辑流程图</Button>
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import {
  queryFlowDefine,
} from '@/api/workflow/flowDefine'

import flowDefineColumns from './flowDefineColumns'

import AddFlowDefineOperator from './operators/AddFlowDefineOperator'
import EditFlowDefineOperator from './operators/EditFlowDefineOperator'
import CopyFlowDefineOperator from './operators/CopyFlowDefineOperator'
import RemoveFlowDefineOperator from './operators/RemoveFlowDefineOperator'
import PublishFlowDefineOperator from './operators/PublishFlowDefineOperator'

export default {
  name: 'PageFlowDefine',

  components: {
    AddFlowDefineOperator,
    EditFlowDefineOperator,
    CopyFlowDefineOperator,
    PublishFlowDefineOperator,
    RemoveFlowDefineOperator,
  },

  data() {
    return {
      flowDefineColumns,

      params: {},
    }
  },

  methods: {
    queryFlowDefine,

    search() {
      const params = this.params

      return this.$refs['Table'].load({params})
    },

    openFlow(val) {
      window.open(`/api/workflow/page/workflow/index.html?flowCode=${val.flowCode}&version=0&contentPath=/api/workflow/`)
    },
  },

  mounted() {
    this.search()
  },
}
</script>
