<template>
  <div>
    <Divider orientation="left" style="font-size: 16px;">审批记录</Divider>

    <DataList
      ref="Table"
      height="auto"
      :columns="dynamicCensorLogColumns"
      :query="queryDynamicCensorLog"
    >
      <template slot="transDate" slot-scope="{value}">
        {{value | timeType}}
      </template>
    </DataList>
  </div>
</template>

<script>
  import dynamicCensorLogColumns from '../columns/dynamicCensorLogColumns'

  import {queryDynamicCensorLog,} from '@/api/task/dynamic'

  export default {
  name: 'DynamicCensorLog',

  props: {
    value: Object,
  },

  data() {
    return {
      dynamicCensorLogColumns,
    }
  },

  methods: {
    queryDynamicCensorLog,

    reload() {
      const params = {
        flowInstId: this.value.flowInstId,
      }

      this.$refs['Table'].load({params})
    },
  },

  // mounted() {
  //   this.reload()
  // },
}
</script>
