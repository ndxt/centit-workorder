<template>
  <ConfirmOperator
    :type="modalParam.type"
    :text="modalParam.text"
    :title="modalParam.title"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  pauseFlowManager,
  wakeFlowManager,
} from '@/api/workflow/flowManager'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'PauseWakeFlowManagerOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    modalParam() {
      if (this.value.instState === 'N') {
        return {
          type: 'warning',
          text: '暂停',
          title: `暂停 [${this.value.flowOptName}]`,
        }
      } else {
        return {
          type: 'success',
          text: '唤醒',
          title: `唤醒 [${this.value.flowOptName}]`,
        }
      }
    },

    content() {
      return `确定${this.modalParam.text}吗？`
    },

    disabled() {
      return ['F', 'C', 'M'].includes(this.value.instState)
    },
  },

  methods: {
    onSubmit() {
      if (this.value.instState === 'N') {
        return pauseFlowManager(this.value)
      } else {
        return wakeFlowManager(this.value)
      }
    },
  },
}
</script>
