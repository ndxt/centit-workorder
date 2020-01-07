<template>
  <ModalOperator fab title="新增角色">
    <zpa-form ref="Form">
      <RoleBaseInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
import RoleBaseInfo from '../RoleBaseInfo';
import { addRole, createSubSysRole, createDepartmentRole } from '@/api/admin/role';

export default {
  name: 'AddRoleOperator',

  inject: ['top'],

  mixins: [ModalOperatorMixin],

  props: {
    dept: Boolean
  },

  components: {
    RoleBaseInfo
  },

  methods: {
    initialize() {
      this.data = {
        _new: true,
        roleType: this.top.dept ? 'D' : 'G',
        isValid: 'T',
        topOptId: this.$route.query.id
      };
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      if (this.dept) {
        delete this.data.topOptId;
        return createDepartmentRole(this.data)
      }
      if (this.data.topOptId) {
        return createSubSysRole(this.data);
      } else {
        delete this.data.topOptId;
        return addRole(this.data);
      }
    }
  }
};
</script>
