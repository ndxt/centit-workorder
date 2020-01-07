<template>
  <zpa-row class="search-box" slot="search" height="auto">
    <zpa-form ref="Form" :label-width="labWidth">
      <zpa-form-group inline>
        <slot></slot>
        <zpa-row height="48px" middle style="margin-left: 16px;">
          <Button type="primary" size="small" @click="onClickSearch">
            <!--<Icon type="ios-search" :size="16" />-->
            {{primaryText}}
          </Button>
          <Button size="small" @click="onClickReset">
            <!--<Icon type="ios-close" :size="18" />-->
            重置
          </Button>
        </zpa-row>
      </zpa-form-group>
    </zpa-form>
  </zpa-row>
</template>

<script>
export default {
  name: 'SearchBox',

  props: {
    primaryText: {
      type: String,
      default: '搜索',
    },
    labWidth: {
      type: Number,
      default: 60,
    }
  },

  computed: {
    form() {
      return this.$refs['Form']
    },
  },

  methods: {
    onClickSearch() {
      this.$emit('search')
    },

    onClickReset() {
      this.$refs['Form'].reset()
      this.$emit('reset')
    },
  },
}
</script>

<style scoped lang="less">
  .search-box {
    button {
      margin-right: 10px;
    }
  }
</style>
