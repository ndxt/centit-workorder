<template>
  <ConfirmOperator
    type="info"
    text="刷新"
    title="刷新"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import {
  singleRefreshOs,
} from '@/api/admin/business'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'SingleRefreshOsOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认刷新[${this.value.osName}]系统数据吗？`
    },

    disabled() {
      const value = this.value

      if (!value) return true

      return ['F'].includes(value.roleType)
    },
  },

  methods: {
    onSubmit() {
      return singleRefreshOs(this.value)
    },
  },
}
</script>
