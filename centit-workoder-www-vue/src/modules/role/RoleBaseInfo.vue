<template>
  <zpa-form-group>
    <zpa-text-input label="编码" v-if="!value._new" v-model="value.roleCode" required readonly />
    <zpa-text-input
      label="类型"
      v-if="!value._new && !value.topOptId"
      v-model="value.roleTypeText"
      required
      readonly
    />
    <zpa-radio-group
      label="类型"
      v-if="!top.dept && value._new && !value.topOptId"
      v-model="value.roleType"
      :values="roleTypes"
      required
    />
    <zpa-text-input
      label="名称"
      v-model="value.roleName"
      required
      :max="100"
      :validators="checkRoleNameUnique"
      validators-message="角色名称已存在，请重新输入。"
    />
    <zpa-textarea label="描述" v-model="value.roleDesc" :max="85" />
    <zpa-switch label="是否在用" v-model="value.isValid" true-value="T" false-value="F" />
  </zpa-form-group>
</template>

<script>
import { checkRoleNameUnique } from '@/api/admin/role';

export default {
  name: 'RoleBaseInfo',

  inject: ['top'],

  data() {
    return {
      roleTypes: [
        {
          value: 'G',
          text: '全局角色'
        },
        {
          value: 'P',
          text: '公共角色'
        }
      ]
    };
  },

  props: {
    value: Object
  },

  methods: {
    checkRoleNameUnique
  }
};
</script>
