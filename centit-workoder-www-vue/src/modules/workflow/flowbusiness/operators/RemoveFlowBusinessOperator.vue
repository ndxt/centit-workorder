<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除流程业务"
    :content="content"
  />
</template>

<script>
import {
  removeOptInfo,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowBusinessOperator',

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
      return removeOptInfo(this.value.optId)
    },
  },
}
</script>
