<template>
  <ModalOperator text="编辑" title="编辑菜单">
    <zpa-form ref="Form">
      <MenuBaseInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import { getMenu, modifyMenu } from '@/api/admin/menu';

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
import MenuBaseInfo from '../MenuBaseInfo';

export default {
  name: 'EditMenuOperator',

  mixins: [ModalOperatorMixin],

  components: {
    MenuBaseInfo
  },

  methods: {
    async beforeOpen() {
      //      console.log(this.value)
      this.data = await getMenu(this.value.optId);
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      const data = this.data;
      this.$emit('changeSuccess', this.data);
      return modifyMenu(data);
    }
  }
};
</script>
