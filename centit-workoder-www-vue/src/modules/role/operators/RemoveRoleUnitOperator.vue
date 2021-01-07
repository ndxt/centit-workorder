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
  import {removeRoleUnit,} from '@/api/admin/role'

  export default {
  name: 'RemoveRoleUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {},

  computed: {
    content() {
      return `确认删除角色机构[${this.value.unitName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      // 固定和系统类型不能删除
      return ['F', 'S'].includes(value.catalogStyle)
    },
  },

  methods: {
    onSubmit() {
      return removeRoleUnit(this.value)
    },
  },
}
</script>
