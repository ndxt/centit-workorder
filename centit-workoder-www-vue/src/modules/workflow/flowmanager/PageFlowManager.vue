<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="实例名称" v-model="params.flowOptName"/>
      <zpa-text-input label="业务号" v-model="params.flowOptTag"/>
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="280"
      :columns="flowManagerColumns"
      :query="queryFlowManager"
    >
      <template slot-scope="props">
        <!--<Dropdown transfer>-->
          <!--<Icon type="ios-more" size="30"/>-->
          <!--<DropdownMenu slot="list">-->
            <!--<DropdownItem>-->
              <!--<EditFlowManagerOperator v-model="props.row" @success="search"/>-->
            <!--</DropdownItem>-->
            <!--<DropdownItem>-->
              <!--<PauseWakeFlowManagerOperator v-model="props.row" @success="search"/>-->
            <!--</DropdownItem>-->
            <!--<DropdownItem>-->
              <!--<StopRestartFlowManagerOperator v-model="props.row" @success="search"/>-->
            <!--</DropdownItem>-->
            <!--<DropdownItem>-->
              <!--<Button ghost type="primary" size="small" @click="openTarget(props.row)">查看流程图</Button>-->
            <!--</DropdownItem>-->
          <!--</DropdownMenu>-->
        <!--</Dropdown>-->
        <EditFlowManagerOperator v-model="props.row" @success="search"/>
        <PauseWakeFlowManagerOperator v-model="props.row" @success="search"/>
        <StopRestartFlowManagerOperator v-model="props.row" @success="search"/>
        <Button ghost type="primary" size="small" @click="openTarget(props.row)">查看流程图</Button>
      </template>
    </DataList>
  </SearchLayout>
</template>

<script>
import {
  queryFlowManager,
  openFlowView,
} from '@/api/workflow/flowManager'

import flowManagerColumns from './flowManagerColumns'

import EditFlowManagerOperator from './operators/EditFlowManagerOperator'
import PauseWakeFlowManagerOperator from './operators/PauseWakeFlowManagerOperator'
import StopRestartFlowManagerOperator from './operators/StopRestartFlowManagerOperator'

export default {
  name: 'PageFlowManager',

  data() {
    return {
      flowManagerColumns,

      params: {},
    }
  },

  components: {
    EditFlowManagerOperator,
    PauseWakeFlowManagerOperator,
    StopRestartFlowManagerOperator,
  },

  methods: {
    queryFlowManager,

    search() {
      const params = this.params

      return this.$refs.Table.load({params})
    },

    openTarget(row) {
      openFlowView(row.flowInstId)
    },
  },

  mounted() {
    this.search()
  },
}
</script>
