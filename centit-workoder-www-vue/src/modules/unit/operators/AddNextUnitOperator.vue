<template>
  <ModalOperator type="primary" text="新增下级" :title="titleString">
    <zpa-form ref="Form">
      <UnitBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import UnitBaseInfo from '../UnitBaseInfo'
import {
  addUnit,
} from '@/api/admin/unit'

export default {
  name: 'AddNextUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    UnitBaseInfo,
  },

  computed: {
    titleString() {
      return `新增${this.value.unitName}下级机构`
    },
  },

  methods: {
    async beforeOpen() {
      this.data = {
        addNext: true,
        parentUnitName: this.value.unitName,
        unitType: 'A',
        isValid: 'T',
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      this.data.parentUnit = this.value.unitCode
      return addUnit(this.data)
    },
  }
}
</script>
