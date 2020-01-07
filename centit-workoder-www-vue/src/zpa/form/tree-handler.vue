<template>
  <Icon
    class="handler"
    :type="icon"
    :size="16"
    :style="marginStyle"
    @click.native.stop="onClick"
  />
</template>

<script>
export default {
  name: 'TreeHandler',

  props: {
    value: Object,
  },

  computed: {
    level() {
      return this.value._level || 0
    },

    isExpand() {
      return this.value._expand
    },

    isLoading() {
      return false
    },

    hasChildren() {
      return this.value.children && this.value.children.length
    },

    marginStyle() {
      return {
        marginLeft: this.icon ? `${this.level * 8}px` : `${this.level * 8 + 16}px`
      }
    },

    icon() {
      if (!this.hasChildren) return ''

      if (this.isLoading) return 'ios-loading'

      return this.isExpand ? 'md-arrow-dropdown' : 'md-arrow-dropright'
    },
  },

  methods: {
    onClick() {
      const expand = this.value._expand
      this.$set(this.value, '_expand', !expand)
      this.$emit('update')
    },
  },
}
</script>

<style scoped lang="less">
  .handler {
    color: #666;

    &:hover {
      color: #333;

      &:before {
        text-shadow: #2d8cf0e6 0 0 4px;
      }
    }
  }
</style>
