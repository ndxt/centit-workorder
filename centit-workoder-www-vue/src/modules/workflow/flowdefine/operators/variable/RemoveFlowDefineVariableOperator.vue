<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
    :disabled="!this.value"
  />
</template>

<script>
import {
  addFlowDefineVariable,
} from '@/api/workflow/flowDefine'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveFlowDefineVariableOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    obj: Object,
  },

  computed: {
    content() {
      return `确认删除变量[${this.value.variableName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      if (this.obj.flowVariableDefines && this.obj.flowVariableDefines.length) {
        this.obj.flowVariableDefines = this.obj.flowVariableDefines.filter(d => {
          return this.value.flowVariableId !== d.flowVariableId
        })
      }

      const {
        flowCode,
        flowVariableDefines,
      } = this.obj

      return addFlowDefineVariable({
        flowCode,
        flowVariableDefines,
      })
    },
  },
}
</script>
