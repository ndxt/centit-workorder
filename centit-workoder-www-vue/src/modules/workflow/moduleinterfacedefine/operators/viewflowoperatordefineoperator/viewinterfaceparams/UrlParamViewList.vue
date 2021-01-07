<template>
  <ModalOperator type="primary" text="参数明细" title="参数明细" width="1000">
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
          <zpa-text-input label="接口Code" v-model="value.interfaceCode" readonly/>
          <zpa-text-input label="接口描述" v-model="value.interfaceDesc" readonly/>
          <zpa-text-input label="接口地址" v-model="value.interfaceAddress" readonly/>
        </zpa-form-group>
      </zpa-form>

      <DataList
        index
        isHidePage
        ref="Table"
        :operWidth="80"
        :columns="columns"
        :query="query"
      >
        <template slot="updateDate" slot-scope="{value}">
          {{value | day}}
        </template>

        <template slot="fab">
          <AddInterfaceParam v-model="value" @success="search"/>
        </template>

        <template slot-scope="props">
          <RemoveInterfaceOperator v-model="props.row" @success="search"/>
        </template>
      </DataList>
    </AsideLayout>
  </ModalOperator>
</template>

<script>
  import {queryParamsList} from '@/api/workflow/flowBusiness'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import AddInterfaceParam from './AddInterfaceParam'
  import RemoveInterfaceOperator from './RemoveInterfaceOperator'

  export default {
  name: 'UrlParamViewList',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      columns: [
        {
          title: '参数Code',
          key: 'businessCode',
          sortable: 'custom',
        },
        {
          title: '参数类型',
          key: 'parameterType',
          sortable: 'custom',
        },
        {
          title: '业务系统参数code',
          key: 'parameterCode',
          sortable: 'custom',
        },
        {
          title: '是否必须',
          key: 'isRequired',
          sortable: 'custom',
        },
        {
          title: '业务系统参数描述',
          key: 'parameterDesc',
          sortable: 'custom',
        },
      ],
    }
  },

  components: {
    AddInterfaceParam,
    RemoveInterfaceOperator,
  },

  methods: {
    async query() {
      const data = await queryParamsList(this.value.interfaceId)

      this.data = data.parameterList

      return data.parameterList
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
