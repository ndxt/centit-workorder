<template>
  <zpa-row height="auto">

    <Button
      class="btn"

      :disabled="disabled"
      :icon="icon"
    >
      <!--size="small"-->
      <!--type="primary"-->
      {{label}}
    </Button>

    <slot/>

  </zpa-row>
</template>

<script>
export default {
  name: 'FAB',

  props: {
    disabled: Boolean,
    icon: {
      type: String,
      default: 'md-add',
    },
    label: {
      type: String,
      default: '新增',
    },
  },
}
</script>

<style scoped lang="less">
  .btn {
  }
</style>
