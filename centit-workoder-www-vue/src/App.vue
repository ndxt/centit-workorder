<template>
  <transition name="fade" v-if="rendered">
    <router-view v-if="fullscreen"/>

    <MainLayout id="app" v-else>
      <Header slot="header">
        <Tooltip content="首页">
          <Icon
            class="icon"
            size="28"
            color="#e8e8e8"
            type="md-home"
            @click="change()"
          />
        </Tooltip>

        <Tooltip content="集成平台">
          <Icon
            class="icon"
            size="28"
            color="#e8e8e8"
            :class="{active: isActive === 'PLATFORM'}"
            type="ios-cube"
            @click="change('PLATFORM')"
          />
        </Tooltip>

        <Tooltip content="演示系统">
          <Icon
            class="icon"
            size="28"
            color="#e8e8e8"
            :class="{active: isActive === 'FLPT'}"
            type="ios-play"
            @click="change('FLPT')"
          />
        </Tooltip>

        <Tooltip content="消息">
          <Message class="icon" v-model="messageParams"></Message>
        </Tooltip>

        <Tooltip content="皮肤">
          <SkinOperator class="icon"></SkinOperator>
        </Tooltip>

        <Tooltip content="全屏">
          <Icon class="icon" size="28" color="#e8e8e8" type="md-expand" @click="full"/>
        </Tooltip>

        <Dropdown class="icon myDropdown" placement="bottom-end" style="margin-right: 20px" trigger="click">
          <span class="userRank" v-if="currentUser">
            {{currentUser.unitName}} - {{currentUser.userName}} - {{currentUser.userStationText}}（{{currentUser.userRankText}}）
          </span>
          <span class="userRank" v-else>
            {{unitName}} - {{name}} - {{userStationText}}（{{userRankText}}）
          </span>
          <Icon size="28" color="#e8e8e8" type="md-arrow-dropdown"/>
          <DropdownMenu slot="list">
            <DropdownItem>
              <Quit></Quit>
            </DropdownItem>
            <DropdownItem>
              <ChangePwd></ChangePwd>
            </DropdownItem>
            <DropdownItem>
              <UserRank></UserRank>
            </DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </Header>

      <Menu ref="menu" slot="aside"/>

      <router-view :key="$route.name"/>
    </MainLayout>
  </transition>
</template>

<script>
  import _ from 'lodash'
  import {mapMutations, mapState,} from 'vuex'
  import {getCurrposition,} from '@/api/login'

  import MainLayout from './components/layout/MainLayout'
  import Header from './components/commons/Header'
  import Menu from './components/commons/Menu'
  import ChangePwd from './components/commons/ChangePwd'
  import Message from './components/commons/Message'
  import Quit from './components/commons/Quit'
  import UserRank from './components/commons/UserRank'
  // import layui from '../layui/layui.js'

// layui.use('layim', function(im) {
//   console.log('im is', im)
// })
export default {
  name: 'App',

  components: {
    MainLayout,
    Header,
    Menu,
    ChangePwd,
    Quit,
    UserRank,
    Message
  },

  data() {
    return {
      name: '',
      unitName: '',
      userStationText: '',
      userRankText: '',
      messageParams: '带到Message组件的参数',
      changeMenu: 'PLATFORM',
      isActive: 'PLATFORM',
    }
  },

  computed: {
    ...mapState({
      rendered: 'rendered',
    }),

    isAdmin() {
      return this.currentUser.userCode === '230' || this.currentUser.unitCode === '93'
    },

    fullscreen() {
      const route = this.$route
      return route.meta && route.meta.fullscreen
    },

    currentUser() {
      return this.$store.state.currentUser
    }
  },

  methods: {
    ...mapMutations([
      'setScreenSize',
    ]),

    change(val) {
      this.changeMenu = val
      this.isActive = val
      this.$refs['menu'].reload(val)
      this.$router.replace({
        name: 'dashboard'
      })
    },

    resize: _.throttle(function resize() {
      const width = document.body.clientWidth
      let size

      if (width > 1280) {
        size = 'large'
      } else if (width > 768) {
        size = 'small'
      } else {
        size = 'small'
      }

      this.setScreenSize(size)

      this.$root.$emit('resize')
    }, 36),

    full() {
      alert('fullScreen')
    },
  },

  async mounted() {
    window.addEventListener('resize', this.resize)
    this.resize()

    const user = await getCurrposition().then(res => {
      this.$store.state.currentUser = res

      // this.$centitIM({
      //   userCode: res.userUnitId,
      //   userName: res.userName
      // })
    })

    if (user) {
      this.name = user.userName
      this.userStationText = user.userStationText
      this.unitName = user.unitName
      this.userRankText = user.userRankText
    }
  },

  destroyed() {
    window.removeEventListener('resize', this.resize)
  },
}
</script>

<style lang="less">
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }

  body {
    overflow-y: hidden;
  }

  #app {
    width: 100vw;
    min-width: 1280px;
    height: 100vh;
    overflow: hidden;
    font-size: 12px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  .icon {
    cursor: pointer;
    margin: 0 15px;
  }

  .ivu-modal-body {
    position: relative !important;
  }

  .ivu-dropdown-item {
    display: flex;
    justify-content: center;
  }

  ::-webkit-scrollbar {
    width: 6px;
    height: 6px;
  }

  ::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background: #c3c3c3;
  }

  ::-webkit-scrollbar-thumb:hover {
    background: #a9a9a9;
  }
</style>
