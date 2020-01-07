<template>
  <div class="aside-layout zpa-row" :class="{reverse: left}">
    <zpa-column :no-gutter="noGutter">
      <slot></slot>
    </zpa-column>

    <zpa-column :width="asideWidth" :height="height" :z-index="zIndex" style="overflow: auto;">
      <slot name="aside"></slot>
    </zpa-column>
  </div>
</template>

<script>
export default {
  name: 'AsideLayout',

  props: {
    noGutter: Boolean,

    height: String,

    left: Boolean,

    asideWidth: {
      type: String,
      default: '480px',
    },

    zIndex: Number,
  },

  computed: {
    asideStyle() {
      return `${this.asideWidth}px`
    },
  },
}
</script>
