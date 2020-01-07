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
    <RadioGroup
      style="height: 32px;"
      class="zpa-row middle"
      v-if="!isLoading"
      :value="value"
      :readonly="readonly"
      :disabled="disabled"
      :type="isButton"
      @on-change="onInputChange"
    >
      <Radio
        v-for="v in renderValues"
        :key="getValue(v)"
        :label="getValue(v)"
      >{{getText(v)}}</Radio>
    </RadioGroup>
  </zpa-form-item>
</template>

<script>
import inputMixin from './mixins/input'
import queryMixin from './mixins/query'
import valueTextMixin from './mixins/value-text'

export default {
  name: 'zpaRadioGroup',

  mixins: [
    inputMixin,
    queryMixin,
    valueTextMixin,
  ],

  props: {
    value: [String, Number],

    values: Array,

    button: Boolean,
  },

  computed: {
    isButton() {
      return this.button ? 'button' : undefined
    }
  },
}
</script>
