<template>
  <ModalOperator fab title="新增顶级机构">
    <zpa-form ref="Form">
      <UnitBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import UnitBaseInfo from '../UnitBaseInfo'
  import {addUnit} from '@/api/admin/unit'

  export default {
  name: 'AddUnitOperator',

  components: {
    UnitBaseInfo,
  },

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    initialize() {
      this.data = {
        unitType: 'A',
        isValid: 'T',
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      this.data.parentUnit = 0
      return addUnit(this.data)
    },
  }
}
</script>
