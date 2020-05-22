<template>
<SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-select
      label="工单状态"
      :labelWidth="labelWidth"
      v-model="params.questionState"
      :values="orderStates"
    />
    <zpa-select
      label="创建时间"
      :labelWidth="labelWidth"
      v-model="params.timeState"
      :values="orderTimes"
    />
    <zpa-text-input
      label="工单标题"
      :labelWidth="labelWidth"
      v-model="params.questionTitle"
    />
    </SearchBox>     
    <DataList
      ref="Table"
      isHidePage
      :checkbox="false"
      :columns="listColumns"
      :query="searchQuestion"
      :data="data"
      :operWidth="300"
      idField="catalogId"
    >
    <template slot-scope="props">
        <EditQuestionOperator v-model="props.row" @success="search"/>
        <ViewQuestionOperator v-model="props.row"/>
        <RemoveQuestionOperator v-model="props.row" @success="search"/>
    </template>
    </DataList>
  </SearchLayout>
</template>
<script>
// 做成选择。。。选择。。。的形式
import RemoveQuestionOperator from './components/RemoveQuestionOperator'
import EditQuestionOperator from './components/EditQuestionOperator'
import ViewQuestionOperator from './components/ViewQuestionOperator'
import { searchQuestion } from '@/api/workorder.js'

const orderStates = [
  {
    value: '',
    text: '全部',
  },
  {
    value: 'U',
    text: '未处理',
  },
  {
    value: 'H',
    text: '已受理',
  },
  {
    value: 'R',
    text: '已答复',
  },
  {
    value: 'C',
    text: '已完结',
  },
]
export default {
  name: 'admin',
  components: {
    RemoveQuestionOperator,
    EditQuestionOperator,
    ViewQuestionOperator
  },
  mounted() {
    this.search()
  },
  data() {
    return {
      addOrderModal: false,
      nameVal: '',
      sortVal: '',
      labelWidth: 80,
      describeVal: '',
      listColumns: [
        {
          title: '状态',
          key: 'questionState',
          minWidth: 150,
          render: (h, params) => {
            let stateName
            switch (params.row.questionState) {
              case 'U':
                stateName = '未处理'
                break
              case 'H':
                stateName = '已受理'
                break
              case 'C':
                stateName = '处理中'
                break
              case 'O':
                stateName = '已完结'
                break
            }
            
            return h('div', stateName)
          }
        },
        {
          title: '标题',
          key: 'questionTitle',
          minWidth: 250,
        },
        {
          title: '创建时间',
          key: 'createTime',
          minWidth: 300,
        },
      ],
      data: [],
      orderStates: orderStates,
      orderTimes: [
        {
          value: 'TM',
          text: '本月',
        },
        {
          value: 'LM',
          text: '上月',
        },
        {
          value: 'TS',
          text: '本季度',
        },
        {
          value: 'LS',
          text: '上季度',
        },
        {
          value: 'TY',
          text: '今年',
        },
        {
          value: 'LY',
          text: '去年',
        },
      ],
      params: {    
        questionState: '',
        timeState: 'TY',
        questionTitle: ''       
      }
    }
  },
  methods: {
    searchQuestion,
    createTimeParams() {
      let beginTime
      let endTime
      let currentTime = new Date()
      let currentYear = currentTime.getFullYear()
      let lastMonthYear = currentYear
      let currentMonth = currentTime.getMonth() + 1
      let currentMonthLastDay = (new Date(currentYear, currentMonth + 1, 0)).getDate()
      let lastMonth
      let currentQuarter = Math.ceil(currentMonth / 3)
      let currentQuarterEndMonth = currentQuarter * 3
      let currentQuarterEndMonthLastDay = (new Date(currentYear, currentQuarterEndMonth + 1, 0)).getDate()
      let currentQuarterBeginMonth = currentQuarterEndMonth - 2
      let lastQuarter
      let lastQuarterYear = currentYear
      let lastQuarterEndMonth
      let lastQuarterBeginMonth
      if (currentQuarter === 1) {
        lastQuarter = 4
        lastQuarterYear -= 1
      } else {
        lastQuarter = currentQuarter - 1
      }
      lastQuarterEndMonth = lastQuarter * 3
      lastQuarterBeginMonth = lastQuarterEndMonth - 2
      let lastQuarterEndMonthLastDay = (new Date(lastQuarterYear, lastQuarterEndMonth + 1, 0)).getDate()
      if (currentMonth === 1) {
        lastMonth = 12
        lastMonthYear -= 1
      } else {
        lastMonth = currentMonth - 1
      }
      let lastMonthLastDay = new Date(lastMonthYear, lastMonth + 1, 0)
      const JOIN_TIME = (year, month, day) => {
        month = (100 + month + '').slice(1, 3)
        day = (100 + day + '').slice(1, 3)
        return year + '-' + month + '-' + day
      } 
      switch (this.params.timeState) {
        case 'TM':
          beginTime = JOIN_TIME(currentYear, currentMonth, 1)
          endTime = JOIN_TIME(currentYear, currentMonth, currentMonthLastDay)
          break
        case 'LM':
          beginTime = JOIN_TIME(currentYear, currentMonth, 1)
          endTime = JOIN_TIME(lastMonthYear, lastMonthYear, lastMonthLastDay)
          break
        case 'TS':
          beginTime = JOIN_TIME(currentYear, currentQuarterBeginMonth, 1)
          endTime = JOIN_TIME(currentYear, currentQuarterEndMonth, currentQuarterEndMonthLastDay)
          break
        case 'LS':
          beginTime = JOIN_TIME(lastQuarterYear, lastQuarterBeginMonth, 1)
          endTime = JOIN_TIME(lastQuarterYear, lastQuarterEndMonth, lastQuarterEndMonthLastDay)
          break 
        case 'TY':
          beginTime = JOIN_TIME(currentYear, 1, 1)
          endTime = JOIN_TIME(currentYear, 12, 31)
          break
        case 'LY':
          beginTime = JOIN_TIME(currentYear - 1, 1, 1)
          endTime = JOIN_TIME(currentYear - 1, 12, 31)
          break          
      }
      
      return `begin=${beginTime}&end=${endTime}`
    },
    search() {
      let timeStr = this.createTimeParams()
      this.$refs['Table'].load({params: {
        osId: 'N002',
        searchStr: `questionState=${this.params.questionState}&questionTitle=${this.params.questionTitle}&${timeStr}`
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
