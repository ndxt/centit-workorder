<template>
  <zpa-form-group>
    <!--<zpa-text-input-->
      <!--required-->
      <!--label="角色代码"-->
      <!--v-model="value.roleCode"-->
    <!--/>-->
    <zpa-auto-complete
      required
      label="角色代码"
      v-model="value.roleCode"
      :query="getRole"
    >
      <zpa-highlight
        slot-scope="{row}"
        :value="`${row.value} (${row.text})`"
        :query="value.roleCode"
      />
    </zpa-auto-complete>
    <zpa-text-input
      required
      label="用户代码"
      v-model="value.userCode"
    />
    <zpa-text-input
      required
      label="授权描述"
      v-model="value.authDesc"
    />
  </zpa-form-group>
</template>

<script>
import {
  getFlowManagerRole,
} from '@/api/workflow/flowManager'

export default {
  name: 'FlowManagerRoleBaseInfo',

  props: {
    value: Object,
    flowCode: String,
  },

  methods: {
    async getRole() {
      const role = []
      const res = await getFlowManagerRole({
        flowCode: this.flowCode,
      })

      for (const key in res) {
        role.push({
          value: key,
          text: res[key],
        })
      }

      return role
    },
  },
}
</script>
