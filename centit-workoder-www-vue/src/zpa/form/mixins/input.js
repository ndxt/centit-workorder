import _ from 'lodash'

import {
  getUuid,
} from '../../utils'

import {
  validate,
  parseValidators2Rules,
  getRules,
} from '../validator'

export const layoutProps = {
  span: Number,

  push: Number,
}

export const validateProps = {
  required: Boolean,

  readonly: Boolean,

  disabled: Boolean,

  len: Number,

  max: Number,

  min: Number,

  pattern: RegExp,

  validators: [RegExp, String, Object, Array, Function],

  validatorsMessage: String,
}

export const labelProps = {
  label: String,

  labelWidth: Number,

  labelAlign: String,

  itemHeight: Number,

  placeholder: String,
}

export default {
  data() {
    return {
      error: '',

      success: false,

      validating: false,

      initialValue: undefined,
    }
  },

  inject: ['form'],

  props: {
    ...layoutProps,

    ...validateProps,

    ...labelProps,

    id: String,

    value: [String, Number],
  },

  computed: {
    uuid() {
      return this.id || `input_${getUuid()}`
    },

    rules() {
      const label = this.label
      const rules = parseValidators2Rules(this.validators, this.validatorsMessage)
      const type = this.type

      return getRules.call(this, {label, type, rules})
    },
  },

  methods: {
    validate,

    debounceValidate: _.debounce(validate, 120),

    reset() {
      this.error = ''
      this.success = false
      this.validating = false
      this.$emit('input', this.initialValue)
    },

    onInputChange(value) {
      this._initialValue = true
      this.$emit('input', !value ? undefined : value.target ? value.target.value : value)
      this.debounceValidate()
    },
    onInputFocus(value) {
      this._initialValue = true
      this.$emit('focus', !value ? undefined : value.target ? value.target.value : value)
      this.debounceValidate()
    },
  },

  watch: {
    value: {
      handler(val) {
        if (!this._initialValue && val) {
          this.initialValue = val
          this._initialValue = true
        }
      },
      immediate: true,
    },
  },

  mounted() {
    if (!this.form) return

    this.form.addField(this)
  },

  beforeDestroy() {
    if (!this.form) return

    this.form.removeField(this)
  },
}
