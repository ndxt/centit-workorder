export default {
  data() {
    return {
      renderValues: [],

      isLoading: false,
    }
  },

  props: {
    values: Array,

    query: Function,
  },

  methods: {
    async reload() {
      if (this.query) {
        this.isLoading = true
        this.renderValues = await this.query() || this.renderValues
        this.isLoading = false
      }
    },
  },

  watch: {
    values: {
      handler(values) {
        if (values) {
          this.renderValues = values
        }
      },
      immediate: true
    },
  },

  created() {
    this.reload()
  },
}
