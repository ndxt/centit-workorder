<template>
  <ConfirmOperator
    type="warning"
    text="发布"
    title="发布"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
  import {publishFlow} from '@/api/workflow/flowDefine'
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'PublishFlowDefineOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认发布流程[${this.value ? this.value.flowName : ''}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value || value.flowState !== 'A') {
        return true
      }

      return false
    },
  },
  methods: {
    onSubmit() {
      return publishFlow(this.value)
    },
  },
}
</script>
