export default {
  data() {
    return {
      data: {},
    }
  },

  provide() {
    return {
      operator: this,
    }
  },

  props: {
    value: {
      type: Object,
      default: function() {
        return {}
      },
    },
  },

  methods: {
    initialize() {
      this.data = {}
    },

    onClose() {
      this.$emit('success', this.data)
    },

    onCancel() {
      this.$emit('cancel')
    }
  },

  created() {
    this.initialize()
  },
}
