<template>
  <zpa-form-group>
    <zpa-text-input
      label="名称"
      v-model="value.stuffTypeName"
      :max="50"
      required
    />
  </zpa-form-group>
</template>

<script>

export default {
  name: 'FlowStuffTypeInfo',

  data() {
    return {
    }
  },

  props: {
    value: Object,
  },

  methods: {
  },
}
</script>
