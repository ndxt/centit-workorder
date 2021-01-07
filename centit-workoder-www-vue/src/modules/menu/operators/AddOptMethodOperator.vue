<template>
  <ModalOperator fab title="新增操作定义">
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
  name: 'AddDictionaryDetailOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    OptMethodInfo,
  },

  //    props: {
  //      catalogCode: String,
  //      fields: Object,
  //    },

  methods: {
    initialize() {
      this.data = {
        optReq: 'R'
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },
  },
}
</script>
