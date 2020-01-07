<template>
  <zpa-row class="login" middle center>
    <zpa-column class="form" width="418px" height="660px">
      <img src="../../assets/loginlogo.png"/>
      <form style="margin-top:15px">
        <div class="fields">
          <div style="line-height: 80px;text-align: center;font-size: 20px">系统登录</div>
          <div class="field-input">
            <Icon type="ios-person" :size="20"></Icon>
            <input style="font-size: 15px;" placeholder="用户名" v-model="obj.username" type="text" autocomplete="off"/>
          </div>

          <div class="field-input">
            <Icon type="ios-lock" :size="20"></Icon>
            <input style="font-size: 15px;" placeholder="密码" v-model="obj.password" type="password" autocomplete="off"/>
          </div>

          <div class="field-submit">
            <Button class="submitBtn" @click.prevent="login">
              登录
            </Button>
            <div class="forgot">
              <a href="#">忘记密码?</a>
            </div>
          </div>
        </div>
      </form>
      <div class="disclaimer">
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>南大先腾后台管理系统</p>
        <p>Power by Centit UI. One Step Further.</p>
      </div>
    </zpa-column>
  </zpa-row>
</template>

<script>
import {
  login,
  loginAsAdmin,
  getCurrposition,
} from '@/api/login'

import _ from 'lodash'
import routers from '@/router/index'

export default {
  name: 'Login',

  data() {
    return {
      obj: {},
    }
  },

  props: {
    admin: Boolean,
  },

  methods: {
    login: _.debounce(function () {
      if (this.admin) {
        loginAsAdmin(this.obj)
          .then(res => {
            this.setLogin(res)
          })
          .catch(e => {
            this.$Message.error({
              top: 50,
              duration: 3,
              content: e.toString(),
            });
          })
      } else {
        login(this.obj)
          .then(res => {
            this.setLogin(res)
          })
          .catch(e => {
            this.$Message.error({
              top: 50,
              duration: 3,
              content: e.toString(),
            });
          })
      }
    }, 200),

    setLogin(data) {
      // add from here
      const {
        accessToken,
      } = data

      if (accessToken) {
        this.$store.commit('setToken', accessToken)
      }
      // add end here

      getCurrposition().then(res => {
        this.$store.state.currentUser = res
      })

      const to = routers.$lastTo || {
        name: 'dashboard'
      }

      this.$nextTick(() => this.$router.replace(to))
    },
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
  * {
    margin: 0;
    padding: 0;
  }

  body {
    overflow: hidden;
  }

  .disclaimer {
    position: relative;
    margin-top: 390px !important;
    text-align: center !important;
    color: white !important;
  }

  .login {
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    font-size: 12px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;

    background: url("../../assets/bg.jpg") center center no-repeat;
    background-size: cover;
    /* background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);*/
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#EA5C54 ', endColorstr='#bb6dec', GradientType=1);

  .form {
    position: relative;
    padding: 0 !important;
    background: none;
    height: 390px;
    margin-top: 20px;
    /* background: linear-gradient(45deg, #35394a 0%, #1f222e 100%);*/
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#35394a', endColorstr='#1f222e', GradientType=1);

  .title {
    color: #afb1be;
    height: 60px;
    text-align: left;
    font-size: 16px;
  }

  .fields {
    height: 418px;
    position: absolute;
    left: 0;
    right: 0;
    width: 418px;
    background: url("../../assets/loginbg.png") center center no-repeat;

  .field-input {
    width: 400px;
    position: relative;
    margin-left: 8px;

  .ivu-icon {
    position: absolute;
    top: 50%;
    transform: translate(40px, -12px);
  }

  }

  input {
    color: #afb1be;
    width: 360px;
    margin-top: -2px;
    background: none;
    padding: 20px 10px;
    box-sizing: border-box;
    border-top: none;
    border-bottom: 1px solid #afb1be;
    border-right: none;
    border-left: none;
    outline: none;
    font-family: 'Gudea', sans-serif;
    box-shadow: none;
    text-indent: 40px;
    margin-left: 20px;

  &
  :-webkit-autofill {
    color: #afb1be !important;
    background: #32364a !important;
    border-top: 2px solid #393d52 !important;
    border-bottom: 2px solid #393d52 !important;
    border-right: none !important;
    border-left: none !important;
    outline: none !important;
    box-shadow: none !important;
    transition: all 5000s ease-in-out 0s;
  }

  }

  .field-submit {
    position: relative;
    top: 35px;
    left: 0;
    width: 80%;
    right: 0;
    margin: auto;

  .submitBtn {

    display: block;
    line-height: 50px;
    text-align: center;
    border-radius: 2px;
    width: 330px;
    font-size: 16px;
    background: #169989;
    color: white;
  }

  .forgot {
    float: left;
    font-size: 10px;
    margin-left: 20px;
    margin-top: 11px;
    text-decoration: underline;

  a {
    color: #606479;
  }

  }
  }
  }
  }
  }
</style>
