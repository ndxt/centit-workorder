<template>
  <div class="header zpa-row">
    <zpa-row class="logo" center middle width="430px" >
      <img @click="showMsg" src="../../assets/logo.png">
    </zpa-row>
    <zpa-row end middle>
      <slot></slot>
    </zpa-row>
  </div>
</template>

<script>
import { getCurrposition } from '@/api/login';
export default {
  name: 'Header',
  methods: {
    showMsg() {
      getCurrposition().then(position => {
        this.$store.state.currentUser = position
        this.$centitIM({
          userCode: position.userCode,
          userName: position.userName
        })
      })
    }
  }
}
</script>

<style scoped lang="less">
  .header {
    background: #263238;

  .logo {
    font-size: 20px;
    color: #fff;
  }
  }
</style>
