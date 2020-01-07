<template>
  <zpa-form-group>
    <zpa-text-input
      label="编码"
      v-model="value.optId"
      required
      readonly
    />
    <zpa-text-input
      label="名称"
      v-model="value.optName"
      :max="30"
      required
    />
    <zpa-text-input
      label="请求地址"
      :max="80"
      v-model="value.optUrl"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'MenuSetInfo',

  props: {
    value: Object,
  },

  methods: {

  },
}
</script>
