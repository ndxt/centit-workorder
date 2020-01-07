<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除参数明细"
    :content="content"
  />
</template>

<script>
import {
  removeOptPage,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveInterfaceOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.businessCode}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeOptPage(this.value.parameterId)
    },
  },
}
</script>
