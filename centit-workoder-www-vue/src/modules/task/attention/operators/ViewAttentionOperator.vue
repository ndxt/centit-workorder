<template>
  <ModalOperator type="info" text="查看" title="查看" width="1280">
    <div style="height: 600px; overflow: auto;">

      <AttentionFlowChart/>

      <TabList :tabData="tabs">
        <AttentionMaterialList
          v-model="value"
          slot="material"
        />

        <AttentionHandleLogList
          v-model="value"
          slot="handleLog"
        />
      </TabList>

    </div>
  </ModalOperator>
</template>

<script>
  import AttentionFlowChart from '../model/AttentionFlowChart'
  import AttentionMaterialList from '../model/AttentionMaterialList'
  import AttentionHandleLogList from '../model/AttentionHandleLogList'

  export default {
  name: 'ViewAttentionOperator',

  data() {
    return {
      tabs: [
        {
          name: '材料列表',
          component: 'material',
        },
        {
          name: '办理日志',
          component: 'handleLog',
        },
      ],
    }
  },

  props: {
    value: Object,
  },

  components: {
    AttentionFlowChart,
    AttentionMaterialList,
    AttentionHandleLogList,
  },

  methods: {
    async beforeOpen() {
    },
  },
}
</script>
