<template>
  <div>
    <Button @click="editBtn" class="editBtn">编辑</Button>
    <div style="text-align: center;">
      <div class="title">{{value.docTitle}}</div>
      <div class="docUpdate"><span>更新时间：</span>{{value.lastUpdateTime}}</div>
      <div class="rich-text" v-html="ueditorFile"></div>
    </div>
    <Modal v-model="showEditor" title="编辑文档" width='50%' @on-ok="ok" @on-cancel="cancel">
      <vue-ueditor-wrap v-model="ueditorFile" :config='editorConfig'></vue-ueditor-wrap>
    </Modal>

  </div>

</template>
<script>
import { editDoc } from '@/api/knowedge/knowedge';
export default {
  name: 'knowedgeDoc',
  data() {
    return {
      showEditor: false,
      ueditorFile: '',
      editorConfig: {
        // 编辑器不自动被内容撑高
        autoHeightEnabled: false,
        // 初始容器高度
        initialFrameHeight: 300,
        // 初始容器宽度
        initialFrameWidth: '98%'
        // serverUrl: '/static/UEditor/php/controller.php',
      }
    };
  },
  methods: {
    async ok() {
      await editDoc({docId: this.value.docId, content: this.ueditorFile})
    },
    editBtn() {
      this.showEditor = true
    },
    cancel() {
      this.showEditor = false
      this.ueditorFile = this.value.docFile
    }
  },
  props: {
    value: Object
  },
  watch: {
    'value.docFile': function(val) {
      this.ueditorFile = val
    }
  },
  mounted() {
    console.log(this.value)
  }
};
</script>
<style scoped>
.editBtn {
  position: absolute;
  top: 20px;
  right: 20px;
}
.title {
  margin-top: 26px;
  font-size: 32px;
}
.docUpdate {
  margin-top: 10px;
  color: #999;
  font-size: 14px;
  font-weight: 400;
  line-height: 24px;
}
.rich-text {
  word-break: break-all;
  color: #333;
  overflow: hidden;
  font-size: 16px;
  line-height: 1.6;
  word-wrap: break-word;
  margin: 0 0 18px;
  text-align: left;
}
</style>
