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
  import {removeUnitUser,} from '@/api/admin/unit'
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveUserOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除机构人员[${this.value.userName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return false
    },
  },

  methods: {
    onSubmit() {
      return removeUnitUser(this.value.userUnitId)
    },
  },
}
</script>
