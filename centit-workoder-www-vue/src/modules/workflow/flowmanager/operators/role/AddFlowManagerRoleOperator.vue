<template>
  <ModalOperator fab title="新增办件角色">
    <zpa-form ref="Form">
      <FlowManagerRoleBaseInfo v-model="data" :flowCode="value.flowCode"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowManagerRole,} from '@/api/workflow/flowManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowManagerRoleBaseInfo from './FlowManagerRoleBaseInfo'

  export default {
  name: 'AddFlowManagerRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowManagerRoleBaseInfo,
  },

  methods: {
    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      return addFlowManagerRole(this.value.flowInstId, this.data)
    },
  },
}
</script>
