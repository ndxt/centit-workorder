<template>
  <ModalOperator fab title="新增流程阶段">
    <zpa-form ref="Form">
      <FlowDefineStageBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowDefineStage,} from '@/api/workflow/flowDefine'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowDefineStageBaseInfo from './FlowDefineStageBaseInfo'

  export default {
  name: 'AddFlowDefineOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowDefineStageBaseInfo,
  },

  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      this.value.flowStages.push(this.data)

      const {
        flowCode,
        flowStages,
      } = this.value

      return addFlowDefineStage({
        flowCode,
        flowStages,
      })
    },
  },
}
</script>
