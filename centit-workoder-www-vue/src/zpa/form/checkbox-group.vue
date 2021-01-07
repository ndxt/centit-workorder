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
    <Spin v-if="isLoading"></Spin>
    <CheckboxGroup
      style="height: 32px;"
      class="zpa-row middle"
      v-if="!isLoading"
      :value="renderValue"
      :readonly="readonly"
      :disabled="disabled"
      @on-change="onInputChange"
    >
      <Checkbox
        v-for="v in renderValues"
        :key="getValue(v)"
        :label="getValue(v)"
      >{{getText(v)}}</Checkbox>
    </CheckboxGroup>
  </zpa-form-item>
</template>

<script>
  import inputMixin from './mixins/input'
  import queryMixin from './mixins/query'
  import valueTextMixin from './mixins/value-text'
  import stringifyMixin from './mixins/stringify'

  export default {
  name: 'zpaCheckboxGroup',

  mixins: [
    inputMixin,
    queryMixin,
    valueTextMixin,
    stringifyMixin,
  ],

  props: {
    value: [String, Array],

    values: Array,
  },
}
</script>
