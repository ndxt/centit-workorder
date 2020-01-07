<template>
  <zpa-form-group>
    <zpa-text-input
      readonly
      label="委托人"
      v-model="value.grantor"
    />
    <zpa-select
      required
      label="部门"
      v-model="value.unitCode"
      :values="units"
    />
    <zpa-select
      required
      multiple
      label="角色"
      v-model="value.roleCode"
      :values="roles"
    />
    <UserSelect
      required
      filterable
      clearable
      label="被委托人"
      tag="A"
      v-model="value.grantee"
    />
    <zpa-date-range-input
      required
      label="委托时间"
      v-model="value"
      startField="relegateTime"
      endField="expireTime"
    />
    <zpa-textarea
      label="备注"
      v-model="value.grantDesc"
      :max="85"
    />
  </zpa-form-group>
</template>

<script>
import {
  getFlowTaskUserRole,
} from '@/api/workflow/flowTask'

export default {
  name: 'FlowTaskEntrustBaseInfo',

  data() {
    return {
      units: [],
      roles: [],
    }
  },

  props: {
    value: Object,
  },

  async mounted() {
    const unit = {}
    const role = {}
    const res = await getFlowTaskUserRole({
      userCode: this.value.grantor,
    })

    res.map(m => {
      unit[m.unitCode] = m.unitName
    })
    for (const key in unit) {
      this.units.push({
        value: key,
        text: unit[key],
      })
    }

    res.map(m => {
      role[m.userRank] = m.userRankText
    })
    for (const key in role) {
      this.roles.push({
        value: key,
        text: role[key],
      })
    }
  },
}
</script>
