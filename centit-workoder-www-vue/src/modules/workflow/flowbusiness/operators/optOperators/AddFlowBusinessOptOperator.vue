<template>
  <ModalOperator fab title="新增流程节点业务">
    <zpa-form ref="Form">
      <FlowBusinessOptBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addOptPage,} from '@/api/workflow/flowBusiness'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowBusinessOptBaseInfo from './FlowBusinessOptBaseInfo'

  export default {
  name: 'AddFlowBusinessOptOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowBusinessOptBaseInfo,
  },

  methods: {
    async beforeOpen() {
      // this.data = await newOptCode()
      this.data.optReq = 'R'
      this.data.optId = this.value.optId
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
