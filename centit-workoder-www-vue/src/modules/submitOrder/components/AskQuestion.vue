<template>
  <ModalOperator  ref="ModalOperator" text="提问" title="提问">
    <zpa-form ref="Form">
      <AskQuestionForm v-model="data" :docList="docList"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import AskQuestionForm from './AskQuestionForm'
import { addSubOrder, docSearch } from '@/api/workorder.js'
export default {
  name: 'AddSubOrderOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      docList: [],
    }
  },

  components: {
    AskQuestionForm,
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
        icon: 'glyphicon-home'
      }

      addSubOrder(Object.assign({ parentId: this.value.catalogId }, submitForm, this.data))
    },
  },
}
</script>
