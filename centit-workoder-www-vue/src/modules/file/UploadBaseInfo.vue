<template>
  <zpa-form-group>
    <zpa-switch
      label="是否加入全文检索"
      v-model="value.index"
      :span="6"
      :labelWidth="labelWidth"
      true-value="true"
      false-value="false"
    />
    <zpa-switch
      label="添加缩略图"
      v-model="value.thumbnail"
      :span="6"
      :labelWidth="labelWidth"
      true-value="true"
      false-value="false"
    />
    <zpa-switch
      label="添加pdf副本"
      v-model="value.pdf"
      :span="6"
      :labelWidth="labelWidth"
      true-value="true"
      false-value="false"
    />
    <zpa-switch
      label="水印"
      v-model="value.watermark"
      :span="6"
      :labelWidth="labelWidth"
      :disabled="!isPdf"
      true-value="true"
      false-value="false"
    />
    <zpa-radio-group
      label="加密类型"
      v-model="value.encryptType"
      :values="encryptTypes"
      :span="6"
      :labelWidth="labelWidth"
    />
    <zpa-text-input
      label="添加密码"
      v-model="value.password"
      :span="6"
      :labelWidth="labelWidth"
    />
    <zpa-text-input
      label="长度"
      v-model="value.width"
      :span="6"
      :labelWidth="labelWidth"
    />
    <zpa-text-input
      label="高度"
      v-model="value.height"
      :span="6"
      :labelWidth="labelWidth"
    />
    <zpa-text-input
      label="设置上传路径(以当前目录''/a/b的形式'')"
      v-model="value.filePath"
      :labelWidth="labelWidth"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'UploadBaseInfo',

  data() {
    return {
      labelWidth: 115,
      isPdf: false,
      encryptTypes: [
        {
          value: 'zip',
          text: 'zipFile',
        },
        {
          value: 'des',
          text: 'DES加密',
        },
        {
          value: '',
          text: '清空',
        },
      ],
    }
  },

  props: {
    value: Object,
  },

  watch: {
    'value.pdf': {
      handler(data) {
        this.isPdf = data === 'true'
      },
    }
  }
}
</script>
