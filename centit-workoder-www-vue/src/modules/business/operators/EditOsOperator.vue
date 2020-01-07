<template>
  <ModalOperator ref="ModalOperator" text="编辑" title="编辑业务系统">
    <zpa-form ref="Form">
      <OsBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  modifyOs,
  getOs,
} from '@/api/admin/business'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import OsBaseInfo from '../OsBaseInfo'

export default {
  name: 'EditOsOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    OsBaseInfo,
  },

  methods: {
    async beforeOpen() {
      this.data = await getOs(this.value.osId)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return modifyOs(this.data.osId, this.data)
    },
  },
}
</script>
