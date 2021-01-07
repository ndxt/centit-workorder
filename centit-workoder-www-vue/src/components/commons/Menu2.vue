<template>
  <div class="aside zpa-column">
    <ul class="menu scrollbar">
      <li
        class="menu1"
        style="position: relative;"
        v-for="(m, index) in carte"
        :key="index"
      >
        <!-- 一级菜单Header -->
        <div class="menu-header" @click="toggle(index)" :class="{ active: m.state }">
          <Icon :type="m.icon" :size="24" class="icon" style="margin-right: 4px;"></Icon>
          <span v-if="m.children">{{m.name || m.text}}</span>
          <router-link
            v-if="!m.children"
            :to="{
                path: (m.url || getRoute(m.id).path) || '/',
                query: {
                  menu: $route.query.menu
                },
              }"
          >
            {{m.name || m.text}}
          </router-link>
          <Icon v-if="m.children" class="arrow" v-show="!m.state" type="ios-arrow-forward" :size="14"/>
          <Icon v-if="m.children" class="arrow" v-show="m.state" type="ios-arrow-down" :size="14"/>
        </div>

        <ul
          class="menu-children"
          v-if="m.children && m.children.length"
        >
          <li class="menu-item"
              style="text-align: center; font-weight: 700; padding-left: 0; height: 32px; line-height: 32px;"
              v-if="small">
            {{m.name || m.text}}
          </li>
          <li
            class="menu-item"
            v-for="mm in m.children"
            :key="mm.id"
          >
            <router-link
              style="background: transparent; color: #fff;"
              :to="{
                path: (mm.url || getRoute(mm.id).path) || '/',
                query: {
                  menu: $route.query.menu
                },
              }"
            >
              {{mm.name || mm.text}}
            </router-link>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import {queryMenus, querySubMenus,} from '@/api/menus'

  export default {
  name: 'Menu',

  data() {
    return {
      routes: {},
      carte: [],
    }
  },

  computed: {
    ...mapState({
      size: 'size',
    }),

    small() {
      return this.size === 'small'
    },

    menuClass() {
      const size = this.size
      return {
        large: size === 'large',
        middle: size === 'middle',
        small: size === 'small',
      }
    },
  },

  methods: {
    reload() {
      // console.log('$router', this.$router.options.routes)

      walkMenu(this.$router.options.routes, this)

      function walkMenu(menus, vm) {
        if (!menus) return

        menus.forEach(r => {
          const {meta, name, path, children} = r

          if (children) {
            walkMenu(children, vm)
          }

          vm.routes[name] = {
            name: meta.title,
            path,
          }
        })
      }

      this.getMenus()
    },

    toggle(index) {
      this.carte[index].state = !this.carte[index].state
    },

    getRoute(id) {
      return this.routes[id] || {}
    },

    async getMenus(sub) {
      sub = sub || this.$route.query.menu

      if (!sub) {
        this.carte = await queryMenus()
      } else {
        this.carte = await querySubMenus(sub)
      }
    },
  },

  created() {
    this.reload()
  }
}
</script>

<style scoped lang="less">
  .aside {
    width: 200px;
    background: #111117;

    .menu {
      font-size: 14px;
      color: #e8e8e8;
      overflow: auto;
      flex: 1 1 0;

      .menu-header {
        width: 100%;
        padding-left: 8px;
        height: 50px;
        line-height: 50px;
        font-size: 16px;
        color: #fff;
        transition: background-color .4s;
        position: relative;

        .icon {
          color: white;
        }

        .arrow {
          position: absolute;
          right: 10px;
          top: 13px
        }

      }

      .menu-children {
        width: 100%;

      }

      .menu-item {
        min-width: 160px;
        padding-left: 40px;
        height: 50px;
        line-height: 50px;
        list-style: none;
        font-size: 14px;
        cursor: pointer;

        &:hover,
        &:hover a {
          color: #5d96f5;
          background: #f4f4f4;
        }

        &.router-link-exact-active,
        &.router-link-exact-active a {
          color: white;
          background: #169989;
          border-right: 4px solid #169989;
        }

      }
    }

    &.small {
      width: 60px;

      .menu {
        overflow: visible;
      }

      .menu-header {
        padding-left: 12px;
        height: 60px;
        line-height: 60px;

        .icon {
          font-size: 32px !important;
        }

      }

      .menu1 {
        position: relative;

        &:hover {

          .menu-children {
            width: 160px;
            border: 1px solid #ececec;
            transition: width .4s ease-in-out;
          }

        }
      }

      .menu-header:hover {
        background: #5d96f5;
        color: #fff;
        .icon {
          color: #fff;
        }
      }

      .menu-children {
        overflow: hidden;
        position: absolute;
        width: 0;
        top: 0;
        left: 60px;
        background: #fff;
      }

    }
  }
</style>
