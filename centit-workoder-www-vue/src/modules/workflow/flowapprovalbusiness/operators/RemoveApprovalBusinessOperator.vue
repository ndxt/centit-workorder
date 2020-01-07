<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除业务表单"
    :content="content"
  />
</template>

<script>
import {
  removeApprovalBusiness,
} from '@/api/workflow/flowApprovalBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveApprovalBusinessOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.optFormId}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeApprovalBusiness(this.value.id)
    },
  },
}
</script>
