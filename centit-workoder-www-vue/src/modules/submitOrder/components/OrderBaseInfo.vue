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
  name: 'OrderBaseInfo',

  props: {
    value: Object,
  },

  methods: {
    listFlowOptInfo,
  },
}
</script>
