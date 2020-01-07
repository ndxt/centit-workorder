<template>
  <p></p>
</template>

<script>
export default {
  name: 'zpaHighlight',

  props: {
    value: String,

    query: String,
  },

  mounted() {
    this.$el.innerHTML = this.html()
  },

  methods: {
    html() {
      const value = this.value
      const query = this.query

      if (!value || !query) return value
      return value.replace(new RegExp(`(${query})`, 'ig'), '<b style="color: #f00e;">$1</b>')
    },
  },

  watch: {
    value() {
      this.$el.innerHTML = this.html()
    },

    query() {
      this.$el.innerHTML = this.html()
    },
  },
}
</script>

<style scoped>
  b {
    color: #f00e;
  }
</style>
