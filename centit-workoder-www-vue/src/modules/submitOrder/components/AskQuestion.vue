<template>
  <ModalOperator  ref="ModalOperator" text="提问" title="提问">
    <zpa-form ref="Form">
      <zpa-form-group>
    <Divider>您可能需要的文章</Divider>
    <ul class="doc-list">
      <li v-for="doc in docList" :key="doc.docId">
        {{doc.title}}
      </li>
    </ul>
    <Button type="primary" @click="showQuestionForm = true" long>我有其它问题</Button>
    <QuestionForm v-show="showQuestionForm" v-model="questionInfo" :catalogName="value.catalogName"/>
  </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import QuestionForm from './QuestionForm'
  import {addQuestion, docSearch} from '@/api/workorder.js'

  export default {
  name: 'AddSubOrderOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      docList: [],
      showQuestionForm: false,
      questionInfo: {
        questionTitle: '',
        catalogId: this.value.catalogId,
        questionContent: ''
      }
    }
  },

  components: {
    QuestionForm
  },

  methods: {
    beforeOpen() {
      docSearch({
        osId: 'N002',
        catalogId: this.value.catalogId
      }).then(res => {
        this.docList = res
      })
    },
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      //         catalogDescribe: "234"
      // catalogName: "bbbb"
      // icon: "glyphicon-home"
      // osId: "N002"
      // sort: "1"
      let submitForm = {
        osId: 'N002',
        questionInfo: this.questionInfo
      }
      if (this.showQuestionForm) {
        addQuestion(submitForm)
      }
    },
  },
}
</script>
