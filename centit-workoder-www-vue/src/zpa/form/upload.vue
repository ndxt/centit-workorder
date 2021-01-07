<script>
  import _ from 'lodash'
  import {createHOC} from 'vue-hoc'
  import {Upload} from 'iview'
  import Uploader from '../../components/uploader'

  const options = {
  name: 'zpaUpload',

  data() {
    return {
      fileList: [],
    }
  },

  methods: {
    onBeginUpload: _.debounce(function onBeginUpload(file, fileList) {
      this.$emit('upload', file, fileList)
    }, 20)
  },

  mounted() {
    this.$emit('upload')
  }
}

const renderWith = {
  props: {
    beforeUpload() {
      const context = this

      return function(file) {
        const upload = new Uploader(file)
        this.fileList.push(upload)
        context.fileList = this.fileList
        upload.start()
        context.onBeginUpload(upload, context.fileList)
        this.$emit('upload', upload, context.fileList)
        context.$emit('upload', upload, context.fileList)
        return false
      }
    },
  },
}

export default createHOC(Upload, options, renderWith)
</script>
