<template>
  <ModalOperator width="60%"  ref="ModalOperator" text="查看子工单" title="查看子工单">
    <DataList
      ref="Table"
      isHidePage
      :checkbox="false"
      :columns="listColumns"
      :query="getSubRankList"
    >
    <template slot-scope="props">
    
        <AskQuestion v-model="props.row" @success="reloadParentTable"/>
    </template>
    </DataList>
  </ModalOperator>
</template>

<script>

import AskQuestion from './AskQuestion'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
export default {
  name: 'UserViewSubOrderListOperator',

  components: {
    AskQuestion
  },

  mixins: [
    ModalOperatorMixin,
  ],
  mounted() {
    
  },
  props: {
    reloadParentTable: Function
  },
  data() {
    return { 
      listColumns: [
        {
          title: '工单名称',
          key: 'catalogName',
          minWidth: 100,
        },
        {
          title: '工单排序',
          key: 'sort',
          minWidth: 100,
        },
        {
          title: '关键词',
          key: 'catalogKeyWords',
          minWidth: 200,
        },
        {
          title: '工单描述',
          key: 'catalogDescribe',
          minWidth: 200,
        },
      ],
      list: [],
    }
  },
  methods: {
    getSubRankList() {
      return new Promise((resolve, reject) => {
        resolve({
          objList: this.value.c || []
        })
      })
    },
    beforeOpen() {
      this.$refs['Table'].load()
    },
    beforeSubmit() {
      
    },

  },
}
</script>
