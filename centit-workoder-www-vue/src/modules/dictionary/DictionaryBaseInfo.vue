<template>
  <zpa-form-group>
    <zpa-text-input
      label="编码"
      v-model="value.catalogCode"
      required
      :readonly="!isNew"
      :validators="[checkDictionaryCodeUnique, 'primary']"
      validators-message="字典编码 已存在，请重新输入。"
      :max="16"
    />
    <zpa-text-input
      label="名称"
      v-model="value.catalogName"
      required
      :max="20"
    />
    <MenuTreeSelect
      label="业务模块"
      v-model="value.optId"
    />
    <zpa-radio-group
      label="参数类型"
      v-model="value.catalogStyle"
      :disabled="!top.admin"
      :values="[{value: 'S', text: '系统'}, {value: 'U', text: '用户'}]"
    />
    <zpa-switch
      label="树形结构"
      v-model="value.catalogType"
      true-value="T"
      false-value="L"
    />
    <zpa-switch
      label="缓存"
      v-model="value.needCache"
      :true-value="1"
      :false-value="0"
    />
    <zpa-textarea
      label="描述"
      v-model="value.catalogDesc"
      :max="85"
    />
  </zpa-form-group>
</template>

<script>
import {
  checkDictionaryCodeUnique
} from '@/api/admin/dictionary'

export default {
  name: 'DictionaryBaseInfo',

  props: {
    value: Object,

    admin: Boolean,
  },

  inject: ['top'],

  computed: {
    isNew() {
      return this.value && this.value._new
    },
  },

  methods: {
    checkDictionaryCodeUnique,
  },
}
</script>
