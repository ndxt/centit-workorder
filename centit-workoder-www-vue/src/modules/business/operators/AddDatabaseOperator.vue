<template>
  <ModalOperator fab customButton ref="ModalOperator" title="新增数据库">
    <zpa-form ref="Form">
      <DatabaseBaseInfo v-model="data"/>
    </zpa-form>

    <div slot="customBtn">
      <Button type="primary" @click="link">链接测试</Button>
      <Button type="primary" @click="ok">添加</Button>
      <Button type="text" @click="close">取消</Button>
    </div>
  </ModalOperator>
</template>

<script>
  import {addDatabase, testConnectOs,} from '@/api/admin/business'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import DatabaseBaseInfo from '../DatabaseBaseInfo'

  export default {
  name: 'AddDatabaseOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    DatabaseBaseInfo,
  },

  methods: {
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    async link() {
      let _this = this
      const result = await _this.beforeSubmit()

      if (result) {
        testConnectOs(this.data).then(function (testData) {
          _this.$Message.success('数据库连接测试成功！')
        },
        function (reason, testData) {
          _this.$Message.error('数据库连接测试失败！')
        })
      }
    },

    ok() {
      return this.$refs['ModalOperator'].ok()
    },

    close() {
      return this.$refs['ModalOperator'].close()
    },

    onSubmit() {
      return addDatabase(this.data)
    },
  },
}
</script>
