<template>
  <ModalOperator fab title="新增流程变量">
    <zpa-form ref="Form">
      <FlowManagerVariableBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowManagerVariable,} from '@/api/workflow/flowManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowManagerVariableBaseInfo from './FlowManagerVariableBaseInfo'

  export default {
  name: 'AddFlowManagerVariableOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowManagerVariableBaseInfo,
  },

  methods: {
    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      return addFlowManagerVariable(this.value.flowInstId, this.data)
    },
  },
}
</script>
