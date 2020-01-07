<template>
  <ModalOperator text="角色明细" title="角色明细" :width="1280" ref="ModalOperator" customButton="true" :ghost="false">
    <div v-if="!value.roleCode">请先选择一条数据</div>
    <AsideLayout
      v-if="value.roleCode"
      left
      no-gutter
      height="440px"
      asideWidth="350px"
      :z-index="0"
    >

      <zpa-clounm slot="aside">
        <zpa-text-input label="角色名称" v-model="value.roleName" readonly/>
        <zpa-text-input label="角色编码" v-model="value.roleCode" readonly/>
      </zpa-clounm>
      <DataList
        ref="Table"
        index
        isHidePage
        :columns="columns"
        :query="query"
        @on-selected-change="SelectedChange"
      >
        <template slot="fab">
          <zpa-row height="auto">
            <AddFlowRoleDefineOperator v-model="value" @success="search" />
            <RemoveFlowRoleDefineOperator v-model="SelectRowData" @success="search" />
          </zpa-row>
        </template>
        <template slot="updateDate" slot-scope="{value}">
          {{value | day}}
        </template>
      </DataList>
    </AsideLayout>
    <div slot="customBtn">
      <Button type="primary" @click="close">关闭</Button>
    </div>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import AddFlowRoleDefineOperator from './AddFlowRoleDefineOperator'
import RemoveFlowRoleDefineOperator from './RemoveFlowRoleDefineOperator'
import {
  queryFlowRoleDefineList,
} from '@/api/workflow/flowroledefine'
export default {
  name: 'FlowRoleDefineViewList',
  mixins: [
    ModalOperatorMixin,
  ],
  data() {
    return {
      columns: [
        {
          title: '关联类型',
          key: 'relatedType',
          sortable: 'custom',
          minWidth: 168,
        },
        {
          title: '关联编码',
          key: 'relatedCode',
          sortable: 'custom',
          minWidth: 168,
        },

      ],
      data: {},
      SelectRowData: {},
    }
  },
  provide() {
    return {
      top: this,
    }
  },
  components: {
    AddFlowRoleDefineOperator,
    RemoveFlowRoleDefineOperator,
  },
  methods: {
    async query() {
      const id = this.value.roleCode
      const data = await queryFlowRoleDefineList(id)
      this.data = data.apprRoleDefineList
      return data.apprRoleDefineList
    },
    SelectedChange(data) {
      console.log(data)
      this.SelectRowData = data
    },
    search() {
      return this.$refs['Table'].load()
    },
    async beforeOpen() {
      this.search()
    },
    close() {
      return this.$refs['ModalOperator'].close()
    },
  },

}
</script>
