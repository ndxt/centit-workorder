<template>
  <zpa-form-group>
    <zpa-text-input
      label="业务系统ID"
      v-model="value.osId"
      :max="20"
      required
    />
    <zpa-text-input
      label="业务系统名称"
      v-model="value.osName"
      :max="20"
      required
    />
    <zpa-text-input
      type="url"
      label="业务系统URL"
      v-model="value.osUrl"
      :max="20"
      required
    />
    <zpa-text-input
      label="oauth2登录密码"
      v-model="value.oauthPassword"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'OsInfo',

  props: {
    value: Object,
  },
}
</script>
