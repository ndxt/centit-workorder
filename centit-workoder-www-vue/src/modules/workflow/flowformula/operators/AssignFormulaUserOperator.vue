<template>
  <ModalOperator footerHide type="info" text="指定用户" title="指定权限表达式用户">
    <UserSelect
      filterable
      multiple
      label="用户"
      tag="A"
      annex="loginName"
      v-model="user"
      @input="changeFormula"
    />
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'AssignFormulaUserOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      user: [],
    }
  },

  methods: {
    async beforeOpen() {
      if (this.user.length) {
        this.$emit('changeFormula', {
          data: this.user,
          type: 'U',
        })
      }
    },

    changeFormula(val) {
      this.$emit('changeFormula', {
        data: val,
        type: 'U',
      })
    },
  },
}
</script>
