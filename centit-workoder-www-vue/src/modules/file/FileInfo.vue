<template>
  <DataList
    ref="Table"
    isHidePage
    :columns="fileInfoColumns"
    :query="queryInfo"
  ></DataList>
</template>

<script>
import fileInfoColumns from './columns/fileInfoColumns'
import {
  queryInfo,
} from '@/api/admin/file'

export default {
  name: 'FileInfo',

  data() {
    return {
      fileInfoColumns,
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
    queryInfo,

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
