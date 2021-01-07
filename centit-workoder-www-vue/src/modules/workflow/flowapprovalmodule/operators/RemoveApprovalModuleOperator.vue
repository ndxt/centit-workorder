<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除通用审批模块"
    :content="content"
  />
</template>

<script>
  import {removeApprovalModule,} from '@/api/workflow/flowApprovalModule'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveApprovalModuleOperator',

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
      return removeApprovalModule(this.value.moduleCode)
    },
  },
}
</script>
