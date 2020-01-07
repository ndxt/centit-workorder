<template>
  <DataList
    index
    ref="Table"
    :columns="flowManagerNodeColumns"
    :query="queryFlowManagerNode"
  >
    <template slot-scope="props">
      <Dropdown transfer>
        <Icon type="ios-more" size="30"/>
        <DropdownMenu slot="list">
          <DropdownItem>
            <Button ghost type="primary" size="small">办理</Button>
          </DropdownItem>
          <DropdownItem>
            <GoBackFlowManagerNodeOperator v-model="props.row" :instState="value.instState" @success="reload"/>
          </DropdownItem>
          <DropdownItem>
            <TaskFlowManagerNodeOperator v-model="props.row" :instState="value.instState" @success="reload"/>
          </DropdownItem>
          <DropdownItem>
            <SubmitFlowManagerNodeOperator v-model="props.row" :instState="value.instState" @success="reload"/>
          </DropdownItem>
          <!--<DropdownItem>-->
            <!--<RestartFlowManagerNodeOperator v-model="props.row" :instState="value.instState" @success="reload"/>-->
          <!--</DropdownItem>-->
        </DropdownMenu>
      </Dropdown>
    </template>
  </DataList>
</template>

<script>
import {
  queryFlowManagerNode,
} from '@/api/workflow/flowManager'

import flowManagerNodeColumns from './flowManagerNodeColumns'

import GoBackFlowManagerNodeOperator from './GoBackFlowManagerNodeOperator'
import TaskFlowManagerNodeOperator from './TaskFlowManagerNodeOperator'
import SubmitFlowManagerNodeOperator from './SubmitFlowManagerNodeOperator'
// import RestartFlowManagerNodeOperator from './RestartFlowManagerNodeOperator'

export default {
  name: 'FlowManagerNodeList',

  components: {
    GoBackFlowManagerNodeOperator,
    TaskFlowManagerNodeOperator,
    SubmitFlowManagerNodeOperator,
    // RestartFlowManagerNodeOperator,
  },

  data() {
    return {
      flowManagerNodeColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    queryFlowManagerNode,

    reload() {
      const params = {
        flowInstId: this.value.flowInstId
      }

      this.$refs.Table.load({params})
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
