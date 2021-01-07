<template>
  <ModalOperator  ref="ModalOperator" text="编辑工单" title="编辑工单">
    <QuestionForm  v-model="value" :catalogName="catalogName"/>
  </ModalOperator>
</template>

<script>

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import QuestionForm from '@/modules/submitOrder/components/QuestionForm'
  import {editOuestion, getOrder} from '@/api/workorder.js'

  export default {
  name: 'EditQuestionOperator',

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
    QuestionForm
  },

  methods: {
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
