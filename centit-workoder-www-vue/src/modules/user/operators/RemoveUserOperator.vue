<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  removeUser,
} from '@/api/admin/user'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveUserOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除用户[${this.value.userName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return false
    },
  },

  methods: {
    onSubmit() {
      return removeUser(this.value.userCode)
    },
  },
}
</script>
