<template>
  <Box style="margin: 8px;">
    {{menuId}}
    <TreeList
      ref="Table"
      index
      :columns="menuColumns"
      :query="queryMenu"
      id="optId"
      treeColumnField="text"
    >
      <template slot="fab">
        <AddMenuOperator @addSuccess="addSuccess" title="新增菜单" />
      </template>
      <template slot-scope="props">
        <AddMenuOperator
          v-model="props.row"
          @addSuccess="addSuccess"
          title="新增下级菜单"
        />
        <SetMenuOperator v-model="props.row" @setSuccess="setSuccess" />
        <EditMenuOperator v-model="props.row" @changeSuccess="changeSuccess" />
        <RemoveMenuOperator v-model="props.row" @removeSuccess="removeSuccess" />
      </template>
    </TreeList>
  </Box>
</template>

<script>
  import {queryMenu} from '@/api/admin/menu';
  import menuColumns from './menuColumns';
  import AddMenuOperator from './operators/AddMenuOperator';
  import EditMenuOperator from './operators/EditMenuOperator';
  import RemoveMenuOperator from './operators/RemoveMenuOperator';
  import SetMenuOperator from './operators/SetMenuOperator';

  export default {
  name: 'PageMenu',

  data() {
    return {
      params: {},
      menuId: '',
      menuColumns,
      data: []
    };
  },

  components: {
    AddMenuOperator,
    EditMenuOperator,
    RemoveMenuOperator,
    SetMenuOperator
  },

  methods: {
    queryMenu,

    search() {
      const params = this.params;
      let menuId = this.$route.query.id;
      return this.$refs['Table'].load({ params, id: menuId });
    },
    addSuccess(data) {
      this.$refs['Table'].addData({
        attributes: { external: false },
        icon: data.icon,
        id: data.optId,
        isInToolbar: data.isInToolbar,
        optCode: data.optId,
        optId: data.optId,
        pid: data.preOptId,
        state: 'open',
        text: data.optName,
        url: data.optRoute
      });
    },
    setSuccess(data) {
      this.$refs['Table'].changeData('id', {
        id: data.optId,
        text: data.optName,
        url: data.optRoute,
        icon: data.icon
      });
    },
    removeSuccess(data) {
      this.$refs['Table'].removeData('id', data);
    },
    changeSuccess(data) {
      this.$refs['Table'].changeData('id', {
        id: data.optId,
        text: data.optName,
        url: data.optRoute,
        isInToolbar: data.isInToolbar,
        pid: data.preOptId,
        icon: data.icon
      });
    },
    view(row) {}
  },

  mounted() {
    this.search();
  },
  watch: {
    $route: function() {
      this.search();
    }
  }
};
</script>
