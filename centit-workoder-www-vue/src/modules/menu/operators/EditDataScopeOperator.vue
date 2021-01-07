<template>
  <ModalOperator type="info" text="编辑" title="编辑数据范围">
    <zpa-form ref="Form">
      <DataScopeInfo
        v-model="data"
      />
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import DataScopeInfo from '../DataScopeInfo'

  export default {
  name: 'EditOptMethodOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    DataScopeInfo,
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
