<template>
  <ModalOperator
    v-bind:fab="!value.id"
    :text="value.id ? '新增下级' : ''"
    :title="title"
    type="primary"
  >
    <zpa-form ref="Form">
      <MenuBaseInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import { addMenu } from '@/api/admin/menu';

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
import MenuBaseInfo from '../MenuBaseInfo';

export default {
  name: 'AddMenuOperator',

  mixins: [ModalOperatorMixin],

  props: {
    title: String
  },

  components: {
    MenuBaseInfo
  },

  methods: {
    beforeOpen() {
      this.data = {
        _new: true,
        pageType: 'D',
        isInToolbar: 'Y',
        preOptId: ''
      };
      console.log(this.value);
      if (this.value) {
        this.data.preOptId = this.value.id;
      }
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      this.$emit('addSuccess', this.data);
      return addMenu(this.data);
    }
  }
};
</script>
