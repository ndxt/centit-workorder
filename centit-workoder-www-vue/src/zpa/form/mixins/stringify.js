export default {
  data() {
    return {
      renderValue: [],
    }
  },

  props: {
    transform: Boolean,

    number: Boolean,

    getter: {
      type: Function,
      default: val => val ? val.split(',').map(n => this.number ? parseFloat(n) : n) : []
    },

    setter: {
      type: Function,
      default: val => val.join(',')
    },
  },

  methods: {
    onInputChange(value) {
      this._initialValue = true

      if (this.transform) {
        this.renderValue = value
        value = this.setter(value)
      }

      this.$emit('input', value)

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

        if (this.transform) {
          this.renderValue = this.getter(val).map(n => this.number ? parseFloat(n) : n)
        } else {
          this.renderValue = val
        }
      },
      immediate: true,
    },
  },
}
