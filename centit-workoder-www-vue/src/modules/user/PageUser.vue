<template>
  <AsideLayout no-gutter>
    <SearchLayout>
      <SearchBox slot="search" @search="search" @reset="search">
        <zpa-text-input label="用户名" v-model="params.userName" />
        <UnitTreeSelect label="机构" v-model="params.queryByUnit" :department="dept" filterable />
        <zpa-radio-group label="状态" v-model="params.isValid" :values="isValidValues" />
      </SearchBox>
      <DataList
        ref="Table"
        index
        checkbox
        :columns="userColumns"
        :query="dept ? queryDept : queryUser"
        :operWidth="240"
        @onSelectionChange="getChecked"
        @on-selected-change="selectedChange"
        >
        <template slot="fab">
          <div class="btn_group">
            <span>当前共有{{ userNumber }}位用户</span>
            <div class="btn_group">
               <zpa-row>
                <Button type="primary" @click="ldap">与ldap同步</Button>
              </zpa-row>
              <AddUserOperator @success="search" />
              <zpa-row>
                <Button :disabled="checkedList.length < 1" icon="md-open" @click="exportData">导出</Button>
              </zpa-row>
              <RemoveUserOperatorChecked :checked="checkedList" @success="search" />
            </div>
          </div>
        </template>
        <template slot-scope="props">
          <EditUserOperator v-model="props.row" @success="search" />
          <PowerUserOperator v-model="props.row" @success="search" />
          <RemoveUserOperator v-model="props.row" @success="search"/>
          <ResetPassWordOperator v-model="props.row" @success="search"/>
        </template>
      </DataList>
    </SearchLayout>
    <RightUser slot="aside" :curUser="curUser"/>
  </AsideLayout>
</template>

<script>
  import {getLdap, getUser, queryDept, queryUser} from '@/api/admin/user'
  import userColumns from './userColumns'
  import AddUserOperator from './operators/AddUserOperator'
  import EditUserOperator from './operators/EditUserOperator'
  import RemoveUserOperator from './operators/RemoveUserOperator'
  import RemoveUserOperatorChecked from './operators/RemoveUserOperatorChecked'
  import PowerUserOperator from './operators/PowerUserOperator'
  import ResetPassWordOperator from './operators/ResetPassWordOperator'

  import RightUser from './RightUser'

  export default {
  name: 'PageUser',
  props: {
    dept: Boolean
  },
  data() {
    return {
      params: {},
      userColumns,
      isValidValues: [{
        value: '',
        text: '全部',
      },
      {
        value: 'T',
        text: '在用',
      },
      {
        value: 'F',
        text: '禁用',
      },
      ],
      checkedList: [],
      userNumber: 0,
      curUser: {
        userInfo: {},
        userUnit: {},
      },
    }
  },
  components: {
    RightUser,
    EditUserOperator,
    AddUserOperator,
    RemoveUserOperator,
    RemoveUserOperatorChecked,
    PowerUserOperator,
    ResetPassWordOperator,
  },
  provide() {
    return {
      top: this,
    }
  },
  methods: {
    queryUser,
    queryDept,
    search() {
      const params = this.params
      queryUser().then(res => {
        this.userNumber = res.pageDesc.totalRows
      })
      return this.$refs['Table'].load({ params })
    },

    getChecked(row) {
      this.checkedList = row
    },

    async selectedChange(row) {
      this.curUser = await getUser(row.userCode)
    },

    exportData() {
      this.$refs['Table'].$refs.table.exportCsv({
        filename: '用户管理',
        columns: this.userColumns,
        data: this.checkedList,
      })
    },

    ldap() {
      let _this = this
      getLdap(this.data).then(function (directory) {
        _this.$Message.success('与ldap同步成功！')
        return this.search()
      },
      function (reason, directory) {
        _this.$Message.error('与ldap同步失败！')
      })
    },
  },

  mounted() {
    this.$nextTick(() => {
      this.search()
    })
  },
}

</script>
<style lang="less">
  .btn_group {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 8px;
  }
  .column_operator>.ivu-btn{
    border: none!important;
  }
</style>
