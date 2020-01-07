<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除流程定义"
    :content="content"
  />
</template>

<script>
import {
  deleteFlow,
} from '@/api/workflow/flowDefine'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowDefineOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除流程[${this.value ? this.value.flowName : ''}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return deleteFlow(this.value)
    },
  },
}
</script>
