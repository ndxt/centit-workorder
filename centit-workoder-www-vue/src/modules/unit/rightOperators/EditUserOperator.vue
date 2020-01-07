<template>
  <ModalOperator text="编辑" title="修改机构人员">
    <zpa-form ref="Form">
      <UserBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import UserBaseInfo from '../right/UserBaseInfo'
import {
  editUnitUser
} from '@/api/admin/unit'

export default {
  name: 'EditUserOperator',

  components: {
    UserBaseInfo,
  },

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    beforeOpen() {
      this.data = this.value
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return editUnitUser(this.data)
    },
  }
}
</script>
