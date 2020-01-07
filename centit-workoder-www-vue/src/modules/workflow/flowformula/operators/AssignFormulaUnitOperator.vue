<template>
  <ModalOperator footerHide type="info" text="指定机构" title="指定权限表达式机构">
    <UnitTreeSelect
      filterable
      multiple
      label="机构"
      tag="A"
      v-model="unit"
      @input="changeFormula"
    />
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'AssignFormulaUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      unit: [],
    }
  },

  methods: {
    async beforeOpen() {
      if (this.unit.length) {
        this.$emit('changeFormula', {
          data: this.unit,
          type: 'D',
        })
      }
    },

    changeFormula(val) {
      this.$emit('changeFormula', {
        data: val,
        type: 'D',
      })
    },
  },
}
</script>
