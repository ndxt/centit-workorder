<template>
  <ModalOperator width="70%" ref="ModalOperator" text="查看工单" title="查看工单">
    <Steps :current="transferState2Code(value.questionState)">
        <Step title="已完成" content="这里是该步骤的描述信息"></Step>
        <Step title="进行中" content="这里是该步骤的描述信息"></Step>
        <Step title="待进行" content="这里是该步骤的描述信息"></Step>
        <Step title="待进行" content="这里是该步骤的描述信息"></Step>
    </Steps>
    <div class="field-container">
      <div class="question-info-field">
    <span class="label">问题标题:</span>
    <span>{{value.questionTitle}}</span>
    </div>
    <div class="question-info-field">
    <span class="label">提交时间:</span>
    <span>{{value.createTime}}</span></div>
    <div class="question-info-field"><span class="label">状态:</span>
    <span>{{transferState2CN(value.questionState)}}</span></div>
    
    <div class="question-info-field"><span class="label">问题描述:</span></div>
    <div class="question-des" v-html="value.questionContent"></div>
    </div>
  </ModalOperator>
</template>

<script>

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import { editOuestion, getOrder } from '@/api/workorder.js'
export default {
  name: 'ViewQuestionOperator',
  
  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      docList: [],
      catalogName: '',
      showQuestionForm: false,
    }
  },

  components: {
  },

  methods: {
    transferState2CN(state) {
      let stateName
      switch (state) {
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
      return stateName     
    },
    transferState2Code(state) {
      let stateName
      switch (state) {
        case 'U':
          stateName = '0'
          break
        case 'H':
          stateName = '1'
          break
        case 'C':
          stateName = '2'
          break
        case 'O':
          stateName = '3'
          break
      }
      return stateName
    },
    beforeOpen() {
      getOrder({
        osId: 'N002',
        catalogId: this.value.catalogId
      }).then(res => {
        this.catalogName = res.catalogName
      })
    },
    beforeSubmit() {
      return true
    //   return this.$refs['Form'].validate()
    },

    onSubmit() {
      //         catalogDescribe: "234"
      // catalogName: "bbbb"
      // icon: "glyphicon-home"
      // osId: "N002"
      // sort: "1"
      let submitForm = {
        osId: 'N002',
        questionId: this.value.questionId,
        questionInfo: this.value
      }
      editOuestion(submitForm)
    },
  },
}
</script>
<style>
.field-container {
  background: #EAEDF1;
  border-radius: 8px;
  padding: 10px;
  margin-top: 16px;
}
.question-des img{
  max-width: 300px !important;
}
.label {
    display: inline-block;
    width: 100px;
}
.question-info-field {
    font-size: 18px;
    margin: 6px;
}
</style>
