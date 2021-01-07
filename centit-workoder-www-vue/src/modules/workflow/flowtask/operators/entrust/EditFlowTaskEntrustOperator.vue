<template>
  <ModalOperator text="修改" title="修改委托">
    <zpa-form ref="Form">
      <FlowTaskEntrustBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {saveFlowTaskEntrust,} from '@/api/workflow/flowTask'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowTaskEntrustBaseInfo from './FlowTaskEntrustBaseInfo'

  export default {
  name: 'EditFlowTaskEntrustOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowTaskEntrustBaseInfo,
  },

  methods: {
    beforeOpen() {
      this.data = Object.assign({
        roleType: 'XZ',
      }, this.value)
    },

    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      return saveFlowTaskEntrust(this.data)
    },
  },
}
</script>
