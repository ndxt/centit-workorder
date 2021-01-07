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
    <i-switch
      :value="switchValue"
      :readonly="readonly"
      :disabled="disabled"
      :loading="loading"
      @on-change="onInputChange"
    >
      <template slot="open" v-if="$slots.open">
        <slot name="open"></slot>
      </template>
      <template slot="close" v-if="$slots.close">
        <slot name="close"></slot>
      </template>
    </i-switch>
  </zpa-form-item>
</template>

<script>
  import inputMixin from './mixins/input'

  export default {
  name: 'zpaSwitch',

  mixins: [inputMixin],

  data() {
    return {
      switchValue: false,
    }
  },

  props: {
    value: [String, Number, Boolean],

    trueValue: [String, Number],

    falseValue: [String, Number],

    loading: Boolean,
  },

  watch: {
    value: {
      handler(val) {
        if (val === undefined) {
          this.switchValue = false
          return
        }

        if (this.trueValue !== undefined && this.trueValue === val) {
          this.switchValue = true
        } else if (this.falseValue !== undefined && this.falseValue === val) {
          this.switchValue = false
        } else {
          this.switchValue = !!val
        }
      },
      immediate: true,
    },
  },

  methods: {
    onInputChange(value) {
      if (this.trueValue !== undefined && value === true) {
        value = this.trueValue
      } else if (this.falseValue !== undefined && value === false) {
        value = this.falseValue
      }

      this.$emit('input', value)

      this.debounceValidate()
    },
  },
}
</script>
