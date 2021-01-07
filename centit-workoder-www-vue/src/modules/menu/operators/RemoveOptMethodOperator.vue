<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
  />
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveOptMethodOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除操作定义 [${this.value.optCode || ''} ：${this.value.optName}] 吗？`
    },
  },
}
</script>
