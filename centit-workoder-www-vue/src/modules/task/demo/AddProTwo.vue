<template>
  <ModalOperator ref="ModalOperator" text="申请" title="投资决策">
  <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="请示标题"
          v-model="data.optName"
          :max="50"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import {demoSubmit, } from '@/api/task/apprdemo'
export default {
  name: 'AddProTwo',
  mixins: [
    ModalOperatorMixin,
  ],
  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },
    onSubmit() {
      this.data.optType = '2'
      this.data.flowCode = '1523'
      return demoSubmit(this.data)
    },
  },
}
</script>
