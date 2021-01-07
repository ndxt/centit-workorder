<template>
  <ModalOperator fab title="新增材料" width="1000">
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <FlowStuffInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowStuff,} from '@/api/workflow/flowStuffManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowStuffInfo from './FlowStuffInfo'

  export default {
  name: 'AddApprovalModuleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      data: {stuffJson: {stuffGroups: []}},
    }
  },

  components: {
    FlowStuffInfo,
  },

  methods: {
    initialize() {
      this.data = {stuffJson: {stuffGroups: []}}
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addFlowStuff(this.data)
    },
  },
}
</script>
