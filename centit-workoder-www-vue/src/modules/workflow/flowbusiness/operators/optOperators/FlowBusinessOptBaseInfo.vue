<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="编码"
      v-model="value.optCode"
      :max="20"
      :readonly="isEdit"
    />
    <zpa-text-input
      required
      label="名称"
      v-model="value.optName"
      :max="100"
    />
    <zpa-select
      label="页面类别"
      v-model="value.pageType"
      :values="pageTypes"
    />
    <zpa-text-input
      label="页面url"
      v-model="value.pageUrl"
      :max="200"
    />
    <zpa-select
      v-if="value.pageType === 'A'"
      label="扩展参数"
      v-model="value.optMethod"
      :values="optMethods"
    />
    <!--<zpa-text-input-->
    <!--label="扩展参数"-->
    <!--placeholder="如移动端方法等等"-->
    <!--v-model="value.extendMethod"-->
    <!--:max="200"-->
    <!--/>-->
  </zpa-form-group>
</template>

<script>
export default {
  name: 'FlowBusinessOptBaseInfo',

  data() {
    return {
      pageTypes: [
        {
          value: 'C',
          text: '公司开发的业务',
        },
        {
          value: 'F',
          text: '自定义表单',
        },
        {
          value: 'E',
          text: '外部（需要iframe嵌入）',
        },
        {
          value: 'A',
          text: 'auto（自动执行http调用）',
        },
      ],
      optMethods: [
        {
          value: 'C',
          text: 'post',
        },
        {
          value: 'R',
          text: 'get',
        },
        {
          value: 'U',
          text: 'put',
        },
        {
          value: 'D',
          text: 'delete',
        },
      ],
    }
  },

  props: {
    value: Object,
    isEdit: Boolean,
  },
}
</script>
