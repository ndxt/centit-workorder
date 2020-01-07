<template>
  <ModalOperator type="success" text="编辑" title="编辑数据字典详情">
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
  name: 'EditDictionaryDetailOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    DictionaryDetailInfo,
  },

  props: {
    fields: Object,
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
