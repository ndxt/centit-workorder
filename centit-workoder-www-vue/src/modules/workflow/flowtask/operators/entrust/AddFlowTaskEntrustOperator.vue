<template>
  <ModalOperator fab title="新增委托">
    <zpa-form ref="Form">
      <FlowTaskEntrustBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  addFlowTaskEntrust,
} from '@/api/workflow/flowTask'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import FlowTaskEntrustBaseInfo from './FlowTaskEntrustBaseInfo'

export default {
  name: 'AddFlowTaskEntrustOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowTaskEntrustBaseInfo,
  },

  methods: {
    initialize() {
      this.data = Object.assign({}, {
        grantor: this.value.userCode,
        roleType: 'XZ',
      })
    },

    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      return addFlowTaskEntrust(this.data)
    },
  },
}
</script>
