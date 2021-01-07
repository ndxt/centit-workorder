<template>
  <ModalOperator type="warning" text="权限" title="机构权限操作" width="1280">
    <PowerList
      ref="Power"
      checkbox
      v-model="data"
    />
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {getUnitPower, saveUnitPower,} from '@/api/admin/unit'

  export default {
  name: 'UnitPowerOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      const res = await getUnitPower(this.value)

      res.forEach(v => {
        this.data[v.optCode] = v.optScopeCodeSet || []
      })
      this.$refs['Power'].load()
    },

    onSubmit() {
      let data = '['

      for (const key in this.data) {
        data += `{'optCode':'${key}'`
        if (this.data[key].length) {
          data += `,'optDataScopes':'${this.data[key].join(',')}'`
        }
        data += '},'
      }

      data += ']'

      return saveUnitPower(this.value.unitCode, data)
    },
  },
}
</script>
