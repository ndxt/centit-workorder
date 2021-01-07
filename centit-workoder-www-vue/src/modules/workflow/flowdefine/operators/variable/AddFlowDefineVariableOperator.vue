<template>
  <ModalOperator fab title="新增流程变量">
    <zpa-form ref="Form">
      <FlowDefineVariableBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowDefineVariable,} from '@/api/workflow/flowDefine'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowDefineVariableBaseInfo from './FlowDefineVariableBaseInfo'

  export default {
  name: 'AddFlowDefineVariableOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowDefineVariableBaseInfo,
  },

  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      this.value.flowVariableDefines.push(this.data)

      const {
        flowCode,
        flowVariableDefines,
      } = this.value

      return addFlowDefineVariable({
        flowCode,
        flowVariableDefines,
      })
    },
  },
}
</script>
