<template>
  <zpa-column>
    <slot name="fab"></slot>
    <zpa-column ref="TableContainer">
      <Table
        width="100%"
        :height="tableHeight"
        size="small"
        :loading="isLoading"
        :stripe="true"
        :border="false"
        :highlight-row="true"
        :columns="tableColumns"
        :data="initItems"
        @on-sort-change="changeSort"
        @on-current-change="changeCurrent"
      ></Table>
    </zpa-column>
  </zpa-column>
</template>

<script>
const defaultSort = {
  sort: 'unitOrder',
  order: 'asc'
};

export default {
  name: 'zpaTable',

  data() {
    return {
      isLoading: false,

      tableHeight: 0,

      params: {},

      initItems: [],

      treeData: [],

      operatorWidth: 256,

      sort: Object.assign({}, defaultSort)
    };
  },

  props: {
    index: Boolean,

    checkbox: Boolean,

    columns: Array,

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
    treeColumnField: String,
    id: String
  },

  computed: {
    tableColumns() {
      const columns = [].concat(this.columns);

      const hasFixed = columns.some(c => c.fixed === 'left');

      columns.forEach(c => {
        c.ellipsis = c.ellipsis !== false;
        c.tooltip = c.tooltip !== false;

        const node = this.$scopedSlots[c.key];
        if (node) {
          c.render = (h, params) =>
            node({
              ...params,
              value: params.row[c.key]
            });
        }
      });

      const col = {
        align: 'left',
        title: columns[0].title,
        key: this.treeColumnField,
        minWidth: 268,
        render: (h, params) => {
          let style = {};

          if (params.row.isInToolbar === 'N') {
            style = {
              height: '39px',
              display: 'flex',
              alignItems: 'center',
              background: '#e3e5e8',
              color: '#c5c8ce',
              borderRadius: '5px'
            };
          }

          if (params.row.state === 'closed' && !params.row.expanded) {
            return h(
              'div',
              {
                style
              },
              [
                h(
                  'icon',
                  {
                    props: {
                      type: 'ios-arrow-forward',
                      size: 16
                    },
                    style: {
                      marginLeft: (params.row.level - 1) * 15 + 'px',
                      cursor: 'pointer'
                    },
                    on: {
                      click: () => {
                        this.toggle(params.index, params.row);
                      }
                    }
                  },
                  ''
                ),
                h(
                  'icon',
                  {
                    props: {
                      type: 'md-folder',
                      size: 16
                    },
                    style: {
                      marginRight: '5px'
                    }
                  },
                  ''
                ),
                h('span', params.row[this.treeColumnField])
              ]
            );
          }
          if (params.row.state === 'closed' && params.row.expanded) {
            return h(
              'div',
              {
                style
              },
              [
                h(
                  'icon',
                  {
                    props: {
                      type: 'ios-arrow-down',
                      size: 16
                    },
                    style: {
                      marginLeft: (params.row.level - 1) * 15 + 'px',
                      cursor: 'pointer'
                    },
                    on: {
                      click: () => {
                        this.toggle(params.index, params.row);
                      }
                    }
                  },
                  ''
                ),
                h(
                  'icon',
                  {
                    props: {
                      type: 'ios-folder-open',
                      size: 16
                    },
                    style: {
                      marginRight: '5px'
                    }
                  },
                  ''
                ),
                h('span', params.row[this.treeColumnField])
              ]
            );
          }
          if (params.row.state === 'open' || !params.row.state) {
            return h(
              'div',
              {
                style
              },
              [
                h(
                  'icon',
                  {
                    props: {
                      type: 'ios-paper',
                      size: 16
                    },
                    style: {
                      marginLeft: (params.row.level - 1) * 15 + 'px',
                      cursor: 'pointer',
                      marginRight: '5px'
                    }
                  },
                  ''
                ),
                h('span', params.row[this.treeColumnField])
              ]
            );
          }
        }
      };
      columns[0] = col;
      // 添加序号
      if (this.index) {
        const col = {
          type: 'index',
          width: 60,
          align: 'center',
          title: '序号'
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
    addData(newData) {
      if (newData.pid && newData.pid !== '0') {
        var index = 0;
        for (var i = 0; i < this.initItems.length; i++) {
          if (this.initItems[i].id === newData.pid) {
            newData.level = this.initItems[i].level + 1;
            index = i;
            if (this.initItems[i].children) {
              this.initItems[i].children.push(newData);
              this.initItems.splice(
                index + this.initItems[i].children.length,
                0,
                newData
              );
            }
          }
        }
      } else {
        newData.level = 1;
        this.initItems.push(newData);
      }
    },
    changeData(idKey, newData) {
      for (let i = 0; i < this.initItems.length; i++) {
        if (this.initItems[i][idKey] === newData[idKey]) {
          var flag = false;
          var oldPid = '';
          if (this.initItems[i]['pid'] !== newData['pid']) {
            flag = true;
            oldPid = this.initItems[i]['pid'];
          }
          for (var item in newData) {
            this.initItems[i][item] = newData[item];
          }
          if (flag) {
            const temp = Object.assign({}, this.initItems[i]);
            this.initItems.splice(i, 1);
            var index = 0;
            for (var k = 0; k < this.initItems.length; k++) {
              if (this.initItems[k].id === oldPid) {
                this.initItems[k].children = this.initItems[k].children.filter(
                  item => {
                    return item.id !== temp.id;
                  }
                );
              }
            }
            for (var j = 0; j < this.initItems.length; j++) {
              if (this.initItems[j].id === oldPid) {
                this.initItems[j].children.filter(item => {
                  return item.id !== temp.id;
                });
                console.log(this.initItems[j].children, temp.id);
              }
              if (this.initItems[j].id === temp.pid) {
                newData.level = this.initItems[j].level + 1;
                index = j;
                if (this.initItems[j].children) {
                  console.log(index + this.initItems[j].children.length + 1);
                  this.initItems[j].children.push(temp);
                  this.initItems.splice(
                    index + this.initItems[j].children.length,
                    0,
                    temp
                  );
                  console.log(this.initItems);
                }
              }
            }
          }
        }
      }
    },
    removeData(idKey, key) {
      for (let i = 0; i < this.initItems.length; i++) {
        if (this.initItems[i][idKey] === key) {
          for (var j = 0; j < this.initItems.length; j++) {
            if (this.initItems[i].pid === this.initItems[j].id) {
              this.initItems[j].children = this.initItems[j].children.filter(
                item => {
                  return item.id !== this.initItems[i].id;
                }
              );
            }
          }
          this.initItems.splice(i, 1);
        }
      }
    },
    tree2List(tree, cb, level = 1) {
      tree = tree || [];
      let list = [];
      tree.forEach(item => {
        list.push(cb.call(this, item, level));

        if (item.expanded && item.children) {
          list = list.concat(this.tree2List(item.children, cb, level + 1));
        }
      });

      return list;
    },

    parseTreeNode(item, level) {
      item.level = level;
      return item;
    },

    parseTree() {
      this.initItems = this.tree2List(this.treeData, this.parseTreeNode);
      //      this.$emit('on-selected-change', this.initItems[0])
    },

    async load({ params = this.params, sort = this.sort, id } = {}) {
      this.isLoading = true;
      this.params = params;
      // TODO data 和 page 的获取改为可配置
      this.treeData = await this.query({ params, sort, id });
      this.$emit('treeData', this.treeData);
      this.parseTree();

      if (this.initItems && this.initItems.length) {
        const row = this.initItems[0];
        row._highlight = true;
        this.changeCurrent(row);
      }

      if (this.initItems.length) {
        this.$nextTick(() => {
          this.operatorWidth =
            this.$el.querySelector('.column_operator').clientWidth + 40;
        });
      }

      this.isLoading = false;
    },

    changeSort({ key: sort, order }) {
      this.sort = {
        sort,
        order
      };

      // 默认排序
      if (!order || order === 'normal') {
        this.sort = Object.assign({}, defaultSort);
      }
      return this.load();
    },

    changeCurrent(now, old) {
      this.$emit('on-selected-change', now, old);
    },

    resize() {
      this.tableHeight = this.$refs['TableContainer'].$el.clientHeight - 16;
    },

    async toggle(index, item) {
      item = this.initItems.filter(node => node[this.id] === item[this.id])[0];

      item.expanded = !item.expanded;

      if (!item.children || item.children.length === 0) {
        const id = item[this.id];
        let params = this.params;
        let sort = {};
        item.children = await this.query({ params, sort, id });
      }

      this.parseTree();
    }
  },

  mounted() {
    this.$root.$on('resize', this.resize);
    this.$nextTick(() => this.resize());
  },

  destroyed() {
    this.$root.$off('resize', this.resize);
  }
};
</script>
<style lang="less">
.ivu-table .demo-table-error-row {
  display: none;
}
</style>
