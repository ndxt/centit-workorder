<template>
  <ModalOperator text="编辑" title="编辑用户">
    <UserBaseInfo v-model="data" ref="editFrom"/>
  </ModalOperator>
</template>

<script>
  import {getUser, modifyUser,} from '@/api/admin/user'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import UserBaseInfo from '../UserBaseInfo'

  export default {
  name: 'EditUserOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      data: {
        userInfo: {},
        userUnit: {},
      },
    }
  },

  components: {
    UserBaseInfo,
  },

  methods: {
    async beforeOpen() {
      this.data = await getUser(this.value.userCode)
    },

    beforeSubmit() {
      return this.$refs['editFrom'].$refs['Form'].validate()
    },

    onSubmit() {
      const _data = {
        ...this.data.userInfo,
        ...{
          userRank: this.data.userUnit.userRank,
          userStation: this.data.userUnit.userStation
        }
      }
      return modifyUser(this.value.userCode, _data)
    },
  },
}
</script>
