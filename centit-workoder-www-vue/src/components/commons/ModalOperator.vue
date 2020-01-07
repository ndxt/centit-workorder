<template>
  <Button
    v-bind:is="fab ? 'FAB' : 'Button'"
    :label="label"
    :disabled="disabled"
    :type="type"
    size="small"
    :ghost="ghost"
    @click.native.stop="open()"
    style="width: min-content;"
  >
    <Icon :type="iconType" :size="iconSize" :color="iconColor" />{{text}}
    <div v-if="renderModal">
      <Modal
        :width="width"
        :title="title"
        :loading="submitLoading"
        :footerHide="footerHide"
        v-model="showModal"
        @on-cancel="cancel"
        @on-ok="ok"
        :draggable="draggable"
        :class-name="className"
        :mask="mask"
        :mask-closable="false"
        :styles="styles"
      >
        <slot></slot>

        <div slot="footer" v-if="customButton || false">
          <slot name="customBtn"></slot>
        </div>

        <Spin size="large" fix v-if="resolving" />
      </Modal>
    </div>
  </Button>
</template>

<script>
export default {
  name: 'ModalOperator',

  data() {
    return {
      renderModal: false,
      showModal: false,
      resolving: false,
      submitLoading: true,
    }
  },

  props: {
    ghost: {
      type: Boolean,
      default: true
    },
    icon: String,
    type: {
      type: String,
      default: 'success',
    },
    iconType: String,
    iconSize: {
      type: String,
      default: '18'
    },
    draggable: {
      type: Boolean,
      default: false,
    },
    className: String,
    mask: {
      type: Boolean,
      default: true,
    },
    styles: Object,
    iconColor: String,
    customButton: Boolean,
    width: {
      type: [Number, String],
      default: '600',
    },
    title: String,
    label: String,
    text: String,
    fab: Boolean,
    disabled: Boolean,
    footerHide: Boolean,
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
      this.renderModal = true
      this.execute('initialize')
      this.resolving = true

      this.$nextTick(() => {
        this.showModal = true
      })

      return this.$nextTick(async () => {
        await this.execute('beforeOpen')
        this.resolving = false
      })
    },

    close() {
      this.showModal = false
      this.execute('onClose')
    },

    cancelLoading() {
      this.submitLoading = false
      this.$nextTick(() => {
        this.submitLoading = true
      })
    },

    cancel() {
      this.submitLoading = true
      this.execute('onCancel')
    },

    async ok() {
      // 提交前的检查
      const result = await this.execute('beforeSubmit')
      if (!result) {
        return this.cancelLoading()
      }

      try {
        // 提交
        await this.execute('onSubmit')
      } catch (e) {
        this.$Message.error({
          content: e.toString(),
          duration: 4,
          closable: true,
        })
        return this.cancelLoading()
      }

      this.close()
    },
  },

  watch: {
    showModal(show) {
      if (!show) {
        this.$nextTick(() => {
          this.renderModal = false
        })
      }
    },
  },
}
</script>

<style>
  .large .ivu-modal-content,
  .middle .ivu-modal-content,
  .small .ivu-modal-content {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 200px;
    overflow: auto;
  }

  .small .ivu-modal-content {
    left: 60px;
  }

  .gray{
    border-color: gray;
    color: gray;
  }
</style>
