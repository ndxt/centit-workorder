<template>
  <zpa-form-group>
    <zpa-text-input
      label="数据权限名称"
      v-model="value.scopeName"
      :max="21"
      required
    />
    <zpa-textarea
      label="过滤条件"
      :max="330"
      v-model="value.filterCondition"
    />
    <zpa-textarea
      label="过滤说明"
      :max="330"
      v-model="value.scopeMemo"
    />

  </zpa-form-group>
</template>
<script>
export default {
  name: 'DataScopeInfo',

  props: {
    value: Object,
  },
}

</script>
