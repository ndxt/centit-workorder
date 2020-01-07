<template>
  <DataList :height="height" ref="Table" index :columns="scopeColumn" :query="query" isHidePage>
    <!--<FAB />-->
    <template slot="fab">
      <AddDataScopeOperator @success="add" />
    </template>
    <template slot-scope="props">
      <EditDataScopeOperator v-model="props.row" @success="row => edit(row, props.index)" />
      <RemoveDataScopeOperator v-model="props.row" @success="remove(props.index)" />
    </template>
  </DataList>
</template>

<script>
import AddDataScopeOperator from './operators/AddDataScopeOperator';
import EditDataScopeOperator from './operators/EditDataScopeOperator';
import RemoveDataScopeOperator from './operators/RemoveDataScopeOperator';

export default {
  name: 'dataScopeList',
  data() {
    return {
      scopeColumn: [
        {
          title: '编码',
          key: 'optScopeCode',
          minWidth: 68
        },
        {
          title: '数据权限名称',
          key: 'scopeName',
          minWidth: 108
        },
        {
          title: '过滤条件',
          key: 'filterCondition',
          minWidth: 268
        },
        {
          title: '过滤说明',
          key: 'scopeMemo',
          minWidth: 108
        }
      ]
    };
  },
  props: {
    value: Object,
    height: Number | String
  },

  components: {
    AddDataScopeOperator,
    EditDataScopeOperator,
    RemoveDataScopeOperator
  },

  methods: {
    query() {
      return this.value ? this.value.dataScopes || [] : [];
    },

    reload() {
      this.$refs['Table'].load();
    },
    add(row) {
      this.value.dataScopes.push(row);
      this.reload();
    },
    edit(row, index) {
      this.$set(this.value.dataScopes, index, row);
      this.reload();
    },
    remove(index) {
      this.value.dataScopes.splice(index, 1);
      this.reload();
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
