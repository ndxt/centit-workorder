<template>
  <ModalOperator fab title="新增材料分组" label="添加组" >
    <zpa-form ref="Form" style="height: 500px; overflow: auto;">
      <zpa-form-group>
        <zpa-text-input
          label="组名称"
          v-model="data.groupName"
          :max="50"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'AddFlowStuffGroupOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      this.$emit('addGroup', this.data.groupName)
    },
  },
}
</script>
