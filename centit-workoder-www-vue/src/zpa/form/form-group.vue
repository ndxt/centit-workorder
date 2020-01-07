<template>
  <fieldset class="zpa-form-group">
    <legend class="title" v-if="title">{{title}}</legend>
    <zpa-row wrap start top no-gutter :inline="inline">
      <slot></slot>
    </zpa-row>
  </fieldset>
</template>

<script>
import props from './form-props'

export default {
  name: 'zpaFormGroup',

  props: {
    title: String,

    inline: Boolean,

    ...props,
  },
}
</script>

<style scoped lang="less">
.zpa-form-group {
  border: none;
}
</style>
