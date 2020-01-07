<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="角色代码"
      v-model="value.roleCode"
    />
    <zpa-text-input
      required
      label="机构代码"
      v-model="value.unitCode"
    />
    <zpa-text-input
      required
      label="授权描述"
      v-model="value.authDesc"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'FlowManagerUnitBaseInfo',

  props: {
    value: Object,
  },
}
</script>
