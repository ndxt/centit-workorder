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
import {
  removeDictionary,
} from '@/api/admin/dictionary'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveDictionaryOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  inject: ['top'],

  computed: {
    content() {
      return `确认删除数据字典[${this.value.catalogName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      // 管理远可以删除 S
      if (this.top.admin) {
        return value.catalogStyle === 'F'
      }

      // 固定和系统类型不能删除
      return ['F', 'S'].includes(value.catalogStyle)
    },
  },

  methods: {
    onSubmit() {
      return removeDictionary(this.value.catalogCode)
    },
  },
}
</script>
