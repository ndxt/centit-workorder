<template>
  <zpa-column
    class="zpa-box"
    background="#fff"
    :width="width"
    :height="height"
    :class="{ Border: noBorder}"
    no-gutter
  >
    <label class="title" v-if="title">
      {{title}}
      <label style="float:right"><slot name="btn"></slot></label>
    </label>

    <slot></slot>
  </zpa-column>
</template>

<script>
export default {
  name: 'Box',

  props: {
    width: String,
    height: String,
    title: String,
    noBorder: Boolean,
    zIndex: {
      type: Number,
      default: 1,
    },
  },
}
</script>

<style scoped lang="less">
  .zpa-box {
    border-radius: 2px;
    border: 1px solid #dcdee2;
  }
  .title{
    padding: 10px 18px;
    font-size: 15px;
    font-weight: bolder;
  }
  .title>label{
    float: right;
  }
  .Border{
    border: none;
  }
</style>
