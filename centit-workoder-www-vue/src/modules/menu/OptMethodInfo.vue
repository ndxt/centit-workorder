<template>
  <zpa-form-group>
    <zpa-text-input label="名称" v-model="value.optName" :max="30" required />
    <zpa-select label="请求类型" :multiple="true" v-model="optReqTemp" :values="values" required />
    <zpa-text-input label="请求地址" :max="80" v-model="value.optUrl" />
    <zpa-text-input label="请求方法" :max="16" v-model="value.optMethod" />
    <zpa-textarea label="权限说明" :max="80" v-model="value.optDesc" />
  </zpa-form-group>
</template>
<script>
export default {
  name: 'OptMethodInfo',

  data() {
    return {
      values: [
        { value: 'C', text: '新增（post）' },
        { value: 'R', text: '读取（get）' },
        { value: 'U', text: '更新（put）' },
        { value: 'D', text: '删除（delete）' }
      ],
      optReqTemp: []
    };
  },

  props: {
    value: Object
  },
  watch: {
    'value.optReq'(f) {
      this.optReqTemp = this.value.optReq.split('');
    },
    optReqTemp(f) {
      let temp = this.value;
      this.value.optReq = f.join('');
      this.$emit('input', temp);
    },
    deep: true
  }
};
</script>
