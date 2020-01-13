<template>
  <ModalOperator  ref="ModalOperator" text="新增子工单" title="新增子工单">
    <zpa-form ref="Form">
      <SubOrderBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import SubOrderBaseInfo from './SubOrderBaseInfo'
import { addSubOrder } from '@/api/workorder.js'
export default {
  name: 'AddSubOrderOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    SubOrderBaseInfo,
  },

  methods: {
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
