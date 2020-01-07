<template>
  <ModalOperator fab title="新增业务系统">
    <zpa-form ref="Form">
      <OsBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  addOs,
} from '@/api/admin/business'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import OsBaseInfo from '../OsBaseInfo'

export default {
  name: 'AddOsOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    OsBaseInfo,
  },

  methods: {
    initialize() {
      this.data = {
        isPrimary: 'T',
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addOs(this.data)
    },
  },
}
</script>
