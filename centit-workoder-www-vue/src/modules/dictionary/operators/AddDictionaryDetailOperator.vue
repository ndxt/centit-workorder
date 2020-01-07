<template>
  <ModalOperator fab title="新增数据字典详情">
    <zpa-form ref="Form">
      <DictionaryDetailInfo
        v-model="data"
        :fields="fields"
      />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import DictionaryDetailInfo from '../DictionaryDetailInfo'

export default {
  name: 'AddDictionaryDetailOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    DictionaryDetailInfo,
  },

  inject: ['top'],

  props: {
    catalogCode: String,
    fields: Object,
  },

  methods: {
    initialize() {
      this.data = {
        catalogCode: this.catalogCode,
        dataTag: 'T',
        dataStyle: this.top.admin ? 'S' : 'U',
        dataOrder: 1,
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },
  },
}
</script>
