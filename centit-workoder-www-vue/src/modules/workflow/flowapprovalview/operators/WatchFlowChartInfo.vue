<template>
  <ul class="chart_container">
    <li class="chartTitle">待审批流程名称</li>
    <li class="chartItem" v-for="item in value.objList" :key="item.nodeCode">
      <div class="row">
        <span class="itemTitle">{{item.nodeName}}</span>
        <span class="itemUser">{{item.userName}}</span>
        <span class="itemTime">{{item.transDate}}</span>
      </div>
      <div class="row">
        <span class="itemSubTitle">结果</span>
        <span class="itemContent">{{item.ideaName}}</span>
      </div>
      <div class="row">
        <span class="itemSubTitle">意见</span>
        <span class="itemContent">{{item.ideaContent}}</span>
      </div>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'WatchFlowChartInfo',

  data() {
    return {};
  },
  props: {
    value: Object
  },

  methods: {},
  mounted() {}
};
</script>
<style lang="less" scoped>
.chart_container {
  border-left: 2px solid #119c83;
  margin-left: 40px;
  min-height: 100px;
  margin-top: 20px;
  margin-right: 20px;
  li {
    margin-left: 35px;
    position: relative;
    border-bottom: 1px dashed #eee;
    padding-bottom: 20px;
  }
  .chartTitle {
    font-weight: bold;
    font-size: 16px;
    border-bottom: 0;
    padding-bottom: 0;
  }
  .chartTitle::before {
    content: "";
    width: 18px;
    height: 18px;
    position: absolute;
    left: -45px;
    top: 0px;
    background: url("../../../../assets/icon-user.png") no-repeat center
      center/18px 18px;
  }
  .chartItem {
    margin-top: 30px;
    .row {
      margin-top: 15px;
      display: flex;
      .itemTitle {
        flex: 5;
        font-size: 14px;
        font-weight: bold;
      }
      .itemUser {
        flex: 2;
      }
      .itemUser::before {
        content: "";
        width: 14px;
        height: 14px;
        background: url("../../../../assets/small_user.png") no-repeat center
          center/14px 14px;
        float: left;
        margin-right: 10px;
        margin-top: 1px;
      }
      .itemTime {
        flex: 2;
      }
      .itemTime::before {
        content: "";
        width: 14px;
        height: 14px;
        background: url("../../../../assets/small_time.png") no-repeat center
          center/14px 14px;
        margin-right: 10px;
        margin-top: 1px;
        float: left;
      }
      .itemSubTitle {
        flex: 1;
        height: 100%;
        float: left;
      }
      .itemContent {
        flex: 9;
      }
    }
  }
  .chartItem::before {
    content: "";
    width: 14px;
    height: 14px;
    border: 2px solid #119c83;
    border-radius: 50%;
    background-color: #fff;
    position: absolute;
    left: -43px;
    top: 2px;
  }
}
</style>
