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
  import {removeOs,} from '@/api/admin/business'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveDatabaseOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除业务系统[${this.value.osName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      return ['F'].includes(value.roleType)
    },
  },

  methods: {
    onSubmit() {
      return removeOs(this.value.osId)
    },
  },
}
</script>
