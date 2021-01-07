<template>
  <div>
    <!-- <TabList slot="aside" :tabData="leftList" @click="getTabValue"> -->
    <div class="contentLayout">
      <div class="layOutItem">
        <span class="spanSubTitle">模块名称</span>
        <Input v-model="value.moduleName" placeholder="输入" size="large" />
      </div>
    </div>
    <zpa-form v-if="type==1">
      <div class="contentLayout">
        <div class="layOutItem">
          <span class="spanTitle">关联流程</span>
          <span class="spanSubTitle">关联分式</span>
          <RadioGroup
            style="padding-left:20px;"
            v-model="value.templateJson.flowInfo.relatedFlowType"
            @on-change="relatedFlowTypeChange"
          >
            <Radio label="A">关联固定流程</Radio>
            <Radio label="B">通过流程业务选择</Radio>
          </RadioGroup>
        </div>
        <div class="layOutItem">
          <span style="padding-top:40px;" class="spanSubTitle">选择 流程/流程业务</span>
          <Select
            v-model="selectedLC"
            :transfer="true"
            size="large"
            placeholder="选择"
            @on-change="lcChange"
          >
            <Option v-for="(item,key) in lcList" :value="key" :key="key">{{item}}</Option>
          </Select>
        </div>
      </div>
    </zpa-form>
    <zpa-form v-if="type==2">
      <div class="contentLayout">
        <div class="layOutItem">
          <span style="padding-top:20px;" class="spanSubTitle">是否有结果</span>
          <Switch v-model="value.templateJson.ideaInfo.hasIdea" />
          <span style="padding-top:10px;" class="spanSubTitle">结果字典</span>
          <Select
            v-model="value.templateJson.ideaInfo.ideaCatalog"
            :transfer="true"
            size="large"
            placeholder="选择 数据字典"
          >
            <Option v-for="(item,key) in dictionaryList" :value="key" :key="key">{{item}}</Option>
          </Select>
          <span style="padding-top:10px;" class="spanSubTitle">结果标签</span>
          <Input v-model="value.templateJson.ideaInfo.ideaLabel" placeholder="输入" size="large" />
        </div>
        <div class="layOutItem" v-if="value.templateJson.formOrDocument==='F'">
          <span style="padding-top:20px;" class="spanSubTitle">是否有意见</span>
          <Switch v-model="value.templateJson.ideaInfo.hasIdeaContent" />
          <span style="padding-top:10px;" class="spanSubTitle">意见信息标签</span>
          <Input
            v-model="value.templateJson.ideaInfo.ideaContentLabel"
            placeholder="输入"
            size="large"
          />
          <div class="oneDivideThree">
            <span style="padding-top:10px;" class="spanSubTitle">是否可以暂停</span>
            <Switch v-model="value.templateJson.ideaInfo.canDefer" />
          </div>
          <div class="oneDivideThree">
            <span style="padding-top:10px;" class="spanSubTitle">是否可以回退</span>
            <Switch v-model="value.templateJson.ideaInfo.canRollback" />
          </div>
          <div class="oneDivideThree">
            <span style="padding-top:10px;" class="spanSubTitle">是否可以关闭</span>
            <Switch v-model="value.templateJson.ideaInfo.canClose" />
          </div>
        </div>
      </div>
    </zpa-form>
    <!-- </TabList> -->
    <div>
      <div class="contentLayout">
        <div class="layOutItem">
          <span class="spanTitle">业务定义</span>
          <span class="spanSubTitle">业务类别</span>
          <RadioGroup style="padding-left:20px;" v-model="value.templateJson.formOrDocument">
            <Radio label="F">表单</Radio>
            <Radio label="D">公文</Radio>
          </RadioGroup>
        </div>
        <div class="layOutItem" v-if="value.templateJson.formOrDocument==='F'">
          <span style="padding-top:40px;" class="spanSubTitle">表单url</span>
          <Input v-model="value.templateJson.formInfo.formUrl" placeholder="输入" size="large" />
          <span style="padding-top:10px;" class="spanSubTitle">是否需要手动提交</span>
          <Switch v-model="value.templateJson.formInfo.needSubmit" />
          <span style="padding-top:10px;" class="spanSubTitle">提交url</span>
          <Input v-model="value.templateJson.formInfo.formSubmitUrl" placeholder="输入" size="large" />
        </div>
        <div class="layOutItem" v-if="value.templateJson.formOrDocument==='D'">
          <span style="padding-top:40px;" class="spanSubTitle">公文模板</span>
          <Select
            v-model="value.templateJson.documentInfo.documentId"
            :transfer="true"
            size="large"
            placeholder="请选择"
          >
            <Option v-for="(item,key) in documentList" :value="key" :key="key">{{item}}</Option>
          </Select>
          <span style="padding-top:10px;" class="spanSubTitle">操作</span>
          <Select
            v-model="value.templateJson.documentInfo.documentOpt"
            :transfer="true"
            size="large"
            placeholder="选择 拟稿/校验/排版/打印/盖章"
          >
            <Option v-for="item in optionList" :value="item.value" :key="item.value">{{item.label}}</Option>
          </Select>
        </div>
      </div>
      <div class="contentLayout">
        <div class="layOutItem">
          <span class="spanTitle">材料选择</span>
          <span class="spanSubTitle">材料模板</span>
          <Select
            v-model="value.templateJson.stuffInfo.stuffCode"
            :transfer="true"
            size="large"
            placeholder="选择 材料"
            @on-change="stuffCodeChange"
          >
            <Option v-for="(item,key) in stuffList" :value="key" :key="key">{{item.stuffName}}</Option>
          </Select>
        </div>
        <div class="layOutItem">
          <span style="padding-top:40px;" class="spanSubTitle">材料</span>
          <ul class="tb_cl" v-if="stuffGroupList.length>0">
            <li v-for="(item,index) in stuffGroupList" :key="item.value">
              <span class="tb_left">{{item.label}}</span>
              <span class="tb_right">
                <RadioGroup
                  @on-change="stuffGroupChange($event,index,item.value)"
                  style="padding-left:20px;"
                  v-model="item.prop"
                >
                  <Radio label="R">只读</Radio>
                  <Radio label="W">编辑</Radio>
                  <Radio label="N">不可见</Radio>
                </RadioGroup>
              </span>
            </li>
          </ul>
        </div>
      </div>
      <div class="contentLayout">
        <span class="spanTitle">
          流程变量
          <div class="addIcon" @click="addLCBL">
            <Icon type="md-add" />添加流程变量
          </div>
        </span>
        <Table :columns="columns1" :data="value.templateJson.flowVariables">
          <template slot-scope="{ row,index }" slot="varName">
            <Input
              v-model="row.varName"
              @on-change="txtChange($event,index,'varName','flowVariables')"
              placeholder="输入"
            />
          </template>
          <template slot-scope="{ row,index }" slot="varValuePath">
            <Input
              v-model="row.varValuePath"
              @on-change="txtChange($event,index,'varValuePath','flowVariables')"
              placeholder="输入"
            />
          </template>
          <template slot-scope="{ row,index }" slot="isGlobalVar">
            <Switch
              v-model="row.isGlobalVar"
              @on-change="txtChange($event,index,'isGlobalVar','flowVariables')"
            />
          </template>
          <template slot-scope="{ row }" slot="isGlobalVar">
            <Switch v-model="row.isGlobalVar" />
          </template>
          <template slot="option" slot-scope="{ index }">
            <span class="deleteThis" @click="deleteThis(index,'flowVariables')">删除</span>
          </template>
        </Table>
        <span class="spanTitle">
          办件角色
          <div class="addIcon" @click="addUser">
            <Icon type="md-add" />添加角色
          </div>
        </span>
        <Table :columns="columns2" :data="value.templateJson.flowRoles">
          <template slot-scope="{ row,index }" slot="roleCode">
            <Input
              v-model="row.roleCode"
              @on-change="txtChange($event,index,'roleCode','flowRoles')"
              placeholder="输入"
            />
          </template>
          <template slot-scope="{ row,index }" slot="roleName">
            <Input
              v-model="row.roleName"
              @on-change="txtChange($event,index,'roleName','flowRoles')"
              placeholder="输入"
            />
          </template>
          <template slot-scope="{ row,index }" slot="userFilter">
            <Input
              v-model="row.userFilter"
              @on-change="txtChange($event,index,'userFilter','flowRoles')"
              placeholder="输入"
            />
          </template>
          <template slot="option" slot-scope="{ index }">
            <span class="deleteThis" @click="deleteThis(index,'flowRoles')">删除</span>
          </template>
        </Table>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    getAllDocument,
    getAllFlow,
    getAllOptinfo,
    getAllStuff,
    getDictionary
  } from '@/api/workflow/flowApprovalModule';

  export default {
  name: 'ApprovalModuleInfo',

  data() {
    return {
      tabValue: 0,
      leftList: [
        {
          name: '创建流程',
          component: 'left'
        },
        {
          name: '审批过程',
          component: 'right'
        }
      ],
      columns1: [
        {
          title: '变量代码',
          slot: 'varName'
        },
        {
          title: '是否全局变量',
          slot: 'isGlobalVar'
        },
        {
          title: '变量关联关系',
          slot: 'varValuePath'
        },
        {
          title: '操作',
          slot: 'option'
        }
      ],
      columns2: [
        {
          title: '角色代码',
          slot: 'roleCode'
        },
        {
          title: '角色名称',
          slot: 'roleName'
        },
        {
          title: '过滤条件',
          slot: 'userFilter'
        },
        {
          title: '操作',
          slot: 'option'
        }
      ],
      dictionaryList: [],
      lcList: [],
      stuffList: [],
      documentList: [],
      optionList: [
        {
          label: '拟稿',
          value: 'A'
        },
        {
          label: '校验',
          value: 'B'
        },
        {
          label: '排版',
          value: 'C'
        },
        {
          label: '打印',
          value: 'D'
        },
        {
          label: '盖章',
          value: 'E'
        }
      ],
      selectedLC: '',
      stuffGroupList: []
    };
  },
  props: {
    value: Object,
    type: Number | String
  },

  methods: {
    stuffGroupChange(e, index, key) {
      let temp = this.value;
      if (!temp.templateJson.stuffInfo.stuffGroupConfig) {
        temp.templateJson.stuffInfo.stuffGroupConfig = {};
      }
      temp.templateJson.stuffInfo.stuffGroupConfig[key] = e;
      this.$emit('input', temp);
    },
    stuffCodeChange(e) {
      this.stuffGroupList = [];
      for (let key in this.stuffList[e].stuffGroup) {
        this.stuffGroupList.push({
          label: this.stuffList[e].stuffGroup[key],
          value: key,
          prop: ''
        });
      }
      let temp = this.value;
      temp.templateJson.stuffInfo.hasStuff = true;
      this.$emit('input', temp);
    },
    lcChange(e) {
      let temp = this.value;
      if (temp.templateJson.flowInfo.relatedFlowType === 'A') {
        temp.templateJson.flowInfo.flowCode = e;
      } else {
        temp.templateJson.flowInfo.flowOptId = e;
      }
      this.$emit('input', temp);
    },
    relatedFlowTypeChange(e) {
      if (e === 'A') {
        this.initLCList(0);
      } else {
        this.initLCList(1);
      }
    },
    txtChange(e, index, par, ppar) {
      let temp = this.value;
      temp.templateJson[ppar][index][par] = e.target.value;
      this.$emit('input', temp);
    },
    getTabValue(index) {
      this.tabValue = index;
    },
    addLCBL() {
      let temp = this.value;
      temp.templateJson.flowVariables.push({
        isGlobalVar: false,
        varName: '',
        varValuePath: ''
      });
      this.$emit('input', temp);
    },
    addUser() {
      let temp = this.value;
      temp.templateJson.flowRoles.push({});
      this.$emit('input', temp);
    },
    deleteThis(index, par) {
      let temp = this.value;
      temp.templateJson[par].splice(index, 1);
      this.$emit('input', temp);
    },
    async initDictionaryList() {
      this.dictionaryList = await getDictionary();
    },
    async initStuffList() {
      this.stuffList = await getAllStuff();
      for (let key in this.value.templateJson.stuffInfo.stuffGroupConfig) {
        this.stuffGroupList.push({
          label: this.stuffList[this.value.templateJson.stuffInfo.stuffCode]
            .stuffGroup[key],
          value: key,
          prop: this.value.templateJson.stuffInfo.stuffGroupConfig[key]
        });
      }
    },
    async initDocumentList() {
      this.documentList = await getAllDocument();
    },
    async initLCList(flag) {
      if (flag === 0) {
        this.lcList = await getAllFlow();
      } else {
        this.lcList = await getAllOptinfo();
      }
    }
  },
  mounted() {
    this.initDictionaryList();
    this.initStuffList();
    this.initDocumentList();
    if (this.value.templateJson.flowInfo.relatedFlowType === 'A') {
      this.selectedLC = this.value.templateJson.flowInfo.flowCode;
    } else if (this.value.templateJson.flowInfo.relatedFlowType === 'B') {
      this.selectedLC = this.value.templateJson.flowInfo.flowOptId;
    }
    this.initLCList(0);
  }
};
</script>
<style lang="less" scoped>
.contentLayout {
  width: 100%;
  .layOutItem {
    width: 50%;
    float: left;
    overflow: hidden;
    box-sizing: border-box;
    padding-right: 15px;
  }
}
.spanTitle {
  width: 100%;
  display: inline-block;
  font-weight: bold;
  font-size: 14px;
  line-height: 40px;
  position: relative;
}
.spanSubTitle {
  width: 100%;
  display: inline-block;
  line-height: 20px;
  padding-bottom: 10px;
}
.tb_cl {
  border: 1px solid #d9d9d9;
  width: 100%;
  overflow: hidden;
  border-bottom: 0;
  span {
    float: left;
    line-height: 43px;
    box-sizing: border-box;
    border-bottom: 1px solid #d9d9d9;
  }
  .tb_left {
    width: 40%;
    padding-left: 20px;
    border-right: 1px solid #d9d9d9;
  }
  .tb_right {
    width: 60%;
  }
}
.oneDivideThree {
  width: 33.3333%;
  float: left;
  overflow: hidden;
}
.translateUp {
  transform: translateY(-120px);
}
.addIcon {
  color: #2c89ff;
  cursor: pointer;
  float: right;
  margin-right: 30px;
}
.deleteThis {
  color: #2c89ff;
  cursor: pointer;
}
</style>
