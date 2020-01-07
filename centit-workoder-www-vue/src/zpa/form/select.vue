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
      :loading="isLoading"
      @on-change="onInputChange"
    >
      <i-option
        v-for="(v, i) in renderValues"
        :key="i"
        :value="getValue(v)"
        :label="getText(v)"
      >
        <slot :row="v" :index="i">
          {{getText(v)}}{{annex ? `（${v[annex]}）` : ''}}
        </slot>
      </i-option>
    </i-select>
  </zpa-form-item>
</template>

<script>
import inputMixin from './mixins/input'
import queryMixin from './mixins/query'
import valueTextMixin from './mixins/value-text'
import stringifyMixin from './mixins/stringify'

export default {
  name: 'zpaSelect',

  mixins: [
    inputMixin,
    queryMixin,
    valueTextMixin,
    stringifyMixin,
  ],

  computed: {
    type() {
      return this.multiple ? 'array' : 'string'
    },
  },

  props: {
    value: [String, Array, Number],

    values: Array,

    multiple: Boolean,

    clearable: Boolean,

    filterable: Boolean,

    annex: String, // 附加显示在每项后面内容的key
  },
}
</script>
