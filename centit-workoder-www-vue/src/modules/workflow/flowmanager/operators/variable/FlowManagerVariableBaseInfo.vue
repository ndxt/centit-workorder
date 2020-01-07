<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="变量名"
      v-model="value.varName"
    />
    <zpa-text-input
      required
      label="变量值"
      v-model="value.varValue"
    />
    <zpa-text-input
      required
      label="令牌"
      v-model="value.runToken"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'FlowManagerVariableBaseInfo',

  props: {
    value: Object,
  },
}
</script>
