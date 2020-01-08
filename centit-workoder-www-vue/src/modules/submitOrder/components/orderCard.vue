<template>
    <div>
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
export default {
  name: 'orderCard',
}
</script>
<style scoped>

</style>
