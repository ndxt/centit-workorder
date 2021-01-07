<template>
  <Box noBorder>
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="用户名"
          v-model="userInfo.userName"
          :max="100"
          :readonly="value.readonly"
          :required="!value.readonly"
        />
        <zpa-text-input
          label="登陆名"
          v-model="userInfo.loginName"
          :max="30"
          :readonly="value.readonly"
          :required="!value.readonly"
        />
        <zpa-text-input
          type="phone"
          label="电话"
          v-model="userInfo.regCellPhone"
          :readonly="value.readonly"
        />
        <zpa-text-input
          label="邮箱"
          type="email"
          v-model="userInfo.regEmail"
          :readonly="value.readonly"
        />
        <UnitTreeSelect
          label="主机构"
          v-model="userInfo.primaryUnit"
          :department="top.dept"
          :required="!value.readonly"
          :disabled="value.readonly"
          />
        <DictionarySelect
          code="RankType"
          label="行政职务"
          v-model="userUnit.userRank"
          :required ="!value.readonly"
          :disabled ="value.readonly"
          />
        <DictionarySelect
          code="StationType"
          label="岗位"
          v-model="userUnit.userStation"
          :required="!value.readonly"
          :disabled="value.readonly"
          />
        <zpa-number-input
          label="排序"
          v-model="userInfo.userOrder"
          :readonly="value.readonly"
          :required="!value.readonly"
        />
        <zpa-switch
          label="是否在用"
          v-model="userInfo.isValid"
          true-value="T"
          false-value="F"
          :disabled="value.readonly"
          />
        <zpa-textarea
          v-if="userInfo.userDesc"
          label="描述"
          v-model="userInfo.userDesc"
          :readonly="value.readonly"
          :max="85"
        />
      </zpa-form-group>
    </zpa-form>
  </Box>
</template>

<script>
  import {checkUserLoginNameUnique} from '@/api/admin/user'
  import {queryDictionaryDetails} from '@/api/admin/cp'

  export default {
  name: 'UserBaseInfo',
  computed: {
    userInfo() {
      const userInfo = {
        userName: '',
        loginName: '',
        regCellPhone: '',
        regEmail: '',
        primaryUnit: '',
        userOrder: 0,
        isValid: '',
        userDesc: '',
      }
      if (!this.value.userInfo) {
        return userInfo
      } else {
        const { userInfo } = this.value
        return userInfo
      }
    },
    userUnit() {
      const userUnit = {
        userRank: '',
        userStation: '',
      }
      if (!this.value.userUnit) {
        return userUnit
      } else {
        const { userUnit } = this.value
        return userUnit
      }
    }
  },
  props: {
    value: Object,
    title: String,
  },

  inject: ['top'],

  methods: {
    checkUserLoginNameUnique,
    queryDictionaryDetails,
  },
}
</script>
