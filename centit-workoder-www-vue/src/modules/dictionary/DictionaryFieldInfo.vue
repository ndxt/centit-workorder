<template>
  <zpa-form-group>
    <zpa-text-input
      label="dataCode"
      v-model="fields.dataCode.value"
      :max="40"
      required
    />
    <zpa-text-input
      label="dataValue"
      v-model="fields.dataValue.value"
      required
      :max="40"
    />
    <zpa-text-input
      label="extraCode"
      v-model="fields.extraCode.value"
      :span="7"
      :required="checkUse(fields.extraCode)"
      :readonly="!checkUse(fields.extraCode)"
      :max="40"
    />
    <zpa-switch
      label="是否使用"
      v-model="fields.extraCode.isUse"
      true-value="T"
      false-value="F"
      :span="5"
    />
    <!--<zpa-text-input-->
      <!--label="extraCode2"-->
      <!--v-model="fields.extraCode2.value"-->
      <!--:span="7"-->
      <!--:max="40"-->
    <!--/>-->
    <!--<zpa-switch-->
      <!--label="是否使用"-->
      <!--v-model="fields.extraCode2.isUse"-->
      <!--true-value="T"-->
      <!--false-value="F"-->
      <!--:span="5"-->
    <!--/>-->
    <zpa-text-input
      label="dataTag"
      v-model="fields.dataTag.value"
      :required="checkUse(fields.dataTag)"
      :readonly="!checkUse(fields.dataTag)"
      :span="7"
      :max="40"
    />
    <zpa-switch
      label="是否使用"
      v-model="fields.dataTag.isUse"
      true-value="T"
      false-value="F"
      :span="5"
    />
    <zpa-text-input
      label="dataDesc"
      v-model="fields.dataDesc.value"
      :required="checkUse(fields.dataDesc)"
      :readonly="!checkUse(fields.dataDesc)"
      :span="7"
      :max="40"
    />
    <zpa-switch
      label="是否使用"
      v-model="fields.dataDesc.isUse"
      true-value="T"
      false-value="F"
      :span="5"
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
      },
    }
  },

  props: {
    value: Object,
  },

  methods: {
    checkUse(obj) {
      return obj.isUse === 'T'
    },

    defaultfieldDesc() {
      return {
        dataCode: {isUse: 'T', value: '编码'},
        dataValue: {isUse: 'T', value: '名称'},
        extraCode: {isUse: 'F', value: '扩展编码'},
        dataTag: {isUse: 'T', value: '启用'},
        dataDesc: {isUse: 'T', value: '描述'},
      }
    },
  },

  watch: {
    'value.fieldDesc': {
      handler(desc) {
        if (desc) {
          this.fields = JSON.parse(desc.replace(/\\/g, ''))
        } else {
          this.fields = this.defaultfieldDesc()
        }
      },

      immediate: true,
    },

    fields: {
      handler(f) {
        this.value.fieldDesc = JSON.stringify(f)
      },

      deep: true,

      immediate: true,
    },
  },
}
</script>
