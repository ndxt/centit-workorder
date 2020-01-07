<template>
  <div>
    <Divider orientation="left" style="font-size: 16px;">业务页面</Divider>
    <iframe ref="iFrame" id="iframe" :src="src" :style="{height}" style="width: 100%; border: none;" scrolling="no"></iframe>
  </div>
</template>

<script>
export default {
  name: 'FlowFrame',

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
      console.log(this.value);
      this.src = this.value.viewUrl + '?flowOptTag=' + this.value.optName
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
