<template>
    <TabList :tabData="dataList" hasDataList>
      <unitUser slot="unitUser" v-model="value"></unitUser>
      <unitRole slot="unitRole" v-model="value"></unitRole>
    </TabList>
</template>
<script>
import unitUser from './right/UnitUser'
import unitRole from './right/UnitRole'
export default {
  name: 'RightUnit',
  data() {
    return {
      dataList: [
        {
          name: '机构人员',
          component: 'unitUser'
        },
        {
          name: '机构角色',
          component: 'unitRole'
        },
      ],
    }
  },
  components: {
    unitUser,
    unitRole,
  },
  props: {
    value: Object,
  },
  methods: {

  },
  mounted() {
  },
}
</script>
