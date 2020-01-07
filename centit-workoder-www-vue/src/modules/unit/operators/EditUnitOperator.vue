<template>
  <ModalOperator text='编辑' :title="titleString">
    <zpa-form ref="Form">
      <UnitBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import UnitBaseInfo from '../UnitBaseInfo'
import {
  getUnit,
  saveUnit,
} from '@/api/admin/unit'

export default {
  name: 'EditUnitOperator',

  mixins: [
    ModalOperatorMixin
  ],

  components: {
    UnitBaseInfo,
  },

  computed: {
    titleString() {
      return '编辑' + (typeof (this.data.unitName) === 'undefined' ? '' : this.data.unitName)
    }
  },

  methods: {
    async beforeOpen() {
      this.data = await getUnit(this.value)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return saveUnit(this.data)
    },
  },
}
</script>
