<template>
  <div class="search-layout zpa-column" :class="className">
    <zpa-row height="auto" style="z-index: 9;">
      <Box>
        <slot name="search"></slot>
      </Box>
    </zpa-row>
    <zpa-column>
      <Box>
        <slot></slot>
      </Box>
    </zpa-column>
  </div>
</template>

<script>
export default {
  name: 'SearchLayout',

  props: {
    noGutter: Boolean,
  },

  computed: {
    className() {
      return {
        'no-gutter': this.noGutter,
      }
    },
  },
}
</script>

<style scoped lang="less">
</style>
