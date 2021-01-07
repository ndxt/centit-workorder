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
    <InputNumber
      ref="input"
      style="width: 100%; height: auto;"
      editable
      :max="maxLength"
      :min="minLength"
      :value="value"
      :placeholder="label"
      :readonly="readonly"
      :disabled="disabled"
      @on-change="onInputChange"
    />
  </zpa-form-item>
</template>

<script>
  import inputMixin from './mixins/input'

  export default {
  name: 'zpaNumberInput',

  mixins: [inputMixin],

  data() {
    return {
      type: 'number',
    }
  },

  mounted() {
    this.$refs['input'].$refs['precisionCursor'].maxLength = this.maxLength.toString().length
  },

  props: {
    value: {
      type: [String, Number],
      default: null,
    },
    maxLength: {
      type: Number,
      default: 999999999999999,
    },
    minLength: {
      type: Number,
      default: 0
    },
  },
}
</script>
