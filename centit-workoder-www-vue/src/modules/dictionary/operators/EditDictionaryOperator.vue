<template>
  <ModalOperator type="success" text="编辑" title="编辑数据字典" width="1380">
    <AsideLayout
      v-if="data"
      left
      no-gutter
      height="440px"
      asideWidth="26%"
      :z-index="0"
    >
      <TabList slot="aside" :tabData="leftList">
        <zpa-form ref="Form1" slot="viewBase">
          <DictionaryBaseInfo v-model="data"/>
        </zpa-form>
        <zpa-form ref="Form2" slot="viewField">
          <DictionaryFieldInfo v-model="data"/>
        </zpa-form>
      </TabList>
      <DictionaryDetailsList v-model="data"/>
    </AsideLayout>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import DictionaryBaseInfo from '../DictionaryBaseInfo'
  import DictionaryFieldInfo from '../DictionaryFieldInfo'
  import DictionaryDetailsList from '../DictionaryDetailsList'

  import {getDictionary, modifyDictionary,} from '@/api/admin/dictionary'

  export default {
  name: 'ViewDictionaryDetailsOperator2',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      leftList: [
        {
          name: '基本信息',
          component: 'viewBase',
        },
        {
          name: '字段描述',
          component: 'viewField',
        },
      ],
    }
  },

  components: {
    DictionaryBaseInfo,
    DictionaryFieldInfo,
    DictionaryDetailsList,
  },

  methods: {
    async beforeOpen() {
      this.data = await getDictionary(this.value.catalogCode)
    },

    async beforeSubmit() {
      const f1 = await this.$refs['Form1'].validate()
      const f2 = await this.$refs['Form2'].validate()
      return f1 && f2
    },

    onSubmit() {
      const data = this.data
      return modifyDictionary(data.catalogCode, data)
    },
  },
}
</script>
