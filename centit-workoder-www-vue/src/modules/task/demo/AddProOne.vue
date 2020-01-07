<template>
  <ModalOperator ref="ModalOperator" text="申请" title="追加预算">
  <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="请示标题"
          v-model="data.optName"
          :max="50"
          required
        />
        <zpa-switch label="是否涉及金额调整" v-model="data.optVar" true-value="Y" false-value="N"/>
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import {demoSubmit, } from '@/api/task/apprdemo'
export default {
  name: 'AddProOne',
  mixins: [
    ModalOperatorMixin,
  ],
  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },
    onSubmit() {
      this.data.optType = '1'
      this.data.flowCode = '1383'
      return demoSubmit(this.data)
    },
  },
}
</script>
