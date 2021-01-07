<template>
  <ModalOperator type="info" text="编辑" title="编辑业务流程" width="1280">
    <AsideLayout
      left
      no-gutter
      height="440px"
      asideWidth="30%"
      :z-index="0"
    >
      <zpa-form ref="Form" slot="aside">
        <FlowBusinessBaseInfo v-model="data" isEdit/>
      </zpa-form>

      <DataList
        index
        isHidePage
        ref="Table"
        :columns="columns"
        :query="query"
      >
        <template slot="updateDate" slot-scope="{value}">
          {{value | timeType}}
        </template>

        <template slot="fab">
          <AddFlowBusinessOptOperator v-model="value" @success="reload"/>
        </template>

        <template slot-scope="props">
          <EditFlowBusinessOptOperator v-model="props.row" isEdit :obj="value" @success="reload"/>
          <RemoveFlowBusinessOptOperator v-model="props.row" @success="reload"/>
        </template>
      </DataList>
    </AsideLayout>
  </ModalOperator>
</template>

<script>
  import {editOptInfo, getOptInfo, listOptPages,} from '@/api/workflow/flowBusiness'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import FlowBusinessBaseInfo from './FlowBusinessBaseInfo'
  import AddFlowBusinessOptOperator from './optOperators/AddFlowBusinessOptOperator'
  import EditFlowBusinessOptOperator from './optOperators/EditFlowBusinessOptOperator'
  import RemoveFlowBusinessOptOperator from './optOperators/RemoveFlowBusinessOptOperator'

  export default {
  name: 'EditFlowBusinessOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    FlowBusinessBaseInfo,
    AddFlowBusinessOptOperator,
    EditFlowBusinessOptOperator,
    RemoveFlowBusinessOptOperator,
  },

  data() {
    return {
      pageTypeText: {
        C: '公司开发的业务',
        F: '自定义表单',
        E: '外部（需要iframe嵌入）',
        A: 'auto（自动执行http调用）',
      },
      columns: [
        {
          title: '名称',
          key: 'optName',
          sortable: 'custom',
        },
        {
          title: '页面类别',
          key: 'pageType',
          sortable: 'custom',
          format({row, value}) {
            return this.pageTypeText[value] || value
          },
        },
        {
          title: '页面url',
          key: 'pageUrl',
          sortable: 'custom',
        },
        {
          title: '扩展参数',
          key: 'optMethod',
          sortable: 'custom',
        },
        {
          title: '修改时间',
          key: 'updateDate',
          sortable: 'custom',
        },
      ],
    }
  },

  methods: {
    async beforeOpen() {
      this.data = await getOptInfo(this.value.optId)
      this.reload()
    },

    async query() {
      const res = await listOptPages(this.value.optId)

      return res // .optPages
    },

    reload() {
      return this.$refs['Table'].load()
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return editOptInfo(this.data)
    },
  },
}
</script>
