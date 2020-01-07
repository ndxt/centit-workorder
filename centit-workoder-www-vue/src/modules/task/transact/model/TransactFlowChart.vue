<template>
  <div>
    <Divider orientation="left" style="font-size: 16px;">业务页面</Divider>
    <iframe ref="iFrame" id="iframe" :src="src" :style="{height}" style="width: 100%; border: none;" scrolling="no"></iframe>
  </div>
</template>

<script>
import {
  queryTransactFormDefById,
} from '@/api/task/transact'

export default {
  name: 'TransactFlowChart',

  props: {
    value: Object,
  },

  data() {
    return {
      src: '',
      height: 'auto',
    }
  },

  methods: {
    async reload() {
      const {
        flowOptTag,
        flowInstId,
        nodeInstId,
      } = this.value
      const data = await queryTransactFormDefById({
        id: this.value.initData.moduleParam.formId,
      })

      this.src = `${data.optFormUrl}?flowOptTag=${flowOptTag}&flowInstId=${flowInstId}&nodeInstId=${nodeInstId}`
      this.value.subUrl = data.optSubmitUrl
      this.value.isSubmit = data.isEdit
      this.value.optFormId = data.optFormId
      const ifm = document.getElementById('iframe')

      if (ifm != null) {
        this.height = 'auto'
        this.height = `${ifm.offsetParent.clientHeight}px`
      }
    },
  },

  // mounted() {
  //   this.reload()
  // },
}
</script>
