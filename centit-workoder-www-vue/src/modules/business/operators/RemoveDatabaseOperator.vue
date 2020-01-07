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
  removeDatabase,
} from '@/api/admin/business'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'RemoveDatabaseOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除数据库[${this.value.databaseName}]吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      return ['F'].includes(value.roleType)
    },
  },

  methods: {
    onSubmit() {
      return removeDatabase(this.value.databaseCode)
    },
  },
}
</script>
