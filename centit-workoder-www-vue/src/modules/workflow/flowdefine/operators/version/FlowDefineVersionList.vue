<template>
  <DataList
    index
    ref="Table"
    :operWidth="200"
    :columns="flowDefineVersionColumns"
    :query="query"
  >
    <template slot-scope="props">
      <Button type="primary" @click="openFlow(props.row)" size="small" ghost>查看</Button>
      <ChangeFlowDefineVersionOperator v-model="value" type="info" @success="reload"/>
    </template>
  </DataList>
</template>

<script>
import {
  queryAllVersions,
} from '@/api/workflow/flowDefine'

import flowDefineVersionColumns from './flowDefineVersionColumns'

import ChangeFlowDefineVersionOperator from './ChangeFlowDefineVersionOperator'

export default {
  name: 'FlowDefineVersionList',

  components: {
    ChangeFlowDefineVersionOperator,
  },

  data() {
    return {
      flowDefineVersionColumns,
    }
  },

  props: {
    value: Object,
  },

  methods: {
    reload() {
      return this.$refs.Table.load()
    },

    query() {
      return queryAllVersions(this.value)
    },

    openFlow(val) {
      window.open(`/api/appr/page/workflow/indexView.html?flowCode=${val.flowCode}&version=${val.version}&contentPath=/api/appr/`)
    }
  },

  mounted() {
    this.reload()
  },
}
</script>
