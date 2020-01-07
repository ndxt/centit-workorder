<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除模块"
    :content="content"
  />
</template>

<script>
import {
  removeModuleByCode,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveModuleInterfaceConfig',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除[${this.value.moduleName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      return removeModuleByCode(this.value.moduleCode)
    },
  },
}
</script>
