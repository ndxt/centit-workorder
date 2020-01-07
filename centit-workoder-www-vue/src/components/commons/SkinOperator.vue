<template>
<div>
  <Icon size="28" color="#e8e8e8" type="md-color-palette" @click="show"/>
  <Drawer title="换肤" :closable="false" v-model="drawerShow" width="500">
    <RadioGroup>
      <Radio label="black">
        <img style="vertical-align: middle;" src="../../assets/colorDefault.jpg"/>
      </Radio>
      <Radio label="blue">
        <img style="vertical-align: middle;" src="../../assets/colorBlue.jpg"/>
      </Radio>
      <Radio label="green">
        <img style="vertical-align: middle;" src="../../assets/colorGreen.jpg"/>
      </Radio>
      <Radio label="red" >
        <img style="vertical-align: middle;" src="../../assets/colorRed.jpg"/>
      </Radio>
    </RadioGroup>
  </Drawer>
</div>
</template>

<script>
export default {
  name: 'SkinOperator',

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
    },
  },
}
</script>

<style>

</style>
