<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除材料"
    :content="content"
  />
</template>

<script>
import {
  removeFlowStuff,
} from '@/api/workflow/flowStuffManager'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowStuffOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.stuffName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeFlowStuff(this.value.stuffCode)
    },
  },
}
</script>
