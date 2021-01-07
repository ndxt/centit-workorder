<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="代码"
      v-model="value.formulaCode"
    />
    <zpa-text-input
      required
      label="名称"
      v-model="value.formulaName"
    />
    <zpa-textarea
      required
      label="表达式"
      v-model="value.roleFormula"
      :rows="2"
    />
    <zpa-column padding="0 85px 0 0" style="margin-left: 90px; position: relative;">
      <div>系统内置的用户和机构变量</div>
      <div>L：表示上一次运行到本节点的用户或者机构</div>
      <div>P：表示前面一个节点的用户或者机构</div>
      <div>C：表示参数指定的（提交的）人或者机构</div>
      <div>F：表示流程的户和机构</div>
      <div>N：表示节点机构表达式计算的结果</div>
      <AssignFormulaUserOperator
        @changeFormula="changeFormula"
        style="position: absolute; right: 10px; top: 0;"
      />
      <AssignFormulaUnitOperator
        @changeFormula="changeFormula"
        style="position: absolute; right: 10px; top: 30px;"
      />
    </zpa-column>
  </zpa-form-group>
</template>

<script>
  import AssignFormulaUserOperator from './AssignFormulaUserOperator'
  import AssignFormulaUnitOperator from './AssignFormulaUnitOperator'

  export default {
  name: 'FlowFormulaInfo',

  components: {
    AssignFormulaUserOperator,
    AssignFormulaUnitOperator,
  },

  props: {
    value: Object,
  },

  methods: {
    changeFormula(obj) {
      const res = obj.data.map(m => {
        return `'${m}'`
      })

      this.$set(this.value, 'roleFormula', res.length ? `${obj.type}(${res.join(',')})` : '')
    },
  },
}
</script>
