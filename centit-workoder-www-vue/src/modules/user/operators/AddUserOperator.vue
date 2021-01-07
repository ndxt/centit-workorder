<template>
  <ModalOperator fab title="新增用户">
    <zpa-form ref="Form">
      <UserBaseInfo v-model="data" />
      <!--<UserFieldInfo v-model="data" />-->
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addUser} from '@/api/admin/user'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import UserBaseInfo from '../UserBaseInfo'
  /* import DictionaryFieldInfo from '../UserFieldInfo' */

export default {
  name: 'AddUserOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    UserBaseInfo,
    /*  UserFieldInfo, */
  },

  methods: {
    initialize() {
      this.data = {
        _new: true,
        needCache: 1,
        userInfo: {
          userOrder: 1,
          isValid: 'T',
        },
        userUnit: {},

      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      const _data = Object.assign({}, this.data.userInfo, this.data.userUnit)
      return addUser(_data)
    },
  },
}
</script>
