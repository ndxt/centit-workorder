<template>
  <Drawer :closable="true" v-model="value"  width="640" @on-close="close">
    <slot></slot>
    <template slot="header">

      <div class="header">
        <div class="headerTitle">{{title}}</div>
        <div class="drawerButton">
          <Button type="default" @click="nextClick" :disabled="disabledButtonNext" style="margin-right: 20px">下一条</Button>
          <Button type="default" @click="prevClick" :disabled="disabledButtonPrev">上一条</Button>
        </div>
      </div>

    </template>
  </Drawer>
</template>

<script>
export default {
  name: 'DrawerOperator',
  inject: ['top'],
  props: {
    disabledButtonNext: {
      type: Boolean,
      default: true
    },
    disabledButtonPrev: {
      type: Boolean,
      default: true
    },
    drawerShow: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: '抽屉'
    },
    value: {
      type: [Object, Boolean],
    },
  },
  watch: {
    value() {
      this.top.drawerShow = this.value
    }
  },
  methods: {

    nextClick() {
      this.$emit('next')
    },

    prevClick() {
      this.$emit('prev')
    },

    close() {
      this.$emit('drawerClose')
    }

  },
}
</script>
<style>
  .headerTitle{
    font-size: 18px;
    font-weight: bold;
    align-self: center;
  }
  .header{
    display: flex;
    justify-content: space-between;
  }
  .drawerButton{
    margin-right: 30px
  }
</style>
