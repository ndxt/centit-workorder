<template>
  <div>
    <div>123</div>
    <div>456</div>
  </div>
</template>
<script>
export default {
  name: 'knowedgeDoc',
  data() {
    return {
      
    }
  },
  mounted() {
    console.log((2).constructor === Number)
    console.log(Object.prototype.toString.call(2))
  },
  methods: {
    type() {
      console.log((2).constructor === Number)
    }
  }
}
</script>
