<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
  />
</template>

<script>
  import {removeQuestion,} from '@/api/workorder'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveOrderOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除工单${this.value.questionTitle}吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeQuestion({
        osId: 'N002',
        id: this.value.questionId
      })
    },
  },
}
</script>
