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
    <DatePicker
      style="width: 100%;border: 1px solid #dcdee2"
      :value="value"
      :placeholder="label"
      :editable="false"
      :readonly="readonly"
      :disabled="disabled"
      @on-change="onInputChange"
    />
  </zpa-form-item>
</template>

<script>
import inputMixin from './mixins/input'

export default {
  name: 'zpaDateInput',

  mixins: [inputMixin],
}
</script>
