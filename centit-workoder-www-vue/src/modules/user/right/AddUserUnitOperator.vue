<template>
  <ModalOperator fab title="添加人员机构">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="用户名"
          v-model="data.userName"
          :max="100"
          required
          readonly
        />
        <UnitTreeSelect label="机构名"  v-model="data.unitCode" :department="top.dept" required/>
        <DictionarySelect code="StationType" label="岗位" v-model="data.userStation" required/>
        <DictionarySelect code="RankType" label="职务"  v-model="data.userRank" required />
        <zpa-textarea :max="85" label="备注" v-model="data.rankMemo"/>
        <zpa-switch label="是否为主机构" v-model="data.isPrimary" true-value="T" false-value="F"/>
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addUserUnit,} from '@/api/admin/user'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'AddUserUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  inject: ['top'],

  methods: {
    initialize() {
      this.data = {
        userName: this.value.userInfo.userName,
        userCode: this.value.userInfo.userCode,
        isPrimary: 'F',
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addUserUnit(this.data)
    },
  },
}
</script>
