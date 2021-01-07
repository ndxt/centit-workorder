<template>
  <ModalOperator  ref="ModalOperator" text="编辑" title="编辑工单">
    <zpa-form ref="Form">
      <OrderBaseInfo v-model="value"/>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {changeOrder} from '@/api/workorder'
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import OrderBaseInfo from './OrderBaseInfo'

  export default {
  name: 'EditOrderOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    value: Object
  },
  components: {
    OrderBaseInfo,
  },

  methods: {

    ok() {
      return this.$refs['ModalOperator'].ok()
    },

    close() {
      return this.$refs['ModalOperator'].close()
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return changeOrder(Object.assign({osId: 'N002'}, this.value))
    },
  },
}
</script>
