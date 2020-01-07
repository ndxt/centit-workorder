<template>
<zpa-form-group title="字段描述">
  <zpa-text-input
    label="dataCode"
    v-model="fields.dataCode.value"
    required
  />
  <zpa-text-input
    label="dataValue"
    v-model="fields.dataValue.value"
    required
  />

  <zpa-text-input
    label="extraCode"
    v-model="fields.extraCode.value"
    :span="6"
  />
  <zpa-text-input
    label="是否使用"
    v-model="fields.extraCode.isUse"
    :span="6"
  />

  <zpa-text-input
    label="extraCode2"
    v-model="fields.extraCode2.value"
    :span="6"
  />
  <zpa-text-input
    label="是否使用"
    v-model="fields.extraCode2.isUse"
    :span="6"
  />

  <zpa-text-input
    label="dataTag"
    v-model="fields.dataTag.value"
    :span="6"
  />
  <zpa-text-input
    label="是否使用"
    v-model="fields.dataTag.isUse"
    :span="6"
  />

  <zpa-text-input
    label="dataDesc"
    v-model="fields.dataDesc.value"
    :span="6"
  />
  <zpa-text-input
    label="是否使用"
    v-model="fields.dataDesc.isUse"
    :span="6"
  />
</zpa-form-group>
</template>

<script>
export default {
  name: 'DictionaryFieldInfo',

  data() {
    return {
      fields: {
        dataCode: {
          isUse: 'T',
        },
        dataValue: {
          isUse: 'T',
        },
        extraCode: {},
        extraCode2: {},
        dataTag: {},
        dataDesc: {},
      }
    }
  },

  props: {
    value: Object,
  },

  watch: {
    'value.fieldDesc': {
      handler(desc) {
        if (desc) {
          this.fields = JSON.parse(desc)
        }
      },

      immediate: true,
    },

    fields: {
      handler(f) {
        this.value.fieldDesc = JSON.stringify(f)
      },

      deep: true,
    },
  },
}
</script>
