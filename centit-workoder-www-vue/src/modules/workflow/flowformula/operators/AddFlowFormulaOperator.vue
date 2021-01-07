<template>
  <ModalOperator fab title="新增权限表达式" width="1000">
    <zpa-row>
      <zpa-form ref="Form" style="width: 50%;">
        <FlowFormulaInfo v-model="data" @viewUser="viewUser"/>
      </zpa-form>

      <FlowFormulaExplan style="width: 50%;"/>
    </zpa-row>

    <Divider size="small" orientation="left" style="margin: 0;">
      <Button
        ghost
        type="primary"
        size="small"
        :disabled="!data.roleFormula"
        @click="viewUser"
      >对应用户预览
      </Button>
    </Divider>
    <FlowFormulaUser ref="User" :formula="data.roleFormula"/>
  </ModalOperator>
</template>

<script>
  import {addFlowFormula,} from '@/api/workflow/flowFormula'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowFormulaInfo from './FlowFormulaInfo'
  import FlowFormulaExplan from './FlowFormulaExplan'
  import FlowFormulaUser from './FlowFormulaUser'

  export default {
  name: 'AddFlowFormulaOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowFormulaInfo,
    FlowFormulaExplan,
    FlowFormulaUser,
  },

  methods: {
    viewUser() {
      this.$refs.User.reload()
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addFlowFormula(this.data)
    },
  },
}
</script>
