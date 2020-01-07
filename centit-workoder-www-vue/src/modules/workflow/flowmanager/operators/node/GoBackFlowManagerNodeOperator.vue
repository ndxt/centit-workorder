<template>
  <ConfirmOperator
    type="warning"
    text="回退"
    :title="title"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  goBackFlowManagerNode,
} from '@/api/workflow/flowManager'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'GoBackFlowManagerNodeOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    instState: String,
  },

  computed: {
    title() {
      return `回退 [${this.value.nodeName}]`
    },

    content() {
      return '确定回退吗？'
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
      return goBackFlowManagerNode(this.value.nodeInstId)
    },
  },
}
</script>
