<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
    :disabled="disabled"
  />
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import {
  removeUnit,
} from '@/api/admin/unit';

export default {
  name: 'RemoverUnitOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `确认删除${this.value.unitName}机构吗？`
    },

    disabled() {
      const value = this.value

      if (value.state === 'closed') return true
    },
  },

  methods: {
    onSubmit() {
      return removeUnit(this.value)
    },
  }
}
</script>
