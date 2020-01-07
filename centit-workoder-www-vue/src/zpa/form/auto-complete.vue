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
    <AutoComplete
      :value="value"
      :placeholder="placeholder || label"
      :clearable="clearable"
      :readonly="readonly"
      :disabled="disabled"
      :data="values"
      @on-change="onInputChange"
      @input="val => onSearch(val)"
      @on-search="onSearch"
      @on-select="onSelect"
    >
      <i-option
        v-for="(v, i) in renderValues"
        :key="i"
        :value="getValue(v)"
        :label="getText(v)"
      >
        <slot :row="v" :index="i">
          <zpa-highlight
            :value="v"
            :query="q"
          />
        </slot>
      </i-option>
    </AutoComplete>
  </zpa-form-item>
</template>

<script>
import inputMixin from './mixins/input'
import queryMixin from './mixins/query'
import valueTextMixin from './mixins/value-text'
import stringifyMixin from './mixins/stringify'

export default {
  name: 'zpaAutoComplete',

  mixins: [
    inputMixin,
    queryMixin,
    valueTextMixin,
    stringifyMixin,
  ],

  data() {
    return {
      q: '',
    }
  },

  props: {
    type: String,

    placeholder: String,

    values: {
      type: Array,
      default: () => [],
    },

    clearable: Boolean,
  },

  methods: {
    onSearch(q) {
      if (q) {
        q = q.replace(/\s\t\n/ig, '')
      }
      this.q = q
      this.$emit('on-search', q)
      this.reload(q)
    },

    onSelect(q) {
      this.$emit('on-select', q)
    },
  },
}
</script>
