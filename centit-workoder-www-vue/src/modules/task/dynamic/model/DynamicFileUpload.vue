  <template>
    <div style="width: 640px;">Hello File
      <zpa-upload
        multiple
        type="drag"
        :showUploadList="false"
        :params="{}"
      />
    </div>
  </template>

<script>
  // import dynamicCensorLogColumns from '../columns/dynamicCensorLogColumns'

  import {
    queryDynamicCensorLog,
  } from '@/api/task/dynamic'

  export default {
    name: 'DynamicFileUpload',

    props: {
      value: Object,
    },

    data() {
      // return {
      //   dynamicCensorLogColumns,
      // }
    },

    methods: {
      queryDynamicCensorLog,

      reload() {
        // const params = {
        //   flowInstId: this.value.flowInstId,
        // }
        //
        // this.$refs['Table'].load({params})
      },
    },

    mounted() {
      this.reload()
    },
  }
</script>
