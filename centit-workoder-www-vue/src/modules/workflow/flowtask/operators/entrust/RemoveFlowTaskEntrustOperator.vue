<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除任务委托"
    :content="content"
  />
</template>

<script>
import {
  removeFlowTaskEntrust,
} from '@/api/workflow/flowTask'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowTaskEntrustOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.relegateNo}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeFlowTaskEntrust(this.value)
    },
  },
}
</script>
