<template>
  <div style="width: 200px;">
    Hello File
    <zpa-upload
      multiple
      type="drag"
      :showUploadList="false"
      :params="{}"
    />
  </div>
</template>

<script>
  import {queryUserLocal} from '@/api/admin/file'

  export default {
  name: 'FileDemo',

  async mounted() {
    const files = await queryUserLocal()

    this.files = files.map(f => f.fileName)
  }
}
</script>
