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
  import {restartFlowManager, stopFlowManager,} from '@/api/workflow/flowManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'StopRestartFlowManagerOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    modalParam() {
      if (this.value.instState === 'N') {
        return {
          type: 'error',
          text: '强制结束',
          title: `强制结束 [${this.value.flowOptName}]`,
        }
      } else {
        return {
          type: 'success',
          text: '重新运行',
          title: `重新运行 [${this.value.flowOptName}]`,
        }
      }
    },

    content() {
      return `确定${this.modalParam.text}吗？`
    },

    disabled() {
      return ['F', 'C', 'P', 'M'].includes(this.value.instState)
    },
  },

  methods: {
    onSubmit() {
      if (this.value.instState === 'N') {
        return stopFlowManager(this.value)
      } else {
        return restartFlowManager(this.value)
      }
    },
  },
}
</script>
