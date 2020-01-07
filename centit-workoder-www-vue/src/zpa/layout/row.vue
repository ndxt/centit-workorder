<template>
  <div class="zpa-row" :style="layoutStyle" :class="layoutClass">
    <slot></slot>
  </div>
</template>

<script>
import layoutMixin from './mixin'

export default {
  name: 'zpaRow',

  mixins: [layoutMixin],

  methods: {
    flexDirection() {
      return 'row'
    },
  },
}
</script>
