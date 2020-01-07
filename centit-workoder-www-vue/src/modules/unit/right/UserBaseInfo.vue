<template>
  <zpa-form-group>
    <zpa-text-input
      label="机构名"
      v-model="value.unitName"
      required
      readonly
    />
    <UserSelect
      label="用户名"
      v-if="value._new"
      v-model="value.userCode"
      :department="top.dept"
      filterable
      required
    />
    <zpa-text-input
      label="用户名"
      v-else
      v-model="value.userName"
      required
      readonly
    />
    <DictionarySelect
      label="岗位"
      v-model="value.userStation"
      code="StationType"
      filterable
      required
    />
    <DictionarySelect
      label="职务"
      v-model="value.userRank"
      code="RankType"
      filterable
      required
    />
    <zpa-textarea
      label="描述"
      v-model="value.rankMemo"
      :max="85"
    />
    <zpa-switch
      label="是否为主机构"
      v-model="value.isPrimary"
      true-value="T"
      false-value="F"
      :disabled="!value._new && value.isPrimary === 'T'"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'UserBaseInfo',

  props: {
    value: Object,
  },

  inject: ['top'],
}
</script>
