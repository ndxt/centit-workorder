<template>
  <zpa-form>
    <zpa-form-group>
      <zpa-text-input
        v-model="value.logId"
        label="日志编号"
        readonly
      />
      <zpa-text-input
        v-model="value.optTime"
        label="操作时间"
        readonly
      />
      <zpa-text-input
        v-model="value.userName"
        label="操作用户"
        readonly
      />
      <zpa-text-input
        v-model="value.optId"
        label="项目模块"
        readonly
      />
      <zpa-textarea
        v-model="value.optContent"
        label="操作信息"
        readonly
      />
      <zpa-textarea
        v-if="value.newValue"
        v-model="value.newValue"
        readonly
      />
    </zpa-form-group>
  </zpa-form>
</template>

<script>
export default {
  name: 'LogInfo',

  props: {
    value: {
      type: Object,
      default: () => {},
    },
  },
}
</script>
