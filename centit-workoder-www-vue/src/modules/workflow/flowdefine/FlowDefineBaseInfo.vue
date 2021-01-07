<template>
  <zpa-form-group>
    <zpa-text-input
      readonly
      label="流程代码"
      v-model="value.flowCode"
    />
    <zpa-text-input
      readonly
      label="当前版本"
      v-model="value.version"
    />
    <zpa-text-input
      required
      label="流程名称"
      v-model="value.flowName"
    />
    <zpa-select
      required
      label="审批模块"
      v-model="value.optId"
      :query="listFlowOptInfo"
      textField="optName"
      valueField="optId"
    />
    <dictionary-select
      label="外部业务"
      v-model="value.osId"
      code="ptbmxx"
    />
    <zpa-text-input
      label="完成期限"
      placeholder="D:天,H:小时,M：分钟,如3D4H30M"
      v-model="value.timeLimit"
      :max="33"
    />
    <dictionary-select
      label="逾期处理"
      v-model="value.expireOpt"
      code="WFExpireOpt"
    />
    <zpa-switch
      v-if="isCopy"
      label="是否复制图"
      v-model="value.doCopyXML"
      true-value="T"
      false-value="F"
    />
    <zpa-textarea
      label="描述"
      v-model="value.flowDesc"
      :max="85"
    />
  </zpa-form-group>
</template>

<script>
  import {listFlowOptInfo,} from '@/api/workflow/flowDefine'

  export default {
  name: 'FlowDefineBaseInfo',

  props: {
    value: Object,
    isCopy: Boolean,
  },

  methods: {
    listFlowOptInfo,
  },
}
</script>
