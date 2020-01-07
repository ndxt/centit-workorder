<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除办件角色"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  removeUnitRole,
} from '@/api/admin/unit'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除机构角色[${this.value.roleName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return false
    },
  },

  methods: {
    onSubmit() {
      return removeUnitRole(this.value)
    },
  },
}
</script>
