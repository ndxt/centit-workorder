<template>
  <ModalOperator type="primary" iconType="md-settings" iconSize="18"  text="修改密码" title="修改密码">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          label="用户名"
          v-model="data.userName"
          required
          readonly
        />
        <zpa-text-input
          label="登陆名"
          v-model="data.loginName"
          readonly
          required
        />
        <zpa-text-input
          label="旧密码"
          v-model="data.oldpassword"
          required
        />
        <zpa-text-input
          label="新密码"
          v-model="data.newpassword"
          required
        />
        <zpa-text-input
          label="新密码确认"
          v-model="data.newpasswordconf"
          :validators="[compare]"
          validators-message="与新密码不一致！"
          required
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>
<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import {getCurrentuser, changepwd, checkpwd, } from '@/api/login'
export default {
  name: 'ChangePwd',

  mixins: [
    ModalOperatorMixin,
  ],
  methods: {
    initialize() {
      this.data = {
        oldpassword: '',
        newpassword: '',
        newpasswordconf: '',
      }
    },
    async beforeOpen() {
      let _currentuser = await getCurrentuser()
      let _data = this.data
      this.data = {..._currentuser.userInfo, ..._data}
    },

    async beforeSubmit() {
      let _checkpwd = await checkpwd({password: this.data.oldpassword})
      let _this = this
      if (!_checkpwd) {
        _this.$Message.error('旧密码输入不正确！')
      }
      return this.$refs['Form'].validate() && _checkpwd
    },

    onSubmit() {
      let _this = this
      changepwd({...this.data, newPassword: this.data.newpassword, password: this.data.oldpassword}).then(function(testData) {
        _this.$Message.success('修改成功！')
      },
      function(reason, testData) {
        _this.$Message.error('修改失败！')
      })
    },

    compare(value) {
      return value === this.data.newpassword
    }
  },
}
</script>
