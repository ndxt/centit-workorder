<template>
  <ModalOperator  :label="'新增一级工单类别'" fab title="新增一级工单类别">
    <zpa-form ref="Form">
      <OrderBaseInfo v-model="data"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import OrderBaseInfo from './OrderBaseInfo'
import { addOrder } from '@/api/workorder.js'
export default {
  name: 'AddOrderCategoryOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    OrderBaseInfo,
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

      addOrder(Object.assign({}, submitForm, this.data))
    },
  },
}
</script>
