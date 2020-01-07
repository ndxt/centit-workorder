<template>
  <iframe
    style="width: 100%; height: 100%;"
    :src="src"
    frameborder="0"
  ></iframe>
</template>

<script>
export default {
  name: 'GzChart',

  computed: {
    src() {
      const id = this.$route.params.id
      return `http://ceshi.centit.com/stat/#/view?id=${id}`
    }
  },
}
</script>

<style scoped>

</style>
