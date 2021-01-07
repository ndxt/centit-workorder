<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除流程机构"
    :content="content"
  />
</template>

<script>
  import {removeFlowManagerUnit,} from '@/api/workflow/flowManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveFlowManagerUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    flowInstId: String,
  },

  computed: {
    content() {
      return `确认删除流程机构[${this.value.roleCode}]吗？`
    },
  },

  methods: {
    onSubmit() {
      removeFlowManagerUnit(this.flowInstId, this.value)
    },
  },
}
</script>
