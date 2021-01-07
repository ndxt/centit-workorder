<template>
    <DataList
      ref="Table"
      index
      :operWidth="80"
      :columns="demoColumns"
      :query="loadDemoList"
    >
      <template slot-scope="props">
        <!--<AddProOne v-model="props.row" @success="reload"/>-->
        <component v-bind:is="props.row.component" v-model="props.row" @success="reload"/>
      </template>
    </DataList>
</template>

<script>
  import AddProOne from './AddProOne'
  import AddProTwo from './AddProTwo'
  import AddProThree from './AddProThree'

  import {loadDemoList,} from '@/api/task/apprdemo'

  export default {
  name: 'PageDemo',

  data() {
    return {
      demoColumns: [
        {
          title: '事项名称',
          key: 'name',
          sortable: 'custom',
          minWidth: 110,
        },
      ],

      params: {},

    }
  },

  components: {
    AddProOne,
    AddProTwo,
    AddProThree,
  },

  methods: {
    loadDemoList,
    reload() {
      const params = this.params

      this.$refs['Table'].load({params})
    },

    // ${this.$route.path}
  },

  mounted() {
    this.$nextTick(() => {
      this.reload()
    })
  },
}
</script>

<style lang="less">
  .viewFlow {
    border: 1px solid #2db7f5;
    color: #2db7f5;
    padding: 2px 5px;
    background: rgba(0, 0, 0, 0);
    border-radius: 3px;
    cursor: pointer;
    vertical-align: middle;
  }
</style>
