<template>
  <ModalOperator type="warning" text="权限" title="操作权限设置" :width="960">
    <zpa-column>
      <zpa-form ref="Form">
        <MenuSetInfo v-model="data" />

      </zpa-form>
    </zpa-column>
    <TabList :tabData="dataList" height="300">
      <dataScope v-model="data" slot="dataScopes" height="180" />
      <optMethod v-model="data" slot="optMethods" height="180" />
    </TabList>
  </ModalOperator>
</template>

<script>
  import {getMenu, setMenuJurisdiction,} from '@/api/admin/menu'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import MenuSetInfo from '../MenuSetInfo'
  import dataScope from '../MenuDataScopeList'
  import optMethod from '../MenuOptMethodList'

  export default {
  name: 'SetMenuOperator',
  data() {
    return {
      dataList: [
        {
          name: '操作定义',
          component: 'optMethods'
        },
        {
          name: '数据范围',
          component: 'dataScopes'
        },
      ],
    }
  },

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    MenuSetInfo,
    dataScope,
    optMethod,
  },

  methods: {
    async beforeOpen() {
      this.data = await getMenu(this.value.optId)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      const data = this.data
      this.$emit('setSuccess', this.data);
      return setMenuJurisdiction(data)
    },
  },
}
</script>
