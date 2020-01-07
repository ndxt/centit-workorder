<template>
  <ModalOperator fab title="新增机构角色">
    <zpa-form ref="Form">
      <RoleBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import RoleBaseInfo from '../right/RoleBaseInfo'
import {
  addUnitRole
} from '@/api/admin/unit'

export default {
  name: 'AddRoleOperator',

  components: {
    RoleBaseInfo,
  },

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    initialize() {
      this.data = {
        unitName: this.value.unitName,
        unitCode: this.value.unitCode,
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addUnitRole(this.data)
    },
  }
}
</script>
