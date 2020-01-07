<template>
  <div class="zpa-box" :class="boxClass">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'zpaBox',

  props: {
    row: Boolean,

    column: Boolean,

    center: Boolean,

    start: Boolean,

    end: Boolean,

    top: Boolean,

    middle: Boolean,

    bottom: Boolean,
  },

  computed: {
    boxClass() {
      return {
        'zpa-row': this.row,
        'zpa-column': this.column,
        start: this.start,
        center: this.center,
        end: this.end,
        top: this.top,
        middle: this.middle,
        bottom: this.bottom,
      }
    },
  },
}
</script>

<style scoped lang="less">
.zpa-box {
  border: 1px solid #c7c7c7;
}
</style>
