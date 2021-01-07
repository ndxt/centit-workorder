<template>
  <ModalOperator fab title="新增数据字典" width="1380">
    <AsideLayout
      v-if="data"
      left
      no-gutter
      height="440px"
      asideWidth="30%"
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
  import {addDictionary,} from '@/api/admin/dictionary'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import DictionaryBaseInfo from '../DictionaryBaseInfo'
  import DictionaryFieldInfo from '../DictionaryFieldInfo'
  import DictionaryDetailsList from '../DictionaryDetailsList'

  export default {
  name: 'AddDictionaryOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    DictionaryBaseInfo,
    DictionaryFieldInfo,
    DictionaryDetailsList,
  },

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

  inject: ['top'],

  methods: {
    initialize() {
      this.data = {
        _new: true,
        needCache: 1,
        catalogStyle: this.top.admin ? 'S' : 'U',
        catalogType: 'L',
        fieldDesc: '',
        dataDictionaries: [],
      }
    },

    async beforeSubmit() {
      const f1 = await this.$refs['Form1'].validate()
      const f2 = await this.$refs['Form2'].validate()
      return f1 && f2
    },

    onSubmit() {
      return addDictionary(this.data)
    },
  },
}
</script>
