<template>
  <div class="page-search-box" height="auto">
    <form>
      <div class="form-box">
        <slot ></slot>
        <div class="btn-box">
          <i-button class="search-btn" icon="md-search" type="primary" @click="onClickSearch">{{primaryText}}</i-button>
          <i-button class="more-btn" icon="ios-more"></i-button>
        </div>
      </div>
    </form>
  </div>
</template>
<script>
export default {
  name: 'PageSearchBox',

  props: {
    primaryText: {
      type: String,
      default: '搜索',
    },
  },

  data() {
    return {
      isValidValues: [
        {
          value: '',
          text: '全部',
        },
        {
          value: 'T',
          text: '在用',
        },
        {
          value: 'F',
          text: '禁用',
        },
      ],
    }
  },

  methods: {
    onClickSearch() {
      this.$emit('search')
    },
  },
  mounted() {
    this.search()
  },
}
</script>

<style scoped lang="less">
  .page-search-box {
    padding: 8px;
    .form-box {
      display: flex;
      flex-wrap: wrap;
      .btn-box {
        padding: 8px;margin-left:auto;
        .search-btn {
          background-color: #169989;
        }
        .more-btn {
          background-color: #efefef;color: #6c6c6c;
        }
      }
    }
  }
</style>
