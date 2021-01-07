<template>
  <ModalOperator type="warning" text="权限" title="角色权限操作" width="1280">
    <PowerList
      ref="Power"
      checkbox
      v-model="data"
    />
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {getRole, saveRolePower,} from '@/api/admin/role'

  export default {
  name: 'RolePowerOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      const res = await getRole(this.value)

      res.rolePowers.forEach(v => {
        this.data[v.optCode] = v.optScopeCodeSet || []
      })
      this.$refs['Power'].load()
    },

    onSubmit() {
      const data = []

      for (const key in this.data) {
        const obj = {
          id: {
            optCode: key,
            roleCode: this.value.roleCode,
          },
          optCode: key,
          roleCode: this.value.roleCode,
        }

        if (this.data[key].length) {
          obj.optScopeCodes = this.data[key].join(',')
        }

        data.push(obj)
      }

      return saveRolePower(this.value.roleCode, data)
    },
  },
}
</script>
