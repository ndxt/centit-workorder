<template>
  <Box>
    <DataList
      ref="Table"
      index
      :columns="settingColumns"
      :query="getUserSetting"
      :isHidePage="true"
    >
    </DataList>
  </Box>
</template>
<script>
import settingColumns from './settingColumns'
import { getUserSetting } from '@/api/admin/user'

export default {
  name: 'UserSetting',
  data() {
    return {
      params: {},
      settingColumns,
    }
  },
  props: {
    value: Object
  },
  methods: {
    getUserSetting,
    search() {
      const params = this.params
      this.params.id = this.value.userInfo.userCode
      this.$refs['Table'].load({ params })
    },
  },

  mounted() {
    this.search()
  },
  watch: {
    value() {
      this.search()
    },
  },
}
</script>
