<template>
  <ModalOperator fab title="新增数据范围">
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
  name: 'AddDataScopeOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    DataScopeInfo,
  },

  //    props: {
  //      catalogCode: String,
  //      fields: Object,
  //    },

  methods: {
    initialize() {
      this.data = {}
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },
  },
}
</script>
