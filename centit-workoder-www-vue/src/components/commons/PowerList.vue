<template>
  <AsideLayout
    left
    no-gutter
    height="360px"
    asideWidth="26%"
    :z-index="0"
    >
    <Tree
      slot="aside"
      :data="treeData"
      :show-checkbox="checkbox"
      :multiple="multiple"
      @on-check-change="checkChange"
      @on-select-change="selectChange"
    ></Tree>

    <DataList
      ref="Table"
      isHidePage
      :checkbox="checkbox"
      :columns="listColumns"
      :query="queryPowerRange"
      idField="optScopeCode"
      @onSelectionChange="selectionChange"
    ></DataList>

  </AsideLayout>
</template>

<script>
import {
  queryPower,
  queryPowerRange,
} from '@/api/admin/power'
import {
  walkTree
} from '@/tool'

export default {
  name: 'PowerList',

  data() {
    return {
      treeData: [],
      currentTreeCode: '',
      listColumns: [
        {
          title: '编码',
          key: 'optScopeCode',
          minWidth: 90,
        },
        {
          title: '数据权限名称',
          key: 'scopeName',
          minWidth: 130,
        },
        {
          title: '过滤条件',
          key: 'filterCondition',
          minWidth: 300,
        },
        {
          title: '描述',
          key: 'scopeMemo',
          minWidth: 200,
        },
      ],
    }
  },

  props: {
    value: Object,
    checkbox: Boolean,
    multiple: Boolean,
    query: Function,
  },

  methods: {
    queryPowerRange,

    async load() {
      const data = await queryPower()

      this.treeData = this.handleTree(data)
    },

    async loadShow(params = {}) {
      const data = await this.query(params)

      this.treeData = this.handleTree(data)
    },

    handleTree(data) {
      const vm = this

      walkTree(data, ele => {
        const {
          text,
          optName,
          children,
          optMethods,
        } = ele

        ele.title = optName || text
        ele.expand = true

        if (!children && optMethods) {
          ele.children = optMethods.map(m => {
            return {
              ...m,
              title: m.optName || m.text,
              checked: Object.keys(vm.value || {}).includes(m.optCode),
            }
          })
        }
      })

      return data
    },

    checkChange(data) {
      const array = Object.keys(this.value)

      data = data.filter(d => !d.expand)

      if (data.length) {
        if (data.length > array.length) {
          data.forEach(v => {
            if (!array.filter(d => v.optCode === d).length) {
              this.value[v.optCode] = []
            }
          })
        } else {
          array.forEach(v => {
            if (!data.filter(d => v === d.optCode).length) {
              const res = this.value[v]

              if (res && res.length && this.currentTreeCode === v) {
                this.$refs['Table'].checkRow(res, false)
              }
              delete this.value[v]
            }
          })
        }
      } else {
        for (const key in this.value) {
          delete this.value[key]
        }
      }
    },

    async selectChange(data) {
      if (!data.length || data[0].expand) return false

      const params = {
        optId: data[0].optId,
        optCode: data[0].optCode,
      }

      this.currentTreeCode = params.optCode

      await this.$refs['Table'].load({params})

      if (this.value && Object.keys(this.value).length) {
        const res = this.value[params.optCode]

        if (res && res.length) {
          // this.setTreeChecked(this.treeData, true)
          this.$refs['Table'].checkRow(res, true)
        }
      }
    },

    selectionChange(data) {
      if (this.currentTreeCode === '') return false

      if (data.length) {
        const array = []

        data.forEach(v => {
          array.push(v.optScopeCode)
        })
        this.value[this.currentTreeCode] = array
      } else {
        delete this.value[this.currentTreeCode]
      }
      this.setTreeChecked(this.treeData, data.length > 0)
    },

    setTreeChecked(data, length) {
      data.forEach(v => {
        if (v.children) {
          this.setTreeChecked(v.children, length)
        } else {
          if (this.currentTreeCode === v.optCode) {
            v.checked = length
          }
        }
      })
    },
  },
}
</script>
