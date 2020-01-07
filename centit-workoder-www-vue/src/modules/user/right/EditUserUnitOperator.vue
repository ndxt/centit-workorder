<template>
  <ModalOperator text="编辑" title="编辑人员机构">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="用户名"
          v-model="data.userName"
          :max="100"
          required
          readonly
        />
        <zpa-text-input
          label="机构名"
          v-model="data.unitName"
          readonly
          required
        />
        <DictionarySelect code="StationType" label="岗位" v-model="data.userStation" required/>
        <DictionarySelect code="RankType" label="职务"  v-model="data.userRank" required />
        <zpa-textarea label="备注" :max="85" v-model="data.rankMemo"/>
        <zpa-switch label="是否为主机构" v-model="data.isPrimary" true-value="T" false-value="F" :disabled="!value._new && value.isPrimary === 'T'" />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {EditUserUnit, getUserUnit, } from '@/api/admin/user'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'EditUserUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      this.data = await getUserUnit(this.value.userUnitId)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return EditUserUnit(this.data.userUnitId, this.data)
    },
  },
}
</script>
