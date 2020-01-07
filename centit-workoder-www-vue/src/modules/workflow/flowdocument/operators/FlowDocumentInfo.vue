<template>
  <div class="documentInfo">
    <div class="item50">
      <span class="itemTitle">文档名称</span>
      <Input v-model="value.documentName" size="large" placeholder="输入名称" />
    </div>
    <div class="item50">
      <span class="itemTitle">上传模板文件</span>
      <zpa-upload :small="true" :params="{}" />
    </div>
    <div class="item50">
      <span class="itemTitle">规则流水号代码</span>
      <Input v-model="value.lshCode" size="large" placeholder="输入流水号" />
    </div>
    <div class="item50">
      <span class="itemTitle50">循环类型</span>
      <span class="itemTitle50">隔离方式</span>
      <div class="itemChild">
        <Select v-model="value.lshCycle" size="large" style="width:100%;">
          <Option v-for="item in lshCycleArr" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </div>
      <div class="itemChild">
        <Select v-model="value.lshOwner" size="large" style="width:100%;">
          <Option v-for="item in lshOwnerArr" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
      </div>
    </div>
    <div class="item50">
      <span class="itemTitle">模板</span>
      <Input v-model="value.documentNoTemplate" size="large" placeholder="模板" />
    </div>
    <div class="item100">
      <span class="itemTitle">备注</span>
      <Input v-model="value.documentDesc" type="textarea" size="large" placeholder="备注信息" rows="5" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'FlowDocumentInfo',

  data() {
    return {
      lshCycleArr: [
        {
          value: 'Y',
          label: '年'
        },
        {
          value: 'M',
          label: '月'
        },
        {
          value: 'W',
          label: '周'
        },
        {
          value: 'D',
          label: '日'
        },
        {
          value: 'N',
          label: '不循环'
        }
      ],
      lshOwnerArr: [
        {
          value: 'U',
          label: '人'
        },
        {
          value: 'D',
          label: '单位'
        },
        {
          value: 'N',
          label: '不隔离'
        }
      ]
    };
  },

  props: {
    value: Object
  }
};
</script>
<style lang='less' scoped>
.item50 {
  float: left;
  width: 50%;
  box-sizing: border-box;
  padding: 0px 20px 20px 20px;
}
.item100 {
  float: left;
  width: 100%;
  box-sizing: border-box;
  padding: 0px 20px 20px 20px;
}
.itemTitle {
  display: inline-block;
  line-height: 24px;
  height: 24px;
}
.itemTitle50 {
  width: 50%;
  line-height: 24px;
  height: 24px;
  box-sizing: border-box;
  padding-right: 20px;
  float: left;
}
.itemChild {
  width: 50%;
  box-sizing: border-box;
  padding-right: 20px;
  float: left;
}
</style>
