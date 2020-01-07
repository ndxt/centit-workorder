<template>
  <ConfirmOperator
    type="info"
    text="全部刷新"
    title="全部刷新"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  allRefreshOs,
} from '@/api/admin/business'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'AllRefreshOsOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认刷新所有系统数据吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      return ['F'].includes(value.roleType)
    },
  },

  methods: {
    onSubmit() {
      return allRefreshOs(this.value)
    },
  },
}
</script>
