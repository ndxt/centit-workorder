<template>
  <ModalOperator  ref="ModalOperator" text="分配工单" title="分配工单">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-select
          v-model="currentOperator"
          label="主要负责人"
          valueField="userCode"
          textField="userName"
          :labelWidth="120"
          :values="userList"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import { assignOuestion } from '@/api/workorder.js'
import { queryUser } from '@/api/admin/user'
export default {
  name: 'EditQuestionOperator',
  
  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      currentOperator: '',
      userList: [],
      catalogName: '',
      showQuestionForm: false,
    }
  },

  components: {
  },

  methods: {
    beforeOpen() {
      queryUser().then((res) => {
        this.userList = res.objList
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
        currentOperator: this.currentOperator,
        assignInfo: {
          questionId: this.value.questionId,
          currentOperator: this.currentOperator,
        }
      }
      assignOuestion(submitForm)
    },
  },
}
</script>
