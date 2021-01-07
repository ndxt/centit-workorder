<template>
  <ModalOperator fab title="新增流程机构">
    <zpa-form ref="Form">
      <FlowManagerUnitBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowManagerUnit,} from '@/api/workflow/flowManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowManagerUnitBaseInfo from './FlowManagerUnitBaseInfo'

  export default {
  name: 'AddFlowManagerUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowManagerUnitBaseInfo,
  },

  methods: {
    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      return addFlowManagerUnit(this.value.flowInstId, this.data)
    },
  },
}
</script>
