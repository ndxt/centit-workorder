<template>
  <ul class="breadcrumb">
    <li @click="refresh">{{$route.meta.title}}</li>
    <li
      class="item"
      v-for="(v, i) in data"
      v-if="data.length"
      :key="v.site"
      @click="changeSite(v.site, i + 1)"
    >
      <b>／</b>
      {{v.name}}
    </li>
  </ul>
</template>

<script>
export default {
  name: 'Breadcrumb',

  data() {
    return {
      data: [],
    }
  },

  computed: {},

  methods: {
    refresh() {
      location.reload(true)
    },

    changeSite(site, index) {
      if (this.data.length === index) {
        return false
      }

      this.$router.push({
        path: this.$route.path,
        query: {
          site,
          noFolder: true,
        }
      })
    },
  },

  watch: {
    '$route'(now, old) {
      this.data = []
      if (now.query.site) {
        let site = ''
        const res = now.query.site.split('_')

        res.forEach(v => {
          site += site !== '' ? `_${v}` : v
          this.data.push({
            site,
            name: v,
          })
        })
      }
    }
  },

  created() {
    this.$router.push({
      path: this.$route.path,
    })
    this.$root.$on('changeB')
  },
}
</script>

<style scoped lang="less">
  .breadcrumb {
    margin: 0 0 8px 10px;
    font-size: 16px;
    color: #666;
    display: flex;
    flex-direction: row;

    li {
      list-style: none;
      padding: 0 5px;
      border-bottom: 1px solid rgba(0, 0, 0, 0);
      position: relative;
      &:not(:last-of-type) {
        cursor: pointer;
      }
      &:not(:last-of-type):hover {
        border-bottom-color: #4285F4;
      }
      &.item {
        margin-left: 10px;
      }
    }
    b {
      position: absolute;
      left: -12px;
      top: -1px;
    }
  }
</style>
