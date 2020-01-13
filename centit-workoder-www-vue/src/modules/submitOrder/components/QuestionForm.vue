<template>
  <zpa-form-group>
    <zpa-text-input
      required
      label="标题"
      v-model="value.catalogName"
    />
    <zpa-text-input
      required
      label="类型"
      v-model="category.catalogName"
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
  name: 'QuestionForm',

  props: {
    value: Object,
    category: Object
  },

  methods: {
    listFlowOptInfo,
  },
}
</script>
