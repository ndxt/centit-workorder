<template>
    <div>
        <AddOrderCategoryOperator/>
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
    </div>
</template>
<script>
// 做成选择。。。选择。。。的形式
import AddOrderCategoryOperator from './components/AddOrderCategoryOperator'

export default {
  name: 'admin',
  components: {
    AddOrderCategoryOperator
  },
  data() {
    return {
      addOrderModal: false,
      nameVal: '',
      sortVal: '',
      describeVal: ''
    }
  },
  methods: {
    ok () {
      //         catalogDescribe: "234"
      // catalogName: "bbbb"
      // icon: "glyphicon-home"
      // osId: "N002"
      // sort: "1"
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
