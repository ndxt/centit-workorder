<template>
  <div class="title-box">
    <div class="info-box">
      <p>
        <span class="title-name">{{title}}</span>
        <slot name="more-title-info" class="title-info"></slot>
      </p>
    </div>
    <div>
      <div class="btn-box">
        <slot></slot>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    title: String,
  }
}
</script>

<style scoped lang="less">
  .title-box {
    border-bottom: 1px solid #dcdee2;padding: 13px 0 29px;
    .info-box {
      float: left;
      .title-name {
        font-size: 24px;color: #323232;font-weight: bold;padding: 0 15px 0 5px;
      }
      .title-info {
        color: #5f5f5f;font-size: 16px;font-weight: bold;
      }
    }
    .btn-box {
      float: right;display: flex;
    }
  }
</style>
