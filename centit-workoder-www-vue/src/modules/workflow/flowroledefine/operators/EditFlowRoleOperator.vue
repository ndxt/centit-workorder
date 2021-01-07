<template>
  <ModalOperator text="编辑角色" title="编辑角色" :width="600" :ghost="false">
    <zpa-form ref="Form">
      <zpa-form-group>
        <!--<zpa-number-input-->
          <!--label="角色编码"-->
          <!--v-model="data.roleCode"-->
          <!--:max="100"-->
          <!--required-->
        <!--/>-->
        <zpa-text-input
          label="角色名称"
          v-model="data.roleName"
          :max="100"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {editFlowRole} from '@/api/workflow/flowroledefine'
  import ZpaNumberInput from '../../../../zpa/form/input-number.vue';

  export default {
  components: {ZpaNumberInput},
  name: 'EditFlowRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      this.data = this.value
      console.log(this.data)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return editFlowRole(this.data)
    },
  },
}
</script>
