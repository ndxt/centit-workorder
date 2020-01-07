<template>
  <zpa-column height="auto" :id="id">
    <h3>{{title}}</h3>
    <p>
      &nbsp;&nbsp;数据字典、机构、人员、角色、菜单下拉框等
    </p>
    <zpa-row height="auto">
      <zpa-column>
        <zpa-form>
          <zpa-form-group label-align="top">
            <zpa-switch :span="6" label="开关" v-model="obj.isValid" true-value="T" false-value="F" />

            <DictionaryRadio :span="6" code="CatalogType" label="数据字典Radio" v-model="obj.type" />

            <DictionaryCheckbox :span="6" code="CatalogStyle" label="数据字典Checkbox" v-model="obj.style" />

            <DictionarySelect :span="6" code="StationType" label="数据字典Select" v-model="obj.station" />

            <UserSelect :span="6" tag="T" label="用户Select" v-model="obj.user" />

            <RoleSelect :span="6" tag="G" label="角色Select" v-model="obj.role" />

            <UnitTreeSelect :span="6" tag="T" label="机构树" v-model="obj.unit" />

            <MenuTreeSelect :span="6" tag="A" label="菜单树" v-model="obj.menu" />
          </zpa-form-group>
        </zpa-form>
        <br>
        <p>{{obj}}</p>
      </zpa-column>

      <zpa-column>
        <codemirror v-model="code"></codemirror>
      </zpa-column>
    </zpa-row>

    <br>
  </zpa-column>
</template>

<script>
import demo from './demo'

export default {
  name: 'FormAdminDemo',

  mixins: [demo],

  inject: ['main'],

  methods: {
    queryList() {
      return new Promise(resolve => {
        setTimeout(() => {
          resolve(this.values)
        }, 2000)
      })
    },

    queryTree() {},

    queryTreeAll() {},
  },

  mounted() {
    setTimeout(() => {
      this.obj = {
        isValid: 'T',
        style: ['F'],
        type: 'L',
        unit: 'U00001',
        role: 'sysadmin',
        user: 'u0000000',
        station: 'mang',
        menu: 'DICTSET_M',
      }
    }, 1000)
  },

  data() {
    return {
      id: 'FormAdminDemo',

      title: '业务组件',

      obj: {},

      code: `
<zpa-switch label="开关" v-model="obj.isValid" true-value="T" false-value="F" />

<DictionaryRadio code="CatalogType" label="数据字典Radio" v-model="obj.type" />

<DictionaryCheckbox code="CatalogStyle" label="数据字典Checkbox" v-model="obj.style" />

<DictionarySelect code="StationType" label="数据字典Select" v-model="obj.station" />

<UserSelect tag="T" label="用户Select" v-model="obj.user" />

<RoleSelect tag="G" label="角色Select" v-model="obj.role" />

<UnitTreeSelect tag="T" label="机构树Select" v-model="obj.unit" />
      `
    }
  },
}
</script>
