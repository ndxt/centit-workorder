<template>
  <zpa-column :height="!!height ? 'auto' : undefined">
    <slot name="fab"></slot>
    <zpa-column ref="TableContainer" :height="!!height ? 'auto' : undefined" style="flex: auto;">
      <Table
        ref="table"
        width="100%"
        :height="tableHeight"
        size="small"
        :loading="isLoading"
        :stripe="true"
        :border="false"
        :highlight-row="true"
        :columns="tableColumns"
        :data="data"
        @on-selection-change="selectionChange"
        @on-sort-change="changeSort"
        @on-current-change="changeCurrent"
        @on-row-dblclick="dblclickRow"
        @on-row-click="selectRow"
      ></Table>
    </zpa-column>
    <zpa-row end height="auto" v-if="!!page && !isHidePage">
      <Page
        :total="page.totalRows"
        :current="page.pageNo"
        :pageSize="page.pageSize"
        :page-size-opts="this.pageSizeOpts || opts"
        size="small"
        show-elevator
        show-sizer
        @on-change="changePage"
        @on-page-size-change="changePageSize"
      />
    </zpa-row>
    <slot name="Drawer"></slot>
  </zpa-column>
</template>

<script>
  import _ from 'lodash';

  const defaultSort = {
  sort: 'updateDate',
  order: 'desc'
};

const defaultPage = {
  pageNo: 1,
  pageSize: 10
};

export default {
  name: 'zpaTable',

  data() {
    return {
      opts: [10, 20, 30, 40],

      isLoading: false,

      tableHeight: 0,

      data: [],

      params: {},

      operatorWidth: 140,

      page: Object.assign({}, defaultPage),

      sort: Object.assign({}, defaultSort)
    };
  },

  props: {
    sortRule: Object,

    pageSizeOpts: Array,

    height: Number,

    idField: String,

    index: Boolean,

    checkbox: Boolean,

    columns: Array,

    pageNo: Number,

    pageSize: Number,

    sortKey: String,

    sortOrder: {
      validate(val) {
        return ['asc', 'desc'].includes(val);
      },

      type: String
    },

    query: {
      type: Function,
      require: true
    },

    operWidth: Number,

    isHidePage: Boolean
  },

  computed: {
    tableColumns() {
      const columns = [].concat(this.columns);

      const hasFixed = columns.some(c => c.fixed === 'left');

      columns.forEach(c => {
        c.ellipsis = c.ellipsis !== false;
        c.tooltip = c.tooltip !== false;

        const format = c.format;
        const node = this.$scopedSlots[c.key];
        if (node) {
          c.render = (h, params) => {
            params.value = params.row[c.key];
            return node(params);
          };
        } else if (format) {
          c.render = (h, params) => {
            params.value = params.row[c.key];
            return h('span', [format(params)]);
          };
        }
      });

      // 添加序号
      if (this.index) {
        const col = {
          type: 'customIndex',
          width: 60,
          align: 'center',
          title: '序号',
          render: (h, params) => {
            return h(
              'span',
              params.index + (this.page.pageNo - 1) * this.page.pageSize + 1
            );
          }
        };
        if (hasFixed) col.fixed = 'left';
        columns.unshift(col);
      }

      // 添加checkbox
      if (this.checkbox) {
        const col = { type: 'selection', width: 60, align: 'center' };
        if (hasFixed) col.fixed = 'left';
        columns.unshift(col);
      }

      const { default: $operators } = this.$scopedSlots;

      if ($operators) {
        columns.push({
          key: '$operators',
          title: '操作',
          align: 'center',
          width: this.operWidth || this.operatorWidth,
          fixed: 'right',
          render: (h, params) => {
            return h(
              'div',
              {
                class: {
                  column_operator: true
                },
                style: {
                  whiteSpace: 'nowrap',
                  display: 'inline-block'
                }
              },
              [
                $operators({
                  ...params
                })
              ]
            );
          }
        });
      }

      return columns;
    }
  },

  methods: {
    checkRow(data = [], flag) {
      if (!data.length || !this.idField) return false;

      this.data
        .filter(d => data.includes(d[this.idField]))
        .forEach(v => {
          v._checked = !flag; // 由于iView的bug，当直接操作列表复选框时，_checked是不会更改的，所以强制修改一次来触发数据绑定
          v._checked = flag;
        });
    },

    async load({
      params = this.params,
      page = this.page,
      sort = this.sortRule || this.sort
    } = {}) {
      this.data = [];
      this.isLoading = true;

      // 如果参数改变，页数改为1
      if (!_.isEqual(params, this.params)) {
        this.page.pageNo = 1;
        page.pageNo = 1;
      }
      this.params = params;

      // TODO data 和 page 的获取改为可配置
      const result = await this.query({ params, page, sort });
      const objList = result.objList || result;
      const pageDesc = result.pageDesc;

      if (
        objList &&
        objList.length &&
        this.$el.querySelector('.column_operator')
      ) {
        this.$nextTick(() => {
          this.operatorWidth =
            this.$el.querySelector('.column_operator').clientWidth + 40;
        });
      }

      this.page = pageDesc || this.page;
      this.data = objList;

      if (this.data && this.data.length) {
        const row = this.data[0];
        row._highlight = true;
        this.changeCurrent(row);
      }
      const width = this.operWidth || this.operatorWidth;
      this.emitOperatorsWidth(width);
      this.isLoading = false;
    },

    selectionChange(data) {
      this.$emit('onSelectionChange', data);
    },

    selectRow(now, index) {
      this.$emit('onSelectRow', now, index);
    },

    appendRow(row) {
      this.data.push(row);
    },

    insertRow(row, index) {
      if (index === undefined) {
        return this.appendRow(row);
      }

      if (index === 0) {
        return this.data.unshift(row);
      }

      // 在新位置插入数据
      this.data.splice(index - 1, 0, row);
    },

    emitOperatorsWidth(width) {
      this.$emit('operatorsWidth', width);
    },

    updateRow(row, index) {
      this.$set(this.data, index, row);
    },

    deleteRow(index) {
      this.data.splice(index, 1);
    },

    changeSort({ key, order, column }) {
      this.sort = {
        sort: column.sortKey || key,
        order
      };

      // 默认排序
      if (!order || order === 'normal') {
        this.sort = Object.assign({}, defaultSort);
      }
      return this.load();
    },

    changePage(pageNo) {
      this.page.pageNo = pageNo;
      this.query({
        page: this.page
      });
      return this.load();
    },

    changePageSize(pageSize) {
      this.page.pageSize = pageSize;
      return this.load();
    },

    changeCurrent(now, old) {
      this.$emit('on-selected-change', now, old);
    },

    dblclickRow(now, index) {
      this.$emit('on-dblclick-row', now, index);
    },

    resize() {
      this.tableHeight =
        this.height || this.$refs['TableContainer'].$el.clientHeight - 16;
    }
  },

  mounted() {
    this.page.pageNo = this.pageNo || this.page.pageNo;
    this.page.pageSize = this.pageSize || this.page.pageSize;

    this.$root.$on('resize', this.resize);

    this.$nextTick(() => this.resize());
  },

  destroyed() {
    this.$root.$off('resize', this.resize);
  }
};
</script>
<style>
.top-btns button {
  margin: 5px 10px 5px 0;
}
</style>
