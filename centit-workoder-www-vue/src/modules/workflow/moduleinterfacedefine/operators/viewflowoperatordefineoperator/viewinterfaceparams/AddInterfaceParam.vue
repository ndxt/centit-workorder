<template>
  <ModalOperator fab title="新增参数明细">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="参数Code"
          v-model="data.businessCode"
          :max="20"
        />
        <zpa-text-input
          label="参数类型"
          v-model="data.parameterType"
          :max="100"
        />
        <zpa-text-input
          label="业务系统参数code"
          v-model="data.parameterCode"
          :max="20"
          required
        />
        <zpa-switch
          label="是否必须"
          v-model="data.isRequired"
          required
          true-value="Y"
          false-value="N"
        />
        <zpa-text-input
          label="业务系统参数描述"
          v-model="data.parameterDesc"
          :max="50"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  newOptCode,
  addParam,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'AddInterfaceOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      this.data = await newOptCode()
      this.data.optReq = 'R'
      this.data.optId = this.value.optId
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      this.data.interfaceId = this.value.interfaceId

      const parameterList = [this.data]

      return addParam({parameterList: parameterList})
    },
  },
}
</script>
