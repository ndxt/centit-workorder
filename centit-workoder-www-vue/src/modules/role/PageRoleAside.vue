<template>
  <TabList :tabData="tabs" hasDataList>

    <Box slot="roleUser">
      <RoleUserList v-model="role"/>
    </Box>

    <Box slot="roleUnit">
      <RoleUnit v-model="role"/>
    </Box>

  </TabList>
</template>

<script>
  import RoleUserInheritList from './RoleUserInheritList'
  import RoleUserList from './RoleUserList'
  import RoleUnit from './RoleUnit'

  export default {
  name: 'PageRoleAside',

  components: {
    RoleUserInheritList,
    RoleUserList,
    RoleUnit,
  },

  data() {
    return {
      tabs: [
        {
          name: '角色用户',
          component: 'roleUser',
        },
        {
          name: '角色机构',
          component: 'roleUnit',
        },
      ],
    }
  },

  props: {
    role: Object,
  },
}
</script>
