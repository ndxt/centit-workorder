<template>
  <ConfirmOperator
    type="success"
    text="重新运行"
    title="重新运行"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  restartFlowManagerNode,
} from '@/api/workflow/flowManager'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RestartFlowManagerNodeOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    instState: String,
  },

  computed: {
    content() {
      return '确定重新运行吗？'
    },

    disabled() {
      if (this.value.nodeState === 'C' || this.instState === 'F') {
        return false
      }

      return true
    },
  },

  methods: {
    onSubmit() {
      return restartFlowManagerNode(this.value.nodeInstId)
    },
  },
}
</script>
