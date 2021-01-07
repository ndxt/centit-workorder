<template>
  <ModalOperator fab title="新增流程图">
    <zpa-form ref="Form">
      <FlowDefineBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowDefine, createNewFlow,} from '@/api/workflow/flowDefine'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowDefineBaseInfo from '../FlowDefineBaseInfo'

  export default {
  name: 'AddFlowDefineOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowDefineBaseInfo,
  },

  methods: {
    async initialize() {
      this.data = await createNewFlow()
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addFlowDefine(this.data)
    },
  },
}
</script>
