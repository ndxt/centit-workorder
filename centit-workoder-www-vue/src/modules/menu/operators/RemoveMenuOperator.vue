<template>
  <ConfirmOperator type="error" text="删除" title="删除" :content="content" :disabled="disabled" />
</template>

<script>
  import {removeMenu} from '@/api/admin/menu';
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';

  export default {
  name: 'RemoveMenuOperator',

  mixins: [ModalOperatorMixin],

  computed: {
    content() {
      return `确认删除数菜单[${this.value.text}]吗？`;
    },

    disabled() {
      const value = this.value;

      if (value.state === 'closed') return true;
    }
  },

  methods: {
    onSubmit() {
      this.$emit('removeSuccess', this.value.optId);
      return removeMenu(this.value.optId);
    }
  }
};
</script>
