<template>
  <ConfirmOperator
    type="error"
    text="强制提交"
    :title="title"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  submitFlowManagerNode,
} from '@/api/workflow/flowManager'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'SubmitFlowManagerNodeOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    instState: String,
  },

  computed: {
    title() {
      return `强制提交 [${this.value.nodeName}]`
    },

    content() {
      return '确定强制提交吗？'
    },

    disabled() {
      if (this.value.nodeState === 'N' && !['F', 'C'].includes(this.instState)) {
        return false
      }

      return true
    },
  },

  methods: {
    onSubmit() {
      return submitFlowManagerNode(this.value.nodeInstId)
    },
  },
}
</script>
