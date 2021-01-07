<template>
  <AsideLayout left no-gutter height="440px" asideWidth="45%" :z-index="0">
    <zpa-form-group slot="aside">
      <zpa-text-input label="材料名称" v-model="value.stuffName" :max="50" required />
      <template>
        <zpa-row>
          <span style="font-weight:600;font-size:14px;">组</span>
          <!-- <AddFlowStuffGroupOperator @addGroup="addGroup" /> -->
          <div class="addIcon" @click="addGroup">
            <Icon type="md-add" />添加组
          </div>
        </zpa-row>
      </template>
      <GroupList
        v-model="stuffGroups"
        :selectedIndex="selectedIndex"
        :canAddGroup="canAddGroup"
        @selected="selected"
        @deleteChildSuccess="deleteChildSuccess"
      ></GroupList>
      <div v-if="canAddGroup" class="newGroupText">
        <Input
          v-model="stuffGroups[stuffGroups.length-1].groupName"
          placeholder="组名称"
          style="width: 100%;"
          size="large"
        />
        <span class="GroupTextCancel" @click="cancelNewGroup">取消</span>
      </div>
    </zpa-form-group>
    <zpa-form-group v-if="selectedIndex>=0">
      <template>
        <zpa-row>
          <span style="font-weight:600;font-size:14px;">清单</span>
          <div class="addIcon" @click="addList">
            <Icon type="md-add" />添加清单
          </div>
        </zpa-row>
      </template>
      <template v-if="canAddList && selectedList>=0">
        <zpa-row>
          <zpa-text-input
            label="名称"
            v-model="stuffGroups[selectedIndex].stuffTypes[selectedList].stuffTypeName"
            :max="50"
            required
          />
        </zpa-row>
        <zpa-row>
          <zpa-select
            label="选择格式"
            v-model="stuffGroups[selectedIndex].stuffTypes[selectedList].fileTypeDesc"
            :values="selectorArr"
            required
          />
        </zpa-row>
        <zpa-row>
          <zpa-switch
            label="树形结构"
            v-model="stuffGroups[selectedIndex].stuffTypes[selectedList].isMulti"
            true-value="T"
            false-value="L"
          />
        </zpa-row>
      </template>
    </zpa-form-group>
  </AsideLayout>
</template>

<script>
  import AddFlowStuffGroupOperator from './AddFlowStuffGroupOperator';
  import GroupList from './GroupList';

  export default {
  name: 'FlowStuffInfo',

  data() {
    return {
      selectorArr: [
        {
          value: 'jpg',
          text: 'jpg'
        }
      ],
      selectedIndex: -1,
      canAddGroup: false,
      canAddList: false,
      selectedList: -1
    };
  },

  components: {
    AddFlowStuffGroupOperator,
    GroupList
  },

  props: {
    value: Object
  },
  computed: {
    stuffGroups() {
      return this.value.stuffJson.stuffGroups;
    }
  },
  methods: {
    uuidGenerator() {
      let originStr = 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx';
      let originChar = '0123456789abcdef';
      let len = originChar.length;
      return originStr.replace(/x/g, function(match) {
        return originChar.charAt(Math.floor(Math.random() * len));
      });
    },
    initialize() {
      this.stuffGroups = this.value.stuffJson.stuffGroups;
    },
    selected(index) {
      this.canAddGroup = false;
      this.canAddList = false;
      this.selectedIndex = index;
      if (!this.stuffGroups[this.stuffGroups.length - 1].groupName) {
        this.stuffGroups.splice(this.stuffGroups.length - 1, 1);
      }
    },
    deleteChildSuccess() {
      this.canAddList = false;
      this.selectedList = -1;
    },
    addGroup() {
      if (this.canAddGroup) {
        if (this.stuffGroups[this.stuffGroups.length - 1].groupName) {
          this.stuffGroups.push({
            groupId: this.uuidGenerator(),
            groupName: '',
            stuffTypes: []
          });
        }
        this.selectedIndex = this.stuffGroups.length - 1;
      } else {
        this.canAddGroup = true;
        this.stuffGroups.push({
          groupId: this.uuidGenerator(),
          groupName: '',
          stuffTypes: []
        });
        this.selectedIndex = this.stuffGroups.length - 1;
      }
    },

    cancelNewGroup() {
      this.canAddGroup = false;
      this.stuffGroups.pop();
    },

    addList() {
      if (this.stuffGroups[this.selectedIndex].groupName) {
        this.canAddList = true;
        this.stuffGroups[this.selectedIndex].stuffTypes.push({
          stuffTypeId: this.uuidGenerator(),
          groupId: this.stuffGroups[this.selectedIndex].groupId,
          stuffTypeName: '',
          isMulti: '',
          fileTypeDesc: ''
        });
        this.selectedList =
          this.stuffGroups[this.selectedIndex].stuffTypes.length - 1;
        this.canAddGroup = false;
      }
    }
  }
};
</script>
<style lang="less" scoped>
.newGroupText {
  width: 100%;
  position: relative;
  padding-left: 8px;
  padding-right: 8px;
}
.GroupTextCancel {
  position: absolute;
  right: 18px;
  top: 50%;
  height: 40px;
  line-height: 40px;
  margin-top: -20px;
  color: #2c89ff;
  cursor: pointer;
}

.addIcon {
  margin-left: auto;
  color: #2c89ff;
  cursor: pointer;
}
</style>
