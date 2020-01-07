<template>
  <ModalOperator type="info" text="编辑" title="编辑流程节点业务">
    <zpa-form ref="Form">
      <FlowBusinessOptBaseInfo v-model="data" isEdit/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  addOptPage,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import FlowBusinessOptBaseInfo from './FlowBusinessOptBaseInfo'

export default {
  name: 'EditFlowBusinessOptOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowBusinessOptBaseInfo,
  },

  props: {
    isEdit: Boolean,
    obj: Object,
  },

  methods: {
    beforeOpen() {
      this.data = this.value
      this.data.optReq = 'R'
      this.data.optId = this.obj.optId
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      // const optDefs = [this.data]
      //
      // return addOptPage({
      //   optDefs: optDefs,
      //   optName: '',
      // })
      return addOptPage(this.data)
    },
  },
}
</script>
