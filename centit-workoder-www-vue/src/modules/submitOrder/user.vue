<template>
<SearchLayout>     
    <DataList
      ref="Table"
      isHidePage
      :checkbox="false"
      :columns="listColumns"
      :query="getRankList"
      :data="data"
      :operWidth="300"
      idField="catalogId"
    >
    <template slot-scope="props">
        <UserViewSubOrderListOperator :reloadParentTable="reloadTable" v-model="props.row"/>
    </template>
    </DataList>
  </SearchLayout>
</template>
<script>
// 做成选择。。。选择。。。的形式
import UserViewSubOrderListOperator from './components/UserViewSubOrderListOperator'
import { getRankList } from '@/api/workorder.js'
export default {
  name: 'admin',
  components: {
    UserViewSubOrderListOperator,
  },
  mounted() {
    this.$refs['Table'].load({params: {
      osId: 'N002'
    }})
  },
  data() {
    return {
      addOrderModal: false,
      nameVal: '',
      sortVal: '',
      describeVal: '',
      listColumns: [
        {
          title: '类别名称',
          key: 'catalogName',
          minWidth: 150,
        },
        {
          title: '类别排序',
          key: 'sort',
          minWidth: 50,
        },
        {
          title: '类别描述',
          key: 'catalogDescribe',
          minWidth: 300,
        },
      ],
      data: [],
    }
  },
  methods: {
    getRankList,
    reloadTable() {
      this.$refs['Table'].load({params: {
        osId: 'N002'
      }})
    },
    ok () {
      //         catalogDescribe: "234"
      // catalogName: "bbbb"
      // icon: "glyphicon-home"
      // osId: "N002"
      // sort: "1"
      this.$Message.info('Clicked ok');
    },
    cancel () {
      this.$Message.info('Clicked cancel');
    }
  }
}
</script>
<style scoped>
.add-order-category {
    font-size: 18px;
}
</style>
