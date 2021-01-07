<template>
  <DataList ref="Table" index :columns="scopeColumn" :query="query" isHidePage :height="height">
    <template slot="fab">
      <AddOptMethodOperator @success="add" />
    </template>
    <template slot-scope="props">
      <EditOptMethodOperator v-model="props.row" @success="row => edit(row, props.index)" />
      <RemoveOptMethodOperator v-model="props.row" @success="remove(props.index)" />
    </template>
  </DataList>
</template>

<script>
  import AddOptMethodOperator from './operators/AddOptMethodOperator';
  import EditOptMethodOperator from './operators/EditOptMethodOperator';
  import RemoveOptMethodOperator from './operators/RemoveOptMethodOperator';

  export default {
  name: 'optMethodList',
  data() {
    return {
      scopeColumn: [
        {
          title: '编码',
          key: 'optCode',
          minWidth: 88
        },
        {
          title: '名称',
          key: 'optName',
          minWidth: 78
        },
        {
          title: '请求地址',
          key: 'optUrl',
          minWidth: 88
        },
        {
          title: '请求方法',
          key: 'optMethod',
          minWidth: 88
        },
        {
          title: '请求类型',
          key: 'optReq',
          minWidth: 88
        },
        {
          title: '操作权限说明',
          key: 'optDesc',
          minWidth: 128
        }
      ]
    };
  },
  props: {
    value: Object,
    height: Number
  },
  components: {
    AddOptMethodOperator,
    EditOptMethodOperator,
    RemoveOptMethodOperator
  },

  methods: {
    query() {
      return this.value ? this.value.optMethods || [] : [];
    },
    add(row) {
      this.value.optMethods.push(row);
      this.reload();
    },
    edit(row, index) {
      this.$set(this.value.optMethods, index, row);
      this.reload();
    },
    remove(index) {
      this.value.optMethods.splice(index, 1);
      this.reload();
    },
    reload() {
      this.$refs['Table'].load();
    }
  },

  mounted() {
    this.reload();
  },

  watch: {
    value() {
      this.reload();
    }
  }
};
</script>
