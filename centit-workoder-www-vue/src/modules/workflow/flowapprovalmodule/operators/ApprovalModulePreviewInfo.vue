<template>
  <div>
    <FormView
      ref="FormView"
      :id="value.templateJson.formInfo.formUrl"
      v-if="value.templateJson.formOrDocument == 'F'"
    />
    <div class="content" v-if="value.templateJson.stuffInfo.stuffGroupConfig">
      <span class="subTitle">材料下载</span>
      <ul class="tb_cl">
        <li class="header">
          组
          <span class="tb_right">文件</span>
          <span class="tb_center">清单</span>
        </li>
        <li v-for="(item,key) in value.templateJson.stuffInfo.stuffGroupConfig" :key="key">
          {{stuffList[value.templateJson.stuffInfo.stuffCode].stuffName}}
          <template
            v-for="(itemChild,keyChild,indexChild) in stuffList[value.templateJson.stuffInfo.stuffCode].stuffGroup"
          >
            <span class="tb_right" :key="keyChild" :class="{noborder:indexChild==0}">
              <a class="uploadFile">上传文件</a>
            </span>
            <span class="tb_center" :key="keyChild" :class="{noborder:indexChild==0}">{{itemChild}}</span>
          </template>
        </li>
      </ul>
    </div>
    <div class="content" v-if="value.templateJson.flowRoles.length>0">
      <span class="subTitle">角色</span>
      <ul class="tb_cl">
        <li class="header">
          角色代码
          <span class="tb_right">角色名称</span>
          <span class="tb_center">人员</span>
        </li>
        <li v-for="item in value.templateJson.flowRoles" :key="item.roleCode">
          {{item.roleCode}}
          <span class="tb_right noborder" :key="keyChild">
            <a class="uploadFile">添加人员</a>
          </span>
          <span class="tb_center noborder" :key="keyChild">{{item.roleName}}</span>
        </li>
      </ul>
    </div>
    <div class="content" v-if="value.templateJson.ideaInfo.hasIdea">
      <span class="subTitle">{{value.templateJson.ideaInfo.ideaLabel}}</span>
      <Select style="margin-top:20px;width:300px;" :transfer="true" size="large" placeholder="选择"></Select>
    </div>
    <div class="content" v-if="value.templateJson.ideaInfo.hasIdeaContent">
      <span class="subTitle">{{value.templateJson.ideaInfo.ideaContentLabel}}</span>
      <Input
        style="margin-top:20px;"
        type="textarea"
        :autosize="{minRows: 2,maxRows: 2}"
        placeholder="输入"
      />
    </div>
    <div
      class="content"
      style="padding-top:0;"
      v-if="value.templateJson.ideaInfo.canDefer&&value.templateJson.ideaInfo.canRollback&&value.templateJson.ideaInfo.canClose"
    >
      <Button style="width:80px;" type="primary" v-if="value.templateJson.ideaInfo.canDefer">暂停</Button>
      <Button style="width:80px;" type="warning" v-if="value.templateJson.ideaInfo.canRollback">回退</Button>
      <Button style="width:80px;" type="error" v-if="value.templateJson.ideaInfo.canClose">关闭</Button>
    </div>
  </div>
</template>

<script>
import { getAllStuff } from '@/api/workflow/flowApprovalModule';

export default {
  name: 'ApprovalModulePreviewInfo',

  data() {
    return {
      stuffList: []
    };
  },
  props: {
    value: Object
  },

  methods: {
    async initStuffList() {
      this.stuffList = await getAllStuff();
    }
  },
  mounted() {
    this.initStuffList();
  }
};
</script>
<style lang="less" scoped>
.content {
  padding: 20px;
  .subTitle {
    display: inline-block;
    width: 100%;
    font-weight: bold;
  }
  .tb_cl {
    width: 100%;
    overflow: hidden;
    line-height: 40px;
    li {
      padding-left: 10px;
      border: 1px solid #d9d9d9;
      overflow: hidden;
      border-top: 0;
    }
    span {
      box-sizing: border-box;
      padding-left: 10px;
      float: right;
      border: 1px solid #d9d9d9;
      border-bottom: 0;
      border-right: 0;
    }
    span.noborder {
      border-top: 0;
    }
    .tb_center {
      width: 20%;
    }
    .tb_right {
      width: 60%;
      .uploadFile {
        float: right;
        margin-right: 20px;
        color: #0d92ff;
      }
    }
    li.header {
      border: 0;
      border-bottom: 1px solid #d9d9d9;
      span {
        border: 0;
      }
    }
  }
}
</style>
