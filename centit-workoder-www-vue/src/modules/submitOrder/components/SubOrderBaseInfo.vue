<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="名称"
      v-model="value.catalogName"
    />
    <zpa-text-input
      required
      label="排序"
      v-model="value.sort"
    />
    <zpa-text-input
      required
      label="关键字"
      v-model="value.catalogKeyWords"
    />
    <zpa-textarea
      label="描述"
      :rows="4"
      v-model="value.catalogDescribe"
    />
  </zpa-form-group>
</template>

<script>
import {
  listFlowOptInfo,
} from '@/api/workflow/flowDefine'

export default {
  name: 'SubOrderBaseInfo',

  props: {
    value: Object,
  },

  methods: {
    listFlowOptInfo,
  },
}
</script>
