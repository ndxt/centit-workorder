<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="标题"
      v-model="value.questionTitle"
    />
    <zpa-text-input
      required
      disabled
      label="类型"
      v-model="catalogName"
    />
    <label class="editor-label">描述</label>
    <vue-ueditor-wrap v-model="value.questionContent" :config="myConfig"></vue-ueditor-wrap>
  </zpa-form-group>
</template>

<script>

export default {
  name: 'QuestionForm',
  data() {
    return {
      myConfig: {
        // autoHeightEnabled: false,
        initialFrameHeight: 240,
        initialFrameWidth: '100%',
        imageUrl: 'http://localhost:8080/utf8-jsp/',
        // 上传文件接口
        // UE.getEditor('editor', { zIndex: 100});
        zIndex: 99999,
      }
    }
  },
  props: {
    value: Object,
    catalogName: String
  },

  methods: {
  },
}
</script>
<style scoped>
.editor-label {
    width: 80px;
    padding: 0 4px 2px 0;
    text-align: right;
}
</style>
