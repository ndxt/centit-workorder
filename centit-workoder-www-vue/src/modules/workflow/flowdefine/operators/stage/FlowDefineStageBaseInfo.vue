<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="阶段编码"
      v-model="value.stageCode"
    />
    <zpa-text-input
      required
      label="阶段名称"
      v-model="value.stageName"
    />
    <zpa-switch
      label="是否计入时间"
      v-model="value.isAccountTime"
      true-value="T"
      false-value="F"
    />
    <dictionary-select
      label="期限类别"
      v-model="value.limitType"
      code="LIMIT_TYPE"
    />
    <zpa-date-input
      label="期限日期"
      v-model="value.timeLimit"
    />
    <zpa-select
      label="逾期处理"
      v-model="value.expireOpt"
      code="WFExpireOpt"
    />
  </zpa-form-group>
</template>

<script>
import {
  listFlowOptInfo,
} from '@/api/workflow/flowDefine'

export default {
  name: 'FlowDefineStageBaseInfo',

  props: {
    value: Object,
  },

  methods: {
    listFlowOptInfo,
  },
}
</script>
