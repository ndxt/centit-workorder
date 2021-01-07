<template>
  <AsideLayout no-gutter>
    <SearchLayout>
      <SearchBox slot="search" @search="reload" @reset="reset">
        <zpa-text-input label="编码" v-model="params.roleCode" />
        <zpa-text-input label="名称" v-model="params.roleName" />
        <zpa-date-range-input
          label="创建时间"
          v-model="params"
          startField="createDateBeg"
          endField="createDateEnd"
        />
        <zpa-radio-group label="状态" v-model="params.isValid" :values="isValidValues" />
      </SearchBox>

      <DataList
        ref="Table"
        index
        :operWidth="170"
        :columns="roleColumns"
        :query="!dept ? getSubSysRole : queryDeptRole"
        @on-selected-change="selectedChange"
      >
        <template slot="createDate" slot-scope="{value}">{{value | day}}</template>
        <template slot="fab">
          <AddRoleOperator @success="reload" :dept="dept" />
        </template>
        <template slot-scope="props">
          <EditRoleOperator v-model="props.row" :dept="dept" @success="reload" />
          <RolePowerOperator v-model="props.row" />
          <RemoveRoleOperator v-model="props.row" @success="reload" />
        </template>
      </DataList>
    </SearchLayout>

    <PageRoleAside v-if="currentRole" slot="aside" :role="currentRole" />
  </AsideLayout>
</template>

<script>
  import roleColumns from './columns/roleColumns';

  import AddRoleOperator from './operators/AddRoleOperator';
  import EditRoleOperator from './operators/EditRoleOperator';
  import RolePowerOperator from './operators/RolePowerOperator';
  import RemoveRoleOperator from './operators/removeRoleOperator';

  import PageRoleAside from './PageRoleAside';

  import {queryDeptRole, queryRole, querySubSysRole} from '@/api/admin/role';

  export default {
  name: 'PageRole',

  props: {
    dept: Boolean
  },

  provide() {
    return {
      top: this
    };
  },

  data() {
    return {
      roleColumns,

      params: {},

      currentRole: undefined,

      isValidValues: [
        {
          value: '',
          text: '全部'
        },
        {
          value: 'T',
          text: '在用'
        },
        {
          value: 'F',
          text: '禁用'
        }
      ]
    };
  },

  components: {
    AddRoleOperator,
    EditRoleOperator,
    RolePowerOperator,
    RemoveRoleOperator,
    PageRoleAside
  },

  methods: {
    queryRole,
    queryDeptRole,
    getSubSysRole() {
      let subSystemROLE = this.$route.query.id;
      if (subSystemROLE) {
        return querySubSysRole({
          params: {
            topOptId: subSystemROLE
          }
        });
      } else {
        return queryRole();
      }
    },
    reset() {
      this.params = {};
      this.reload();
    },
    reload() {
      const params = this.params;
      this.$refs['Table'].load({ params });
    },

    selectedChange(data) {
      this.currentRole = data;
    }
  },
  watch: {
    $route: function() {
      this.reload();
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.reload();
    });
  }
};
</script>
