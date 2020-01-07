<template>
  <ModalOperator type="info" text="查看" title="查看日志">
    <LogInfo v-model="data" />
  </ModalOperator>
</template>

<script>
import {getLog} from '@/api/admin/log'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import LogInfo from './LogInfo'

export default {
  name: 'ViewLogOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    LogInfo,
  },

  methods: {
    async beforeOpen() {
      this.data = await getLog(this.value.logId)
    },
  },
}
</script>
