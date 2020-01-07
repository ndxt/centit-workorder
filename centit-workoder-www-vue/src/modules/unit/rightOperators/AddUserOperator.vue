<template>
  <ModalOperator fab title="新增机构人员">
    <zpa-form ref="Form">
      <UserBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import UserBaseInfo from '../right/UserBaseInfo'
import {
  addUnitUser
} from '@/api/admin/unit'

export default {
  name: 'AddUserOperator',

  components: {
    UserBaseInfo,
  },

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    initialize() {
      this.data = Object.assign({
        _new: true,
        isPrimary: 'F',
      }, this.value)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addUnitUser(this.data)
    },
  }
}
</script>
