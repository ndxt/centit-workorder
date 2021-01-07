<template>
  <zpa-form-group>
    <zpa-text-input
      label="上级机构"
      v-if="value.addNext"
      v-model="value.parentUnitName"
      readonly
    />
    <zpa-text-input
      label="编号"
      v-if="isEdit"
      v-model="value.unitCode"
      readonly
    />
    <zpa-text-input
      label="上级机构"
      v-if="isEdit"
      v-model="value.parentUnitName"
      readonly
    />
    <zpa-text-input
      label="名称"
      v-model="value.unitName"
      required
      :max="100"
    />
    <zpa-text-input
      label="简称"
      v-model="value.unitShortName"
      required
      :max="10"
    />
    <zpa-text-input
      label="编码"
      v-model="value.unitWord"
      required
      :max="33"
    />
    <zpa-number-input
      label="排序"
      v-model="value.unitOrder"
      required
    />
    <DictionarySelect
      label="类别"
      v-model="value.unitType"
      code="UnitType"
    />
    <zpa-textarea
      label="描述"
      v-model="value.unitDesc"
      :max="85"
    />
    <zpa-switch
      label="是否在用"
      v-model="value.isValid"
      true-value="T"
      false-value="F"
    />
  </zpa-form-group>
</template>

<script>
  import {checkUnitUpdateWordUnique, checkUnitWordUnique} from '@/api/admin/unit'

  export default {
  name: 'UnitBaseInfo',

  props: {
    value: Object,
  },

  methods: {
    checkUnitWordUnique,

    editWordUsability() {
      return checkUnitUpdateWordUnique(this.value)
    },
  },

  computed: {
    isEdit() {
      return this.value.hasOwnProperty('unitCode')
    },
  },
}
</script>
