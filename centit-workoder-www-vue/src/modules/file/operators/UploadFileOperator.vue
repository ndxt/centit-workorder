<template>
  <ModalOperator
    fab
    footerHide
    label="上传"
    title="上传文件"
    :width="700"
    :disabled="disabled"
    >
    <zpa-form ref="Form">
      <UploadBaseInfo v-model="data"/>
      <zpa-upload
        type="drag"
        :params="data"
      />
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import UploadBaseInfo from '../UploadBaseInfo'

  export default {
  name: 'UploadFileOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    root: String,
  },

  components: {
    UploadBaseInfo,
  },

  computed: {
    disabled() {
      return this.root === undefined
    },
  },

  methods: {
    initialize() {
      this.data = {
        width: 100,
        height: 100,
      }
    },
  },
}
</script>
