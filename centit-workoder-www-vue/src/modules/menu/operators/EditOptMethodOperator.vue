<template>
  <ModalOperator type="info" text="编辑" title="编辑操作定义">
    <zpa-form ref="Form">
      <OptMethodInfo
        v-model="data"
      />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import OptMethodInfo from '../OptMethodInfo'

export default {
  name: 'EditOptMethodOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    OptMethodInfo,
  },

  methods: {
    beforeOpen() {
      this.data = Object.assign({}, this.value)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },
  },
}
</script>
