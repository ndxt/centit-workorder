<template>
  <Box style="margin: 8px;">
    <DataList
      ref="Table"
      isHidePage
      :columns="fileManageColumns"
      :query="queryManage"
    ></DataList>
  </Box>
</template>

<script>
import fileManageColumns from './columns/fileManageColumns'

import {
  queryManage,
} from '@/api/admin/file'

export default {
  name: 'PageFileManage',

  data() {
    return {
      fileManageColumns,
    }
  },

  methods: {
    queryManage,

    reload() {
      this.$refs['Table'].load()
    },
  },

  mounted() {
    this.$nextTick(() => {
      this.reload()
    })
  },
}
</script>
