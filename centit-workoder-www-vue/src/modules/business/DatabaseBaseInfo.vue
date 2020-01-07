<template>
  <zpa-form-group>
    <zpa-text-input
      label="数据库名"
      v-model="value.databaseName"
      :max="20"
      required
    />
    <zpa-select
      label="业务系统名称"
      v-model="value.osId"
      valueField="osId"
      textField="osName"
      :department="top.dept"
      :query="osSelect"
      required
    />
    <zpa-text-input
      label="用户名"
      v-model="value.username"
      :max="20"
      required
    />
    <zpa-text-input
      label="密码"
      v-model="value.password"
      :max="20"
      required
    />
    <zpa-text-input
      label="数据库连接URL"
      v-model="value.databaseUrl"
      :max="80"
    />
    <zpa-textarea
      label="数据库描述"
      v-model="value.databaseDesc"
      :max="80"
    />
  </zpa-form-group>
</template>

<script>
import {queryOs} from '@/api/admin/business'
export default {
  name: 'DatabaseInfo',

  inject: ['top'],
  methods: {
    async osSelect() {
      const res = await queryOs()
      return res.objList.map(m => {
        const {
          osId,
          osName,
        } = m

        return {
          osId,
          osName,
        }
      })
    },
  },

  props: {
    value: Object,
  },
}
</script>
