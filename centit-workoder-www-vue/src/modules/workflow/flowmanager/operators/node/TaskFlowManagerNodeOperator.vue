<template>
  <ModalOperator text="分配任务" :title="title" :disabled="disabled">
    <zpa-form ref="Form">
      <zpa-form-group>
        <UserSelect
          required
          filterable
          clearable
          label="任务代办人"
          :labelWidth="100"
          tag="A"
          v-model="data.userCode"
        />
        <!--<zpa-date-input-->
          <!--required-->
          <!--label="任务过期时间"-->
          <!--:labelWidth="100"-->
          <!--v-model="data.expireTime"-->
        <!--/>-->
        <zpa-textarea
          required
          label="描述"
          :labelWidth="100"
          v-model="data.authDesc"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {addFlowManagerNodeTask,} from '@/api/workflow/flowManager'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'TaskFlowManagerNodeOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    title() {
      return `分配节点任务 [${this.value.nodeName}]`
    },

    disabled() {
      if (this.value.nodeState === 'N' && !['F', 'C'].includes(this.instState)) {
        return false
      }

      return true
    },
  },

  methods: {
    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      return addFlowManagerNodeTask(this.value.nodeInstId, this.data)
    },
  },
}
</script>
