<template>
  <zpa-row class="transfer">

    <zpa-column>
      <Card>
        <p slot="title">待选区</p>
        <!--<span slot="extra">{{leftChecked.length}} / 10</span>-->
        <div v-if="filter" class="search">
          <zpa-text-input
            type="search"
            style="padding: 0;"
            @input="filterLeft"
          />
        </div>
        <Tree
          show-checkbox
          multiple
          :data="leftData"
          @on-check-change="checkLeft"
        ></Tree>
      </Card>
    </zpa-column>

    <zpa-column class="btn" width="50px">
      <Button
        type="primary"
        shape="circle"
        icon="ios-arrow-back"
        @click="toLeft"
        :disabled="!rightChecked.length"
      ></Button>
      <Button
        type="primary"
        shape="circle"
        icon="ios-arrow-forward"
        @click="toRight"
        :disabled="!leftChecked.length"
      ></Button>
    </zpa-column>

    <zpa-column>
      <Card>
        <p slot="title">已选区</p>
        <!--<span slot="extra">{{rightChecked.length}} / 10</span>-->
        <div v-if="filter" class="search">
          <zpa-text-input
          type="search"
          style="padding: 0;"
          @input="filterRight"
          />
        </div>
        <Tree
          show-checkbox
          multiple
          :data="rightData"
          @on-check-change="checkRight"
        ></Tree>
      </Card>
    </zpa-column>

  </zpa-row>
</template>

<script>
  import {buildTree} from '@/zpa/utils'

  export default {
  name: 'TreeTransfer',

  data() {
    return {
      leftChecked: [],
      rightChecked: [],
      leftData: this.data || [],
      rightData: [],
      beforeLeftData: this.data || [],
      beforeRightData: [],
    }
  },

  props: {
    data: {
      type: Array,
      default: () => [],
    },
    targetKeys: {
      type: Array,
      default: () => [],
    },
    filter: Boolean,
  },

  computed: {},

  methods: {
    filterLeft(data) {
      if (!data) {
        this.leftData = this.beforeLeftData
      } else {
        const leftData = this.beforeLeftData.concat()
        const flatData = this.flatTree(leftData)

        const left = flatData.filter(d => d.title.indexOf(data) !== -1)
        const filterLeft = []

        left.map(m => m.key)
          .forEach(v => {
            const res = this.filterFlatTree(flatData, v)

            res.forEach(k => {
              if (!filterLeft.map(m => m.key).includes(k.key)) {
                filterLeft.push(k)
              }
            })
          })

        this.leftData = buildTree(filterLeft)
      }
    },

    filterRight(data) {
      if (!data) {
        this.rightData = this.beforeRightData
      } else {
        const rightData = this.beforeRightData.concat()
        const flatData = this.flatTree(rightData)

        const right = flatData.filter(d => d.title.indexOf(data) !== -1)
        const filterRight = []

        right.map(m => m.key)
          .forEach(v => {
            const res = this.filterFlatTree(flatData, v)

            res.forEach(k => {
              if (!filterRight.map(m => m.key).includes(k.key)) {
                filterRight.push(k)
              }
            })
          })

        this.rightData = buildTree(filterRight)
      }
    },

    checkLeft(data) {
      this.leftChecked = data.filter(d => !d.expand).map(m => m.key)
    },

    checkRight(data) {
      this.rightChecked = data.filter(d => !d.expand).map(m => m.key)
    },

    toLeft() {
      let i = this.targetKeys.length

      while (i--) {
        if (this.rightChecked.includes(this.targetKeys[i])) {
          this.targetKeys.splice(i, 1)
        }
      }
      this.rightChecked = []
      this.moveTree()
    },

    toRight() {
      this.targetKeys = this.targetKeys.concat(this.leftChecked)
      this.leftChecked = []
      this.moveTree()
    },

    moveTree() {
      const flatData1 = this.flatTree()
      const flatData2 = this.flatTree()

      const left = flatData1.filter(d => !this.targetKeys.includes(d.key))
      let right = []

      this.leftData = buildTree(left)
      this.beforeLeftData = this.leftData.concat()

      this.targetKeys
        .forEach(v => {
          const res = this.filterFlatTree(flatData2, v)

          res.forEach(k => {
            if (!right.map(m => m.key).includes(k.key)) {
              right.push(k)
            }
          })
        })
      this.rightData = buildTree(right)
      this.beforeRightData = this.rightData.concat()

      this.$emit('changeTargetKeys', this.targetKeys)
    },

    // 平铺树并去除children
    flatTree(data = this.data || [], flat = []) {
      data.forEach(v => {
        const obj = Object.assign({}, v)

        delete obj.children

        flat.push(obj)

        if (v.children && v.children.length) {
          this.flatTree(v.children, flat)
        }
      })

      return flat
    },

    // 找出参数数据所有父级包括自身
    filterFlatTree(flat, parent, filter = []) {
      const res = flat.filter(d => parent === d.key)

      filter.push(res[0])

      if (res[0].parent && res[0].parent !== 0) {
        this.filterFlatTree(flat, res[0].parent, filter)
      }

      return filter
    },
  },

  mounted() {
    this.$nextTick(() => {
      this.moveTree()
    })
  },
}
</script>

<style lang="less">
  .transfer {

    .ivu-card {
      height: 100%;
      display: flex;
      flex-direction: column;

      .ivu-card-head {
        background: #f9fafc;

        p {
          display: flex;
          justify-content: space-between;
        }
      }
      .ivu-card-body {
        display: flex;
        flex: auto;
        flex-direction: column;
        flex-basis: 300px;

        .search {
          margin-bottom: 16px;
        }
        .ivu-tree {
          overflow: auto;
        }
      }
    }
    .btn {
      display: flex;
      align-items: center;
      justify-content: center;

      button {
        margin: 5px 0;
      }
    }
  }
</style>
