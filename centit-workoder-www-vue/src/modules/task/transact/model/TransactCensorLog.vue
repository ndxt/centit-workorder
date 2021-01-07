<template>
  <DataList
    ref="Table"
    id="logTable"
    :columns="transactCensorLogColumns"
    :query="queryTransactCensorLog"
    :height="height"
  >
    <template slot="transDate" slot-scope="{value}">
      {{value | timeType}}
    </template>
  </DataList>
</template>

<script>
  import transactCensorLogColumns from '../columns/transactCensorLogColumns'

  import {queryTransactCensorLog,} from '@/api/task/transact'

  export default {
  name: 'TransactCensorLog',

  props: {
    value: Object,
  },

  data() {
    return {
      transactCensorLogColumns,
      height: 'auto',
    }
  },

  methods: {
    queryTransactCensorLog,

    async reload() {
      const params = {
        flowInstId: this.value.flowInstId,
      }

      await this.$refs['Table'].load({params})
      this.$nextTick(() => {
        this.setHeight()
      })
    },

    setHeight() {
      const ifm = document.getElementById('logTable')

      if (ifm != null) {
        this.height = ifm.clientHeight + 50
        this.$emit('setParentHeight', this.height)
      }
    },
  },

  mounted() {
    // this.reload()
  },
  watch: {
    value: {
      handler() {
        this.reload()
      },
    }
  },
}
</script>
