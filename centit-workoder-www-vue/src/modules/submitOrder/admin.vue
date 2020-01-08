<template>
    <div>
        <Button icon="md-add" class="add-order-category" @click=" addOrderModal = true ">新增一级工单类别</Button>
        <Card style="width:350px">
        <p slot="title">
            <Icon type="ios-film-outline"></Icon>
            Classic film1
        </p>
        <a href="#" slot="extra" @click.prevent="changeLimit">
            <Icon type="ios-loop-strong"></Icon>
            Change
        </a>
        <!-- <ul>
            <li v-for="item in randomMovieList">
                <a :href="item.url" target="_blank">{{ item.name }}</a>
                <span>
                    <Icon type="ios-star" v-for="n in 4" :key="n"></Icon><Icon type="ios-star" v-if="item.rate >= 9.5"></Icon><Icon type="ios-star-half" v-else></Icon>
                    {{ item.rate }}
                </span>
            </li>
        </ul> -->
        </Card>
        <Modal
        v-model="addOrderModal"
        title="新增一级工单类型"
        @on-ok="ok"
        @on-cancel="cancel">
        <p>Content of dialog</p>
        <p>Content of dialog</p>
        <p>Content of dialog</p>
    </Modal>
    </div>
</template>
<script>
// 做成选择。。。选择。。。的形式
export default {
  name: 'admin',
  data() {
    return {
      addOrderModal: false
    }
  },
  methods: {
    ok () {
      this.$Message.info('Clicked ok');
    },
    cancel () {
      this.$Message.info('Clicked cancel');
    }
  }
}
</script>
<style scoped>
.add-order-category {
    font-size: 18px;
}
</style>
