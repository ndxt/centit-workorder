<template>
  <div style="display: inline-block">
    <Icon v-if="buttonType==='Icon'"  :size="iconSize" :color="iconColor" :type="iconType" @click="open"/>
    <Button v-if="buttonType==='Button'"  type="default" :disabled="isDisabled"  slot="btn"  @click="save" >保存</Button>
  </div>
</template>

<script>
export default {
  name: 'MyButton',
  inject: ['top'],
  props: {
    value: Object,
    iconType: {
      type: String,
      default: 'ios-create'
    },
    iconSize: {
      type: String,
      default: '28'
    },
    iconColor: {
      type: String,
      default: '#169989'
    },
    buttonType: {
      type: String,
      default: 'Icon'
    },
    isDisabled: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    open() {
      /* this.$emit('startEdit') */
    },
    save() {
      this.$emit('editSave', this.value)
    }
  },
}
</script>
