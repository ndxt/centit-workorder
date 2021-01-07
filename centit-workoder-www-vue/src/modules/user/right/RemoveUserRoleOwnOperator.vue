<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
  />
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {removeUserRoleOwn} from '@/api/admin/user'

  export default {
  name: 'RemoveRoleUserOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {},

  computed: {
    content() {
      return `确认删除用户角色[${this.value.roleName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      // 固定和系统类型不能删除
      // return ['F', 'S'].includes(value.catalogStyle)
    },
  },

  methods: {
    onSubmit() {
      return removeUserRoleOwn(this.value)
    },
  },
}
</script>
