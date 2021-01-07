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
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {removeRole,} from '@/api/admin/role'

  export default {
  name: 'RemoveRoleOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {},

  computed: {
    content() {
      return `确认删除角色[${this.value.roleName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      return ['F'].includes(value.roleType)
    },
  },

  methods: {
    onSubmit() {
      return removeRole(this.value)
    },
  },
}
</script>
