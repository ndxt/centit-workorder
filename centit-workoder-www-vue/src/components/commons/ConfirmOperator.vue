<template>
  <Button :type="type" :disabled="disabled" style="width: min-content;"  :loading="resolving" :size="size" :ghost="ghost" @click.native.stop="open()">
    <Icon :type="iconType" :size="iconSize" :color="iconColor" /> {{text}}
  </Button>
</template>

<script>
export default {
  name: 'ModalOperator',

  data() {
    return {
      showModal: false,
      resolving: false,
      submitLoading: true,
    }
  },

  props: {
    title: String,
    text: String,
    content: String,
    type: {
      type: String,
      default: 'success',
    },
    size: {
      type: String,
      default: 'small',
    },
    iconType: String,
    iconSize: {
      type: String,
      default: '18',
    },
    iconColor: String,
    disabled: Boolean,
    ghost: {
      type: Boolean,
      default: true
    },
  },

  inject: ['operator'],

  methods: {
    /**
     * 执行注入 operator 的方法
     * @param method
     * @returns {Promise<*>}
     */
    async execute(method) {
      const operator = this.operator
      if (!operator || !(operator[method] instanceof Function)) return true
      return operator[method]()
    },

    async open() {
      const title = this.title
      const content = this.content
      const onOk = this.ok

      this.$Modal.confirm({
        title,
        content,
        loading: true,
        onOk,
      })
    },

    close() {
      this.$Modal.remove()
      this.execute('onClose')
    },

    cancel() {
      this.$Modal.remove()
      this.execute('onCancel')
    },

    async ok() {
      try {
        // 提交
        await this.execute('onSubmit')
      } catch (e) {
        this.$Message.error({
          content: e.toString(),
          duration: 4,
          closable: true,
        })
        return this.cancel()
      }

      this.close()
    },
  },
}
</script>
