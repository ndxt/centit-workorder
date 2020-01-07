<template>
  <div class="groupContainer">
    <div
      class="groupItem"
      v-for="(item,index) in showArr"
      :class="{groupSelected:index==selectedIndex}"
      :key="item.groupId"
      @click="selectThis(index)"
    >
      <div class="groupTitle" :class="{noborder:item.stuffTypes.length==0}">
        {{item.groupName}}
        <span class="group_icon" @click.stop="deleteThis(index)">删除</span>
        <span v-if="isEdit!=index" class="group_icon" @click.stop="editThis(index)">编辑</span>
        <span v-if="isEdit==index" class="group_icon" @click.stop="editThis(-1)">取消编辑</span>
      </div>
      <div
        class="groupChild"
        v-for="(childItem,childIndex) in item.stuffTypes"
        :class="{noborder:childIndex==item.stuffTypes.length-1}"
        :key="childItem.stuffTypeId"
      >
        {{childItem.stuffTypeName}}
        <Icon
          @click.stop="deleteThisChild(index,childIndex)"
          v-if="index == isEdit"
          style="position:absolute;left:10px;top:50%;margin-top:-10px;cursor:pointer;"
          color="red"
          size="18"
          type="ios-remove-circle-outline"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GroupList',

  data() {
    return {
      isEdit: -1
    };
  },

  components: {},

  computed: {
    showArr() {
      if (this.canAddGroup) {
        return this.value.slice(0, this.value.length - 1);
      } else {
        return this.value;
      }
    }
  },

  props: {
    value: Object | Array,
    canAddGroup: Boolean,
    selectedIndex: String | Number
  },

  methods: {
    deleteThis(index) {
      let temp = this.value;
      temp.splice(index, 1);
      this.$emit('input', temp);
      this.$emit('selected', -1);
    },
    selectThis(index) {
      this.$emit('selected', index);
    },
    editThis(index) {
      this.isEdit = index;
    },
    deleteThisChild(index, childIndex) {
      let temp = this.value;
      temp[index].stuffTypes.splice(childIndex, 1);
      this.$emit('input', temp);
      this.$emit('deleteChildSuccess');
    }
  }
};
</script>
<style lang="less" scoped>
.groupContainer {
  padding: 8px;
  width: 100%;
  border-top: 1px solid #ebebeb;
  .groupItem {
    width: 100%;
    border: 1px solid #ebebeb;
    margin-bottom: 8px;
    .groupTitle {
      height: 40px;
      line-height: 24px;
      border-bottom: 1px solid #ebebeb;
      box-sizing: border-box;
      padding: 8px;
      .group_icon {
        color: #2c89ff;
        cursor: pointer;
        float: right;
        margin-left: 5px;
      }
    }
    .groupChild {
      height: 40px;
      line-height: 24px;
      border-bottom: 1px solid #ebebeb;
      box-sizing: border-box;
      padding: 8px;
      padding-left: 28px;
      position: relative;
    }
    .noborder {
      border: 0;
    }
  }
  .groupSelected {
    border: 1px solid #9ecafb;
  }
}
</style>
