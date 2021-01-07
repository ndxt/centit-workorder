<template>
  <ModalOperator type="info" text="编辑" title="编辑模块">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="模块代码"
          v-model="data.moduleCode"
          :max="20"
          required
        />
        <zpa-text-input
          label="模块名称"
          v-model="data.moduleName"
          :max="20"
          required
        />
        <zpa-text-input
          label="业务Code"
          v-model="data.optCode"
          :max="100"
        />
        <zpa-text-input
          label="模块描述"
          v-model="data.moduleDesc"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addInterFaceConfig, getInterFaceConfig,} from '@/api/workflow/flowBusiness'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'EditModuleInterfaceConfig',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    async beforeOpen() {
      this.data = await getInterFaceConfig(this.value.moduleCode)
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return addInterFaceConfig(this.data)
    },
  },
}
</script>
