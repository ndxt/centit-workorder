<template>
  <div>
    <Icon size="28" color="#e8e8e8" type="md-notifications" @click="show"/>
    <Drawer title="消息提示框" :closable="false" v-model="drawerShow" width="500">
      消息提示框
    </Drawer>
  </div>
</template>

<script>
export default {
  name: 'Message',

  data() {
    return {
      drawerShow: false
    }
  },

  props: {
    ghost: {
      type: Boolean,
      default: true
    },
  },

  methods: {
    show() {
      this.drawerShow = !this.drawerShow
      /* 可以自行查询操作 */
      console.log('查询得到的数据')
    },
  },
}
</script>

<style>

</style>
