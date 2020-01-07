<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="业务ID"
      v-model="value.optId"
      :labelWidth="100"
      :readonly="isEdit"
    />
    <zpa-text-input
      required
      label="业务名称"
      v-model="value.optName"
      :labelWidth="100"
    />
    <zpa-text-input
      label="业务标题模板"
      v-model="value.titleTemplate"
      :labelWidth="100"
    />
    <zpa-text-input
      label="新建业务url"
      v-model="value.optUrl"
      :labelWidth="100"
    />
    <zpa-text-input
      label="业务查看url"
      v-model="value.optViewUrl"
      :labelWidth="100"
    />
    <zpa-text-input
      label="默认流程"
      v-model="value.defaultFlow"
      :labelWidth="100"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'FlowBusinessBaseInfo',

  props: {
    value: Object,
    isEdit: Boolean,
  },
}
</script>
