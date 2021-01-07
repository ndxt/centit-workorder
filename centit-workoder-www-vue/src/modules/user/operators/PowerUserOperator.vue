<template>
  <ModalOperator type="warning" :width="1260" text="权限" title="用户权限">
    <PowerList
      ref="Power"
      :query="getUserRight"
    />
  </ModalOperator>
</template>
<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {getUserRight,} from '@/api/admin/user'

  export default {
  name: 'PowerUserOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  methods: {
    getUserRight,

    async beforeOpen() {
      this.$refs['Power'].loadShow(this.value)
    },

    beforeSubmit() {
      // return this.$refs['Form'].validate()
    },

    onSubmit() {
      /* return saveRolePower(this.data) */
    },
  },
}
</script>
