<template>
  <ModalOperator type="info" text='编辑' title="编辑流程图" width="1280">
    <AsideLayout no-gutter asideWidth="70%" :z-index="0" style="height: 450px;">
      <zpa-form ref="Form">
        <FlowDefineBaseInfo v-model="data"/>
      </zpa-form>

      <TabList hasDataList slot="aside" v-if="Object.keys(data).length > 0" :tabData="tabs">
        <FlowDefineStageList slot="viewStage" v-model="data"/>

        <FlowDefineRoleList slot="viewRole" v-model="data"/>

        <FlowDefineVariableList slot="viewVariable" v-model="data"/>

        <FlowDefineVersionList slot="viewVersions" v-model="data" />
      </TabList>
    </AsideLayout>
  </ModalOperator>
</template>

<script>
import {
  queryFlowDefineByCode,
  addFlowDefine,
} from '@/api/workflow/flowDefine'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import FlowDefineBaseInfo from '../FlowDefineBaseInfo'
import FlowDefineStageList from './stage/FlowDefineStageList'
import FlowDefineRoleList from './role/FlowDefineRoleList'
import FlowDefineVariableList from './variable/FlowDefineVariableList'
import FlowDefineVersionList from './version/FlowDefineVersionList'

export default {
  name: 'EditFlowDefineOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowDefineBaseInfo,
    FlowDefineStageList,
    FlowDefineRoleList,
    FlowDefineVariableList,
    FlowDefineVersionList,
  },

  data() {
    return {
      tabs: [
        {
          name: '流程阶段',
          component: 'viewStage',
        },
        {
          name: '办件角色',
          component: 'viewRole',
        },
        {
          name: '流程变量',
          component: 'viewVariable',
        },
        {
          name: '流程版本',
          component: 'viewVersions',
        },
      ],
    }
  },

  methods: {
    async beforeOpen() {
      this.data = {}
      if (this.value) {
        const res = await queryFlowDefineByCode(this.value.flowCode)

        this.data = Object.assign({}, res)
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addFlowDefine(this.data)
    },
  },
}
</script>
