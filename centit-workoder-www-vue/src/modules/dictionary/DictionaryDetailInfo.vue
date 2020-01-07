<template>
  <zpa-form-group>
    <zpa-text-input
      :label="fields.dataCode.value"
      v-model="value.dataCode"
      required
      :validators="[checkUnique, 'primary']"
      :validators-message="`${fields.dataCode.value}已存在`"
      :max="16"
    />
    <zpa-text-input
      :label="fields.dataValue.value"
      v-model="value.dataValue"
      required
      :max="256"
    />
    <zpa-text-input
      v-if="fields.extraCode"
      :label="fields.extraCode.value"
      v-model="value.extraCode"
      validators="primary"
      :max="16"
    />
    <!--<zpa-text-input-->
      <!--v-if="checkUse(fields.extraCode2)"-->
      <!--:label="fields.extraCode2.value"-->
      <!--v-model="value.extraCode2"-->
      <!--required-->
      <!--validators="primary"-->
      <!--:max="16"-->
    <!--/>-->
    <!--<zpa-text-input-->
      <!--v-if="checkUse(fields.dataTag)"-->
      <!--:label="fields.dataTag.value"-->
      <!--v-model="value.dataTag"-->
      <!--required-->
      <!--validators="primary"-->
      <!--:max="1"-->
    <!--/>-->
    <zpa-number-input
      label="排序"
      v-model="value.dataOrder"
      required
    />
    <zpa-switch
      v-if="top.admin"
      label="系统参数"
      v-model="value.dataStyle"
      true-value="S"
      false-value="U"
    />
    <zpa-switch
      label="启用"
      v-model="value.dataTag"
      true-value="T"
      false-value="F"
    />
    <zpa-textarea
      v-if="fields.dataDesc"
      :label="fields.dataDesc.value"
      v-model="value.dataDesc"
      :max="80"
    />
  </zpa-form-group>
</template>

<script>
export default {
  name: 'DictionaryDetailInfo',

  props: {
    value: Object,
    fields: Object,
  },

  inject: ['top', 'detail'],

  methods: {

    async checkUnique(value) {
      return !this.detail.value.dataDictionaries.some(d => d.dataCode === value)
    },
  },
}
</script>
