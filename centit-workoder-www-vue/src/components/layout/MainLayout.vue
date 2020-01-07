<template>
  <div class="main-layout zpa-column">
    <zpa-row class="main-header" height="64px" :gutter="0" :z-index="10">
      <slot name="header"></slot>
    </zpa-row>

    <div class="zpa-row">
      <zpa-column class="main-aside" width="auto" :gutter="0" :z-index="1" style="z-index: 999;">
        <slot name="aside"></slot>
      </zpa-column>
      <zpa-column class="main-content" no-gutter>
<!--  标题重复      <Breadcrumb/>-->
        <slot></slot>
      </zpa-column>
    </div>
  </div>
</template>

<script>
/* import Breadcrumb from '../commons/Breadcrumb' */

export default {
  name: 'MainLayout',

/*  components: {
    Breadcrumb,
  }, */

}
</script>

<style scoped lang="less">
.main-layout {
  .main-header {
    box-shadow: 0 2px 1px -1px rgba(0, 0, 0, 0.2),
    0 1px 1px 0 rgba(0, 0, 0, 0.14),
    0 1px 3px 0 rgba(0, 0, 0, 0.12);
  }

  .main-aside {
    background-color: #263238;
  }

  .main-content {
    padding: 16px;
    overflow: hidden;
    background: #ececec;
  }
}
</style>
