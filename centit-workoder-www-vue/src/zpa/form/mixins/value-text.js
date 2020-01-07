export default {
  props: {
    valueField: {
      type: String,
      default: 'value',
    },

    textField: {
      type: String,
      default: 'text',
    },
  },

  methods: {
    getValue(obj) {
      if (obj === undefined) return
      const value = obj[this.valueField]

      return value === undefined ? obj : value
    },

    getText(obj) {
      if (obj === undefined) return
      const text = obj[this.textField]

      return text === undefined ? obj : text
    },
  },
}
