<template>
  <div class="aside nav" :class="menuClass">
    <ul v-if="loading">

      <li
        class="nav-item"
        :class="showClass(m)"
        v-for="m in carte"
        :key="m.id"
      >
        <template v-if="m.children">
          <a @click="toggle(m)">
            <Icon class="nav-icon" :type="m.icon"/>
            <span>{{m.text}}</span>
            <Icon class="nav-more" :type="m.state ? 'ios-arrow-down' : 'ios-arrow-forward'"/>
          </a>

          <ul>
            <li
              class="nav-item"
              :class="showClass(m2)"
              v-for="m2 in m.children"
              :key="m2.id"
            >
              <template v-if="m2.children">
                <a @click="toggle(m2, m2.pid)">
                  <Icon class="nav-icon" :type="m2.icon"/>
                  <span>{{m2.text}}</span>
                  <Icon class="nav-more" :type="m2.state ? 'ios-arrow-down' : 'ios-arrow-forward'"/>
                </a>

                <ul>
                  <li v-for="m3 in m2.children" :key="m3.id">
                    <router-link v-if="!isHttp(m3.url)" :to="m3.url || '/'" :class="selectedClass(m3)">
                      <Icon class="nav-icon" :type="m3.icon"/>
                      <span>{{m3.text}}</span>
                    </router-link>

                    <a v-else :href="m3.url" target="_blank">
                      <Icon class="nav-icon" :type="m3.icon"/>
                      <span>{{m3.text}}</span>
                    </a>
                  </li>
                </ul>
              </template>

              <router-link v-else-if="!isHttp(m2.url)" :to="m2.url || '/'" :class="selectedClass(m2)">
                <Icon class="nav-icon" :type="m2.icon"/>
                <span>{{m2.text}}</span>
              </router-link>

              <a v-else :href="m2.url" target="_blank">
                <Icon class="nav-icon" :type="m2.icon"/>
                <span>{{m2.text}}</span>
              </a>
            </li>
          </ul>
        </template>

        <router-link v-else-if="!isHttp(m.url)" :to="m.url || '/'" :class="selectedClass(m)">
          <Icon class="nav-icon" :type="m.icon"/>
          <span>{{m.text}}</span>
        </router-link>

        <a v-else :href="m.url" target="_blank">
          <Icon class="nav-icon" :type="m.icon"/>
          <span>{{m.text}}</span>
        </a>
      </li>
    </ul>

    <span class="toggle" @click="expand = !expand">
      <Icon
        style="width: 10px; height: 10px; position: absolute; left: 1px; top: 50%; margin-top: -6px;"
        :type="expand ? 'ios-arrow-dropleft' : 'ios-arrow-dropright'"
        size="12px"
        color="#aaa"
      />
    </span>
  </div>
</template>

<script>
import {
  mapState,
} from 'vuex'
import {
  queryMenus,
  querySubMenus,
} from '@/api/menus'

