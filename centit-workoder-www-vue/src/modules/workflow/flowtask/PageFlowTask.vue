<template>
  <SearchLayout split="false">
    <SearchBox slot="search" @search="reload" @reset="reload">
      <UserSelect
        filterable
        clearable
        label="用户"
        tag="A"
        v-model="params.userCode"
        @input="reload"
      />
    </SearchBox>

    <TabList hasDataList :tabData="tabs">
      <template v-if="params.userCode">
        <FlowTaskTransferList slot="viewTransfer" ref="Transfer" v-model="params"/>

        <FlowTaskEntrustList slot="viewEntrust" ref="Entrust" v-model="params"/>
      </template>
    </TabList>
  </SearchLayout>
</template>

<script>
  import FlowTaskTransferList from './FlowTaskTransferList'
  import FlowTaskEntrustList from './FlowTaskEntrustList'

  export default {
  name: 'PageFlowTask',

  components: {
    FlowTaskTransferList,
    FlowTaskEntrustList,
  },

  data() {
    return {
      params: {},
      tabs: [
        {
          name: '任务转移',
          component: 'viewTransfer',
        },
        {
          name: '任务委托',
          component: 'viewEntrust',
        },
      ],
    }
  },

  methods: {
    reload() {
      if (this.$refs.Transfer) {
        this.$refs.Transfer.reload()
      }
      if (this.$refs.Entrust) {
        this.$refs.Entrust.reload()
      }
    },
  },

  mounted() {
  },
}
</script>
