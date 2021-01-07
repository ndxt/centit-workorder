<template>
  <zpa-column>
    <zpa-form-group title="基本信息">
      <zpa-text-input
        readonly
        label="流程实例号"
        v-model="value.flowInstId"
      />
      <zpa-text-input
        required
        label="实例名称"
        v-model="value.flowOptName"
      />
      <zpa-text-input
        required
        label="业务号"
        v-model="value.flowOptTag"
      />
      <UserSelect
        required
        filterable
        clearable
        label="用户"
        tag="A"
        v-model="value.userCode"
      />
      <UnitTreeSelect
        required
        filterable
        clearable
        label="机构"
        tag="A"
        v-model="value.unitCode"
      />
    </zpa-form-group>

    <zpa-form-group title="状态管理">
      <zpa-switch
        label="暂停"
        v-model="value.instState"
        true-value="P"
        false-value="N"
        :disabled="pauseDisabled"
        :loading="pauseLoading"
        @input="wakePause"
      />
      <zpa-switch
        label="暂停计时"
      />
      <zpa-row style="justify-content: flex-end;">
        <StopRestartFlowManagerOperator v-model="value"/>
      </zpa-row>
    </zpa-form-group>
  </zpa-column>
</template>

<script>
  import {pauseFlowManager, wakeFlowManager,} from '@/api/workflow/flowManager'

  import StopRestartFlowManagerOperator from './StopRestartFlowManagerOperator'

  export default {
  name: 'FlowManagerBaseInfo',

  components: {
    StopRestartFlowManagerOperator,
  },

  data() {
    return {
      pauseLoading: false,
    }
  },

  props: {
    value: Object,
  },

  computed: {
    pauseDisabled() {
      return ['F', 'C', 'M'].includes(this.value.instState)
    },
  },

  methods: {
    async wakePause(val) {
      this.pauseLoading = true
      if (val) {
        await pauseFlowManager(this.value)
      } else {
        await wakeFlowManager(this.value)
      }
      this.pauseLoading = false
    },
  },
}
</script>
