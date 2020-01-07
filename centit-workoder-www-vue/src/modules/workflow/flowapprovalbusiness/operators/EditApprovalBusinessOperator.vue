<template>
  <ModalOperator type="info" text="编辑" title="编辑业务表单">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="表单名称"
          v-model="data.optFormId"
          :max="20"
          required
        />
        <zpa-text-input
          label="表单地址"
          v-model="data.optFormUrl"
          :max="1000"
          required
        />
        <zpa-switch
          label="是否可编辑"
          v-model="data.isEdit"
          true-value="Y"
          false-value="N"
        />
        <zpa-text-input
          v-if="data.isEdit === 'Y'"
          label="提交url"
          v-model="data.optSubmitUrl"
          :max="1000"
          :required="data.isEdit==='Y'?true:false"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  addApprovalBusiness,
  getApprovalBusiness,
} from '@/api/workflow/flowApprovalBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'EditApprovalBusinessOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      this.data = await getApprovalBusiness(this.value.id)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addApprovalBusiness(this.data)
    },
  },
}
</script>
