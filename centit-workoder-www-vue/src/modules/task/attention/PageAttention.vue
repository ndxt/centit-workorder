<template>
  <SearchLayout>

    <SearchBox slot="search" @search="reload">
      <zpa-text-input label="业务名称" v-model="params.flowOptName"/>
    </SearchBox>

    <DataList
      ref="Table"
      index
      :operWidth="80"
      :columns="attentionColumns"
      :query="queryAttention"
    >
      <template slot="attSetTime" slot-scope="{value}">
        {{value | timeType}}
      </template>
      <template slot-scope="props">
        <button class="viewFlow" type="button" @click="viewInfo(props.row)">查看</button>
        <!--<ViewAttentionOperator v-model="props.row" />-->
      </template>
    </DataList>

  </SearchLayout>
</template>

<script>
  import attentionColumns from './columns/attentionColumns'

  import ViewAttentionOperator from './operators/ViewAttentionOperator'

  import {queryAttention,} from '@/api/task/attention'

  import {openInfo,} from '@/api/workflow/flowManager'

  export default {
  name: 'PageAttention',

  data() {
    return {
      attentionColumns,

      params: {},

    }
  },

  components: {
    ViewAttentionOperator,
  },

  methods: {
    queryAttention,

    reload() {
      const params = this.params
      params.isAtt = 0

      this.$refs['Table'].load({params})
    },

    // ${this.$route.path}
    viewFlow(data) {
      window.open(`/api/appr/page/workflow/flowView.html?flowInstId=${data.flowInstId}&contentPath=/api/appr/`)
    },
    viewInfo(data) {
      openInfo(data.flowInstId)
    },
  },

  mounted() {
    this.$nextTick(() => {
      this.reload()
    })
  },
}
</script>

<style lang="less">
  .viewFlow {
    border: 1px solid #2db7f5;
    color: #2db7f5;
    padding: 2px 5px;
    background: rgba(0, 0, 0, 0);
    border-radius: 3px;
    cursor: pointer;
    vertical-align: middle;
  }
</style>
