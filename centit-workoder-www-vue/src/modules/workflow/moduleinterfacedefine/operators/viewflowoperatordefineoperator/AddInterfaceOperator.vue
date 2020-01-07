<template>
  <ModalOperator fab title="新增接口明细">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="接口编码"
          v-model="data.interfaceCode"
          :max="20"
        />
        <zpa-text-input
          label="接口地址"
          v-model="data.interfaceAddress"
          :max="100"
        />
        <zpa-text-input
          label="接口描述"
          v-model="data.interfaceDesc"
          :max="20"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import {
  addInterface,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'AddInterfaceOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      // this.data = await newOptCode()

      this.data.optReq = 'R'
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      this.data.interfaceId = '';

      this.data.moduleCode = this.value.moduleCode

      const interfaceList = [this.data]

      return addInterface({interfaceList: interfaceList, optName: ''})
    },
  },
}
</script>
