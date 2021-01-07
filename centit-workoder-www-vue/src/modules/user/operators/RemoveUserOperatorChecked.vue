<template>
  <ConfirmOperator
    type="warning"
    text="批量删除"
    title="批量删除"
    :content="content"
    :disabled="disabled"
    iconType="md-trash"
    size="default"
  />
</template>

<script>
  import {removeUser,} from '@/api/admin/user'
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveUserOperatorChecked',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    checked: Array
  },

  computed: {
    content() {
      const userName = this.checked.map(item => item.userName)
      return `确认删除用户 <span style="color: #f90">[${userName}]</span> 吗？`
    },

    disabled() {
      if (this.checked.length < 1) return true
    },
  },

  methods: {
    onSubmit() {
      const userCode = this.checked.map(item => item.userCode)
      return removeUser(userCode)
    },
  },
}
</script>
