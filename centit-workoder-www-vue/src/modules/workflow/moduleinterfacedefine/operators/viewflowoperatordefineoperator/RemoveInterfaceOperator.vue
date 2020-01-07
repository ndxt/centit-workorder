<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除接口明细"
    :content="content"
  />
</template>

<script>
import {
  removeInterFacesList,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveInterfaceOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.interfaceCode}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeInterFacesList(this.value.interfaceCode)
    },
  },
}
</script>
