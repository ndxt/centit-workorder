<template>
  <TabList :tabData="tabs">

    <Box slot="fileInfo">
      <FileInfo v-model="current" :field="field"/>
    </Box>

    <Box slot="fileLog">
      <FileLog v-model="current" :field="field"/>
    </Box>

  </TabList>
</template>

<script>
import FileInfo from './FileInfo'
import FileLog from './FileLog'

export default {
  name: 'PageFileAside',

  components: {
    FileInfo,
    FileLog,
  },

  data() {
    return {
      tabs: [
        {
          name: '文件信息',
          component: 'fileInfo',
        },
        {
          name: '下载日志',
          component: 'fileLog',
        },
      ],
    }
  },

  props: {
    current: Object,
    field: String,
  },
}
</script>
