<template>
  <DataList
    ref="Table"
    index
    isHidePage
    :columns="fileLogColumns"
    :query="queryLog"
  ></DataList>
</template>

<script>
  import fileLogColumns from './columns/fileLogColumns'
  import {queryLog,} from '@/api/admin/file'

  export default {
  name: 'FileLog',

  data() {
    return {
      fileLogColumns,
      params: {},
    }
  },

  props: {
    value: Object,
    field: {
      type: String,
      default: 'fileId',
    },
  },

  methods: {
    queryLog,

    reload() {
      const params = {
        fileId: this.value[this.field]
      }

      this.$refs['Table'].load({params})
    },
  },

  mounted() {
    this.reload()
  },

  watch: {
    value() {
      this.reload()
    },
  },
}
</script>
