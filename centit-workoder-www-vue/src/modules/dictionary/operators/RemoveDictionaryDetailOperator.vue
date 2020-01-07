<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveDictionaryDetailOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  inject: ['top'],

  computed: {
    content() {
      return `确认删除数据字典明细 [${this.value.dataCode} ：${this.value.dataValue}] 吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      // 管理远可以删除 S
      if (this.top.admin) {
        return value.dataStyle === 'F'
      }

      // 固定和系统类型不能删除
      return ['F', 'S'].includes(value.dataStyle)
    },
  },
}
</script>
