<template>
  <p>
    hello {{name}}!
    <input type="text" v-model="name">
  </p>
</template>

<script>
export default {
  name: 'Hello',

  props: {
    name: String,
  },
}
</script>

<style scoped>
  p {
    color: red;
  }
</style>
