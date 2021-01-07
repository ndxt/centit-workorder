<template>
  <zpa-form-item
    :error="error"
    :success="success"
    :validating="validating"
    :required="required"
    :readonly="readonly"
    :disabled="disabled"
    :uuid="uuid"
    :span="span"
    :push="push"
    :label="label"
    :labelWidth="labelWidth"
    :labelAlign="labelAlign"
    :itemHeight="itemHeight"
  >
    <i-select
      :value="renderValue"
      :multiple="multiple"
      :clearable="clearable"
      :filterable="filterable"
      :readonly="readonly"
      :disabled="disabled"
      remote
      @on-change="onInputChange"
      @on-query-change="treeFilter"
    >
      <i-option
        v-for="(v, i) in renderValues"
        :key="i"
        :value="getValue(v)"
        :label="getText(v)"
      >
        <zpa-row middle>
          <TreeHandler
            :value="v"
            @update="onUpdate"
          />
          <slot :row="v" :index="i">
            {{getText(v)}}
          </slot>
        </zpa-row>
      </i-option>
    </i-select>
  </zpa-form-item>
</template>

<script>
  import inputMixin from './mixins/input'
  import queryMixin from './mixins/query'
  import valueTextMixin from './mixins/value-text'
  import stringifyMixin from './mixins/stringify'
  import {flatTree} from '../utils'
  import TreeHandler from './tree-handler'

  export default {
  name: 'zpaTreeSelect',

  mixins: [
    inputMixin,
    queryMixin,
    valueTextMixin,
    stringifyMixin,
  ],

  components: {
    TreeHandler,
  },

  data() {
    return {
      trees: [],

      q: '',
    }
  },

  props: {
    value: [String, Array, Number],

    values: Array,

    multiple: Boolean,

    clearable: {
      type: Boolean,
      default: true,
    },

    filterable: Boolean,
  },

  computed: {
    type() {
      return this.multiple ? 'array' : 'string'
    },
  },

  methods: {
    treeFilter(q) {
      this.q = q
      this.$nextTick(this.onUpdate)
    },

    onUpdate(values = this.trees) {
      if (!values) {
        this.renderValues = []
        return
      }

      this.renderValues = flatTree(values)

      // 搜索
      if (this.q) {
        const {q, getText} = this
        this.expandParent(
          this.renderValues,
          n => getText(n).indexOf(q) > -1,
          {hideOther: true}
        )
      } else {
        const {value, getValue} = this
        if (value) {
          this.expandParent(this.renderValues, n => getValue(n) === value)
        }
      }

      this.renderValues = this.renderValues.filter(n => n._hide === false)
    },

    expandParent(list, needExpand, options = {}) {
      // 缓存搜索索引
      let index = 0
      list.forEach((n, i) => {
        // 符合条件
        if (needExpand(n)) {
          // 显示
          n._hide = false
          let l = n._level
          let j = i

          // 找到他的上级并设置显示
          while (l > 0 && j-- >= index) {
            const node = list[j]
            if (node._level < l) {
              node._hide = false
              node._expand = true
              l--
            }
          }

          index = i
        } else {
          if (options.hideOther) {
            n._hide = true
          }
        }
      })
    },

    async reload() {
      if (this.query) {
        this.isLoading = true
        this.trees = await this.query()
        this.onUpdate()
        this.isLoading = false
      }
    },
  },

  watch: {
    values: {
      handler(values) {
        this.trees = values
        this.onUpdate()
      },
      immediate: true,
    },
  },
}
</script>
