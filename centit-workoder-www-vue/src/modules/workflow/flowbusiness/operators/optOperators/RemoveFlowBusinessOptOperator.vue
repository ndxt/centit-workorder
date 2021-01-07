<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除流程节点业务"
    :content="content"
  />
</template>

<script>
  import {removeOptPage,} from '@/api/workflow/flowBusiness'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveFlowBusinessOptOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.optName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeOptPage(this.value.optCode)
    },
  },
}
</script>
