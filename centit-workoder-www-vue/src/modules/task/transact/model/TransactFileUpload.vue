<template>
  <zpa-form>
  <zpa-row>
    <Upload action="/api/appr/workflow/upload/upload"
            :on-remove="handleRemove"
            :data="uploadData"
            :on-success="handleSuccess"
            :default-file-list="files">

      <Button icon="ios-cloud-upload-outline">文件上传</Button>
    </Upload>
  </zpa-row></zpa-form>
</template>

<script>

  import ZpaRow from '../../../../zpa/layout/row.vue';
  import {deleteFileById, queryNodeInstFiles} from '@/api/task/transact'

  export default {
  components: {ZpaRow},
  name: 'TransactFileUpload',
  props: {
    value: Object,

  },

  data() {
    return {
      uploadData: {nodeInstId: this.value.nodeInstId},
      files: [
        {
          name: 'img1.jpg',
          url: 'http://www.xxx.com/img1.jpg'
        },
        {
          name: 'img2.jpg',
          url: 'http://www.xxx.com/img2.jpg'
        }
      ]
    };
  },
  computed: {
    params() {
      return {
        nodeInstId: this.value.nodeInstId, }
    }},
  methods: {
    reload() {
      this.getFileData()
    },

    handleRemove(file) {
      deleteFileById(file.fileId)
      console.log(file.fileId)
    },
    async getFileData () {
      const res = await queryNodeInstFiles(this.params.nodeInstId
      )
      this.files = res.files
    },
    handleSuccess (res, file, fileList) {
      file.fileId = res.data.fileId
    }
  },

  async mounted() {
    this.reload()
  },
}
</script>
