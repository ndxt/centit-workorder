<template>
  <zpa-column>

    <slot name="fab"/>

    <zpa-column ref="PapersContainer" :style="papersStyle">
      <div
        class="item"
        v-for="v in data"
        :key="v.name"
        :style="itemStyle(v)"
        @click="changeCurrent(v)"
        @dblclick="dblclickRow(v)"
      >
        <Icon
          :type="v.fileType === 'f' ? 'ios-paper' : 'ios-folder-open'"
          color="#57a3f3"
          size="50"
        ></Icon>
        <span>{{v.fileName}}</span>
        <b>{{v.versions !== 0 ? v.versions : ''}}</b>
      </div>
    </zpa-column>

    <Spin v-if="isLoading" size="large" fix></Spin>

  </zpa-column>
</template>

<script>
const defaultSort = {
  sort: 'updateDate',
  order: 'desc',
}

export default {
  name: 'PapersList',

  data() {
    return {
      isLoading: false,
      data: [],
      params: {},
      sort: Object.assign({}, defaultSort),
      currentSelect: '',
    }
  },

  props: {
    query: {
      type: Function,
      require: true,
    },
  },

  computed: {
    papersStyle() {
      return {
        flexDirection: 'row',
        flexWrap: 'wrap',
        alignItems: 'start',
        alignContent: 'start',
        overflow: 'auto',
      }
    },
  },

  methods: {
    itemStyle(data) {
      if (this.currentSelect === data.accessToken) {
        return {
          borderColor: '#57a3f3',
          background: '#fff9d1',
        }
      }
    },

    async load({params = this.params, sort = this.sort} = {}) {
      this.data = []
      this.currentSelect = ''
      this.isLoading = true

      this.params = params

      // TODO data 的获取改为可配置
      this.data = await this.query({params, sort})

      this.isLoading = false
    },

    // changeSort({key: sort, order}) {
    //   this.sort = {
    //     sort,
    //     order,
    //   }
    //
    //   // 默认排序
    //   if (!order || order === 'normal') {
    //     this.sort = Object.assign({}, defaultSort)
    //   }
    //   return this.load()
    // },

    changeCurrent(data) {
      this.currentSelect = data.accessToken
      this.$emit('on-selected-change', data)
    },

    dblclickRow(data) {
      this.$emit('on-dblclick-row', data)
    },
  },
}
</script>

<style scoped lang="less">
  .item {
    width: 100px;
    margin: 5px;
    padding: 15px 5px;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    border: 1px solid rgba(0, 0, 0, 0);
    border-radius: 3px;
    transition: all .2s ease-in-out;
    position: relative;
    &:hover {
      background: #ebf7ff;
    }

    span {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      width: 110%;
    }
    b {
      top: 3px;
      right: 5px;
      position: absolute;
    }
  }
</style>
