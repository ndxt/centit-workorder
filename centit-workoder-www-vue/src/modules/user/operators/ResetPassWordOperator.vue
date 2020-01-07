<template>
  <ConfirmOperator
    type="primary"
    text="重置密码"
    title="重置密码"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  resetUserPassWord,
} from '@/api/admin/user'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'ResetPassWordOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认重置[${this.value.userName}]的密码吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return false
    },
  },

  methods: {
    onSubmit() {
      return resetUserPassWord({userCodes: this.value.userCode})
    },
  },
}
</script>