export default {
  name: 'Menu',

  data() {
    return {
      carte: [],
      expand: true,
      loading: true,
    }
  },

  computed: {
    ...mapState({
      size: 'size',
    }),

    path() {
      return this.$route.fullPath
    },

    menuClass() {
      return {
        'nav-mini': !this.expand,
      }
    },
  },

  methods: {  
    async reload(sub) {
      this.loading = false
      sub = sub || this.$route.query.menu

      this.carte = !sub ? await queryMenus() : await querySubMenus(sub)
      this.carte.forEach(v => {
        if (v.children) {
          v.children.forEach(k => {
            if (this.path === k.url) {
              this.$set(v, 'state', true)
            }
            if (k.children) {
              k.children.forEach(j => {
                if (this.path === j.url) {
                  this.$set(k, 'state', true)
                  this.$set(v, 'state', true)
                }
              })
            }
          })
        }
      })
      // 菜单写死
      let _menuAry = [
        { icon: 'ios-flag',
          id: 'knowledgeBase',
          isInToolbar: 'Y',
          pid: 'APPRFORM',
          text: '知识库',
          url: '/knowledgeBase'}, 
        {
          icon: 'ios-crop',
          id: 'AdminOrder',
          isInToolbar: 'Y',
          pid: 'APPRFORM',
          text: '提交工单',
          url: '/admin/SubmitOrder'
        },
        {
          icon: 'ios-crop',
          id: 'UserOrder',
          isInToolbar: 'Y',
          pid: 'APPRFORM',
          text: '普通用户的提交工单',
          url: '/user/SubmitOrder'
        },
        {
          icon: 'ios-crop',
          id: 'UserQuestion',
          isInToolbar: 'Y',
          pid: 'APPRFORM',
          text: '普通用户的我的工单',
          url: '/user/questions'
        },
        {
          icon: 'ios-crop',
          id: 'MATERIALMODULE',
          isInToolbar: 'Y',
          pid: 'ARPRO',
          text: '我的工单',
          url: '/MaterialModule',
        }]
      Array.prototype.push.apply(this.carte, _menuAry);

      this.loading = true
    },

    showClass(menu) {
      if (menu.state) {
        return {
          'nav-show': true,
        }
      }
    },

    selectedClass(menu) {
      if (this.path === menu.url) {
        return {
          selected: true,
        }
      }
    },

    isHttp(url) {
      return !url || url.indexOf('http') === 0
    },

    toggle(menu, parent) {
      if (menu.state) {
        this.$set(menu, 'state', false)
        return
      }

      if (!parent) {
        this.carte.forEach(m => {
          this.$set(m, 'state', false)
        })
      } else {
        const res = this.carte.filter(d => parent === d.id)

        res[0].children.forEach(m => {
          this.$set(m, 'state', false)
        })
      }
      this.$set(menu, 'state', !menu.state)
    },
  },

  watch: {
    size(size) {
      this.expand = size !== 'small'
    }
  },

  created() {
    this.reload()
  },
}
</script>

<style scoped lang="less">
  .nav {
    width: 220px;
    height: 100%;
    background: #263238;
    transition: all .3s;
    font-size: 14px;
    user-select: none;

    &:hover {
      .toggle {
        right: -18px;
        z-index: -1;
        opacity: 1;
      }
    }

    .toggle {
      position: absolute;
      top: 50%;
      right: 300px;
      height: 64px;
      width: 18px;
      background: #263238;
      margin-top: -64px;
      border-radius: 0 4px 4px 0;
      transition: all .2s;
      cursor: pointer;
      opacity: 0;
    }

    li {
      list-style: none;
      text-indent: 40px;

      li {
        text-indent: 55px;

        li {
          text-indent: 75px;
        }
      }
    }

    a {
      display: block;
      min-height: 45px;
      line-height: 45px;
      color: #ABB1B7;
      transition: all .3s;

      &:before {
        content: "";
        position: absolute;
        left: 0;
        width: 3px;
        height: 46px;
        background: #34A0CE;
        opacity: 0;
        transition: all .3s;
      }

      &:hover,
      &.selected {
        color: #fff;
        background: rgba(0, 0, 0, .1);

        &:before {
          opacity: 1;
        }
      }

      span {
      }
    }

    .nav-item {
      position: relative;

      &.nav-show > ul {
        display: block;
      }

      ul {
        display: none;
      }

      .nav-icon {
        font-size: 20px;
        position: absolute;
        left: -25px;
        line-height: inherit;
        width: 0;
      }

      .nav-more {
        font-size: 16px;
        position: absolute;
        right: 10px;
        line-height: inherit;
      }
    }

    &.nav-mini {
      width: 50px;

      & > ul > li > a span,
      & > ul > li > a .nav-more {
        display: none;
      }

      & .nav-item ul {
        width: 220px;
        top: 0;
        left: 50px;
        position: absolute;
        background: #263238;
        border-left: 1px solid #515a6e;
        display: none;
      }

      & .nav-item ul ul {
        left: 219px;
      }

      & .nav-item:hover > ul {
        display: block;
      }
    }
  }
</style>
