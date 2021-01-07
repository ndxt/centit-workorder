<template>
    <TabList :tabData="dataList" hasDataList>
      <UserBaseInfo slot="userInfo" v-model="userInfo"/>
      <UserRole slot="userRole" v-model="curUser" />
      <UserUnit slot="userUnit" v-model="curUser" />
      <UserSetting slot="userSetting" v-model="curUser" />
    </TabList>
</template>
<script>
  import UserBaseInfo from './UserBaseInfo'
  import UserRole from './right/UserRole'
  import UserUnit from './right/UserUnit'
  import UserSetting from './right/UserSetting'

  export default {
  name: 'RightUser',
  computed: {
    userInfo() {
      const curUser = this.curUser
      const readonly = {
        readonly: true
      }
      Object.assign(this.curUser, readonly)
      return curUser
    }
  },
  data() {
    return {
      dataList: [
        {
          name: '用户信息',
          component: 'userInfo'
        },
        {
          name: '用户角色',
          component: 'userRole'
        },
        {
          name: '用户机构',
          component: 'userUnit'
        },
        {
          name: '用户设置',
          component: 'userSetting'
        },
      ],
    }
  },
  components: {
    UserBaseInfo,
    UserRole,
    UserUnit,
    UserSetting,
  },
  props: {
    curUser: Object,
  },
}
</script>
