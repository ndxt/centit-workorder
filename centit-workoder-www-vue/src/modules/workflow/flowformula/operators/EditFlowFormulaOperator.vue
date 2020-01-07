<template>
  <ModalOperator type="info" text="编辑" title="编辑权限表达式" width="1000">
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
import {
  getFlowFormula,
  saveFlowFormula,
} from '@/api/workflow/flowFormula'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import FlowFormulaInfo from './FlowFormulaInfo'
import FlowFormulaExplan from './FlowFormulaExplan'
import FlowFormulaUser from './FlowFormulaUser'

export default {
  name: 'EditFlowFormulaOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowFormulaInfo,
    FlowFormulaExplan,
    FlowFormulaUser,
  },

  methods: {
    async beforeOpen() {
      this.data = await getFlowFormula(this.value)
      this.$nextTick(() => {
        this.viewUser()
      })
    },

    viewUser() {
      this.$refs.User.reload()
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return saveFlowFormula(this.data)
    },
  },
}
</script>
