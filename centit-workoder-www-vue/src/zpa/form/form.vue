<template>
  <form class="zpa-column zpa-form no-fit">
    <slot></slot>
  </form>
</template>

<script>
  import props from './form-props'

  export default {
  name: 'zpaForm',

  data() {
    return {
      fields: [],
    }
  },

  props: {
    ...props,
  },

  provide() {
    return {
      form: this,
    }
  },

  methods: {
    addField(field) {
      if (field && !this.fields.includes(field)) {
        this.fields.push(field)
      }
    },

    removeField(field) {
      const index = this.fields.indexOf(field)

      if (index > -1) {
        this.fields.splice(index, 1)
      }
    },

    async validate() {
      const result = await Promise.all(this.fields.map(f => f.validate()))

      return result.every(r => !r)
    },

    reset() {
      this.fields.forEach(f => f.reset())
    },
  },

  created() {
  },

  destroyed() {
  },
}
</script>

<style scoped lang="less">
  .zpa-form {
    flex-basis: auto;
    max-width: 100%;
  }
</style>
