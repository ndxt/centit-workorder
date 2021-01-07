<template>
  <ModalOperator fab title="新增业务表单">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="表单名称"
          v-model="data.optFormId"
          :max="50"
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
          label="提交地址"
          v-model="data.optSubmitUrl"
          :max="1000"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addApprovalBusiness,} from '@/api/workflow/flowApprovalBusiness'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'AddApprovalBusinessOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addApprovalBusiness(this.data)
    },
  },

  created() {
    window['getData'] = () => {
      return this.data
    }
  },
}
</script>
