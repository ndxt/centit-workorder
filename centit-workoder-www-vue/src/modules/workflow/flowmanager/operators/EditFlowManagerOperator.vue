<template>
  <ModalOperator type="info" text="编辑" title="编辑流程实例" width="1280">
    <AsideLayout
      no-gutter
      height="450px"
      asideWidth="70%"
      :z-index="0"
    >
      <zpa-form ref="Form">
        <FlowManagerBaseInfo v-model="data"/>
      </zpa-form>

      <TabList hasDataList slot="aside" v-if="Object.keys(data).length > 0" :tabData="tabs">
        <FlowManagerNodeList slot="viewNode" v-model="data"/>

        <FlowManagerVariableList slot="viewVariable" v-model="data"/>

        <FlowManagerRoleList slot="viewRole" v-model="data"/>

        <FlowManagerUnitList slot="viewUnit" v-model="data"/>

        <FlowManagerLogList slot="viewLog" v-model="data"/>
      </TabList>
    </AsideLayout>
  </ModalOperator>
</template>

<script>
  import {saveFlowManager,} from '@/api/workflow/flowManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowManagerBaseInfo from './FlowManagerBaseInfo'
  import FlowManagerNodeList from './node/FlowManagerNodeList'
  import FlowManagerVariableList from './variable/FlowManagerVariableList'
  import FlowManagerRoleList from './role/FlowManagerRoleList'
  import FlowManagerUnitList from './unit/FlowManagerUnitList'
  import FlowManagerLogList from './log/FlowManagerLogList'

  export default {
  name: 'EditFlowManagerOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowManagerBaseInfo,
    FlowManagerNodeList,
    FlowManagerVariableList,
    FlowManagerRoleList,
    FlowManagerUnitList,
    FlowManagerLogList,
  },

  data() {
    return {
      tabs: [
        {
          name: '节点列表',
          component: 'viewNode',
        },
        {
          name: '流程变量',
          component: 'viewVariable',
        },
        {
          name: '办件角色',
          component: 'viewRole',
        },
        {
          name: '流程机构',
          component: 'viewUnit',
        },
        {
          name: '日志信息',
          component: 'viewLog',
        },
      ],
    }
  },

  methods: {
    async beforeOpen() {
      this.data = await Object.assign({}, this.value)
    },

    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      return saveFlowManager(this.data)
    },
  },
}
</script>
