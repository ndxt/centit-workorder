<template>
  <ModalOperator type="primary" text='复制' title="复制流程图" width="1280">
    <AsideLayout no-gutter asideWidth="70%" :z-index="0" style="height: 500px;">
      <zpa-form ref="Form">
        <FlowDefineBaseInfo isCopy v-model="data"/>
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
  createNewFlow,
  copyFlowDefine,
} from '@/api/workflow/flowDefine'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import FlowDefineBaseInfo from '../FlowDefineBaseInfo'
import FlowDefineStageList from './stage/FlowDefineStageList'
import FlowDefineRoleList from './role/FlowDefineRoleList'
import FlowDefineVariableList from './variable/FlowDefineVariableList'
import FlowDefineVersionList from './version/FlowDefineVersionList'

export default {
  name: 'CopyFlowDefineOperator',

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
    async initialize() {
      this.data = {}
      if (this.value) {
        const res = await queryFlowDefineByCode(this.value.flowCode)
        const newFlow = await createNewFlow() // 获取最新flowcode

        this.data = Object.assign({
          oldFlowCode: res.flowCode,
          doCopyXML: 'F',
        }, res, newFlow)
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return copyFlowDefine(this.data)
    },
  }
}
</script>
