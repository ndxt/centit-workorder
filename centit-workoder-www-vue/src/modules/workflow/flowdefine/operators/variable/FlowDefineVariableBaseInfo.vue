<template>
  <zpa-form-group>
    <!--<zpa-text-input-->
      <!--required-->
      <!--label="变量ID"-->
      <!--v-model="value.flowVariableId"-->
    <!--/>-->
    <zpa-text-input
      required
      label="变量名称"
      v-model="value.variableName"
    />
    <zpa-select
      label="变量类型"
      v-model="value.variableType"
      :values="variableTypes"
    />
    <zpa-text-input
      label="默认值"
      v-model="value.defaultValue"
    />
    <zpa-textarea
      label="中文描述"
      v-model="value.variableDesc"
    />
    <!--<zpa-number-input
      label="变量顺序"
      v-model="value.variableOrder"
    />-->
  </zpa-form-group>
</template>

<script>
export default {
  name: 'FlowDefineVariableBaseInfo',

  props: {
    value: Object,
  },

  data() {
    return {
      variableTypes: [
        {
          value: 'E',
          text: '集合',
        },
        {
          value: 'S',
          text: '单值',
        },
      ],
    }
  },
}
</script>
