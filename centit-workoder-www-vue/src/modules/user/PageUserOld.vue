<template>
  <AsideLayout no-gutter>
    <SearchLayout>
      <SearchBox slot="search" @search="search">
        <zpa-text-input label="用户名" v-model="params.userName" />
        <UnitTreeSelect label="机构" v-model="params.queryByUnit" :department="dept"/>
        <zpa-radio-group label="状态" v-model="params.isValid" :values="isValidValues"/>
      </SearchBox>
      <DataList
        ref="Table"
        index
        :columns="userColumns"
        :query="!dept ? queryUser : queryDept"
        @on-selected-change="selectedChange"
      >
        <template slot="fab">
          <AddUserOperator @success="search" />
        </template>
        <template slot-scope="props">
          <EditUserOperator v-model="props.row" @success="search" />
          <PowerUserOperator v-model="props.row"/>
          <RemoveUserOperator v-model="props.row" @success="search" />
          <ResetPassWordOperator v-model="props.row" @success="search" />
        </template>
      </DataList>
    </SearchLayout>
    <TabList v-if="selectedRow" :tabData="dataList" slot="aside">
      <Box slot="userInfo">
        <UserBaseInfo v-model="userBaseInfoData"/>
      </Box>
      <UserRole ref="userRole" v-model="selectedRow" slot="userRole"/>
      <UserUnit ref="userUnit" v-model="selectedRow" slot="userUnit"/>
      <UserSetting :id="selectedRow.userCode" slot="userSetting"/>
    </TabList>
  </AsideLayout>
</template>

<script>
  import {getDeptUser, getUser, queryDept, queryUser} from '@/api/admin/user'
  import userColumns from './userColumns'
  import AddUserOperator from './operators/AddUserOperator'
  import EditUserOperator from './operators/EditUserOperator'
  import RemoveUserOperator from './operators/RemoveUserOperator'
  import PowerUserOperator from './operators/PowerUserOperator'
  import UserRole from './right/UserRole'
  import UserUnit from './right/UserUnit'
  import UserSetting from './right/UserSetting'
  import UserBaseInfo from './UserBaseInfo'
  import ResetPassWordOperator from './operators/ResetPassWordOperator'

  export default {
    name: 'PageUser',
    props: {dept: Boolean},
    provide() {
      return {
        top: this,
      }
    },
    data() {
      return {
        params: {},
        userColumns,
        isValidValues: [
          {
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
        dataList: [
          {
            name: '用户信息',
            component: 'userInfo'
          },
          {
            name: '用户角色',
            component: 'userRole'
          }, {
            name: '用户机构',
            component: 'userUnit'
          },
          {
            name: '用户设置',
            component: 'userSetting'
          },
        ],
        selectedRow: null,
        userBaseInfoData: null,
        fn: null,
      }
    },
    components: {
      EditUserOperator,
      AddUserOperator,
      RemoveUserOperator,
      PowerUserOperator,
      UserRole,
      UserUnit,
      UserSetting,
      UserBaseInfo,
      ResetPassWordOperator,
    },
    methods: {
      queryUser,
      queryDept,
      search() {
        const params = this.params
        return this.$refs['Table'].load({params})
      },
      async selectedChange(row) {
        this.selectedRow = row
        if (!this.dept) {
          this.userBaseInfoData = await getUser(row.userCode)
        } else {
          this.userBaseInfoData = await getDeptUser(row.userCode)
        }
        this.userBaseInfoData.readonly = true
        this.$refs['userRole'].search()
        this.$refs['userUnit'].search()
      }
    },
    mounted() {
      this.search()
    },
  }
</script>
