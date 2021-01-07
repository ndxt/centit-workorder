<template>
  <ModalOperator :disabled="disabled" text="编辑" title="编辑角色">
    <zpa-form ref="Form">
      <RoleBaseInfo v-model="data" />
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
  import RoleBaseInfo from '../RoleBaseInfo';
  import {getRole, saveRole, updateDepartmentRole} from '@/api/admin/role';

  export default {
  name: 'EditRoleOperator',

  mixins: [ModalOperatorMixin],

  components: {
    RoleBaseInfo
  },

  props: {
    dept: Boolean
  },

  computed: {
    disabled() {
      const value = this.value;

      if (!value) return true;

      return ['F'].includes(value.roleType);
    }
  },

  methods: {
    async beforeOpen() {
      this.data = await getRole(this.value);
    },

    beforeSubmit() {
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      if (this.dept) {
        delete this.data.roleType;
        return updateDepartmentRole(this.data);
      }
      return saveRole(this.data);
    }
  }
};
</script>
