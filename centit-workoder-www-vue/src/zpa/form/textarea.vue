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
    <Input
      type="textarea"
      :value="value"
      :rows="rows"
      :placeholder="placeholder || label"
      :readonly="readonly"
      :disabled="disabled"
      @on-change="onInputChange"
      @on-focus="onInputFocus"
    />
  </zpa-form-item>
</template>

<script>
  import inputMixin from './mixins/input'

  export default {
  name: 'zpaTextarea',

  mixins: [inputMixin],

  data() {
    return {
      type: 'string',
    }
  },

  props: {
    rows: {
      type: [Number, String],
      default: 4,
    }
  },
}
</script>
