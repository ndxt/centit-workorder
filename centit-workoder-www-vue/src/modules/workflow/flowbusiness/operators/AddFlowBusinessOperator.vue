<template>
  <ModalOperator fab title="新建业务流程">
    <zpa-form ref="Form">
      <FlowBusinessBaseInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  // getNewOptId,
  addOptInfo,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import FlowBusinessBaseInfo from './FlowBusinessBaseInfo'

export default {
  name: 'AddFlowBusinessOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowBusinessBaseInfo,
  },

  methods: {
    async beforeOpen() {
      // this.data = await getNewOptId()
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addOptInfo(this.data)
    },
  },
}
</script>
