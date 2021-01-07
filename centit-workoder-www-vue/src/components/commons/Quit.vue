<template>
  <ConfirmOperator
    type="primary"
    iconType="md-exit"
    iconSize="18"
    text="退出登录"
    title="退出登录"
    :content="content"
  />
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import router from '@/router/index'

  export default {
  name: 'Quit',
  mixins: [
    ModalOperatorMixin,
  ],

  components: {},

  computed: {
    content() {
      return '确定退出登录吗？'
    },
  },
  methods: {
    onSubmit() {
      this.$closeCentitIM()
      router.replace({
        name: 'login',
      })
    },
  },
}
</script>
