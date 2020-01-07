<template>
  <ModalOperator type="primary" text="接口明细" title="接口明细" width="1280">
    <AsideLayout
      left
      no-gutter
      height="440px"
      asideWidth="30%"
      :z-index="0"
    >
      <zpa-form slot="aside">
        <zpa-form-group>
          <zpa-text-input label="模块编码" v-model="value.moduleCode" readonly/>
          <zpa-text-input label="模块名称" v-model="value.moduleName" readonly/>
          <zpa-text-input label="模块描述" v-model="value.moduleDesc" readonly/>
        </zpa-form-group>
      </zpa-form>

      <DataList
        index
        isHidePage
        ref="Table"
        :operWidth="150"
        :columns="columns"
        :query="query"
      >
        <template slot="updateDate" slot-scope="{value}">
          {{value | day}}
        </template>

        <template slot="fab">
          <AddInterfaceOperator v-model="value" @success="search"/>
        </template>

        <template slot-scope="props">
          <UrlParamViewList v-model="props.row" @success="search"/>
          <RemoveInterfaceOperator v-model="props.row" @success="search"/>
        </template>
      </DataList>
    </AsideLayout>
  </ModalOperator>
</template>

<script>
import {
  queryInterFacesList
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import AddInterfaceOperator from './AddInterfaceOperator'
import UrlParamViewList from './viewinterfaceparams/UrlParamViewList'
import RemoveInterfaceOperator from './RemoveInterfaceOperator'

export default {
  name: 'ModuleInterfaceViewList',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      columns: [
        {
          title: '接口编码',
          key: 'interfaceCode',
          sortable: 'custom',
        },
        {
          title: '接口地址',
          key: 'interfaceAddress',
          sortable: 'custom',
        },
        {
          title: '接口描述',
          key: 'interfaceDesc',
          sortable: 'custom',
        },

      ],
    }
  },

  components: {
    AddInterfaceOperator,
    UrlParamViewList,
    RemoveInterfaceOperator,
  },

  methods: {
    async query() {
      const data = await queryInterFacesList(this.value.moduleCode)

      this.data = data.flowModuleInterfaceList

      return data.flowModuleInterfaceList
    },

    search() {
      return this.$refs['Table'].load()
    },

    async beforeOpen() {
      this.search()
    },
  },
}
</script>
