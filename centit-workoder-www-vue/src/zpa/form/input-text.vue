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
    <i-input
      :value="value"
      :placeholder="placeholder || label"
      :readonly="readonly"
      :disabled="disabled"
      @on-change="onInputChange"
    >
      <Icon v-if="type === 'search'" type="ios-search" slot="suffix"></Icon>
      <Icon v-else-if="type === 'email'" type="ios-mail" slot="suffix"></Icon>
      <Icon v-else-if="type === 'phone'" type="ios-call" slot="suffix"></Icon>
      <Icon v-else-if="type === 'url'" type="ios-globe" slot="suffix"></Icon>
    </i-input>
  </zpa-form-item>
</template>

<script>
import inputMixin from './mixins/input'

export default {
  name: 'zpaTextInput',

  mixins: [inputMixin],

  props: {
    type: String,
  },
}
</script>
