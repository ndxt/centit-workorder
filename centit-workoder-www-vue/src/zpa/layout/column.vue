<template>
  <div class="zpa-column" :style="layoutStyle" :class="layoutClass">
    <slot></slot>
  </div>
</template>

<script>
import layoutMixin from './mixin'

export default {
  name: 'zpaColumn',

  mixins: [layoutMixin],

  methods: {
    flexDirection() {
      return 'column'
    },
  },
}
</script>
