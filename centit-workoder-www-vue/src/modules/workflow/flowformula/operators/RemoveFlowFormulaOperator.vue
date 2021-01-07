<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除权限表达式"
    :content="content"
  />
</template>

<script>
  import {removeFlowFormula,} from '@/api/workflow/flowFormula'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveFlowFormulaOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.formulaName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeFlowFormula(this.value)
    },
  },
}
</script>
