<template>
  <zpa-form-group>
    <zpa-text-input
      label="编码"
      v-model="value.optId"
      required
      :readonly="!value._new"
      :validators="checkMenuCodeUnique"
      validators-message="编码 已存在，请重新输入。"
      :max="32"
    />
    <zpa-text-input
      label="名称"
      v-model="value.optName"
      :max="30"
      required
    />
    <zpa-text-input
      label="菜单链接"
      v-model="value.optRoute"
      :max="80"
    />
    <DictionarySelect code="OptType" label="业务类型" v-model="value.optType" />
    <!-- <zpa-text-input
      label="父级节点"
      :max="10"
      v-model="value.preOptId"
    /> -->
    <MenuTreeSelect :span="6" tag="A" label="父级节点" v-model="value.preOptId" />
    <zpa-radio-group label="打开方式" v-model="value.pageType" :values="openList" />
    <zpa-radio-group label="是否显示" v-model="value.isInToolbar" :values="queryList" required />
    <zpa-text-input
      label="图标"
      :max="170"
      v-model="value.icon"
    />
    <zpa-number-input
      label="排序"
      v-model="value.orderInd"
    />

  </zpa-form-group>
</template>

<script>
import {checkMenuCodeUnique} from '@/api/admin/menu'
export default {
  name: 'MenuBaseInfo',
  data() {
    return {
      queryList: [{value: 'Y', text: '显示'}, {value: 'N', text: '不显示'}],
      openList: [{value: 'D', text: 'DIV'}, {value: 'I', text: 'IFRAME'}],
    }
  },

  props: {
    value: Object,
  },

  methods: {
    checkMenuCodeUnique,
  },
}
</script>
