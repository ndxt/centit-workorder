<template>
  <zpa-column height="auto" :id="id">
    <h3>{{title}}</h3>
    <p>
      &nbsp;&nbsp;模拟下拉框、树形下拉框、Radio、Checkbox可选项由后台传入。通过设置属性 <b>query</b> 传入查询方法
    </p>
    <p>
      &nbsp;&nbsp;需要注意，<i>query</i> 方法必须要是 <b>异步（async）</b>的
    </p>
    <zpa-row height="auto">
      <zpa-column>
        <zpa-form>
          <zpa-form-group>
            <zpa-radio-group label="Radio" v-model="obj.radio" :query="queryList" />

            <zpa-checkbox-group label="Checkbox" v-model="obj.checkbox" :query="queryList" />

            <zpa-select label="下拉框" v-model="obj.select" :query="queryList" />

            <zpa-tree-select label="树下拉框" v-model="obj.tree" :query="queryTreeAll" />

            <hr>

            <table>
              <tr><td><b>obj：</b></td> <td>{{obj}}</td></tr>
              <tr><td><b>values：</b></td> <td>{{values}}</td></tr>
              <tr><td><b>trees：</b></td> <td>{{trees2}}</td></tr>
            </table>
          </zpa-form-group>
        </zpa-form>
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
  name: 'FormAsyncDemo',

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

    queryTreeAll() {
      return new Promise(resolve => {
        setTimeout(() => {
          resolve(this.trees)
        }, 2000)
      })
    },
  },

  mounted() {
    setTimeout(() => {
      this.obj = {
        radio: 1,

        checkbox: [1, 2, 3],

        select: 2,

        tree: '12',
      }
    }, 1000)
  },

  data() {
    return {
      id: 'FormAsyncDemo',

      title: '异步加载',

      values: [
        {value: 1, text: '111'},
        {value: 2, text: '222'},
        {value: 3, text: '333'},
      ],

      trees: [
        {
          text: 'parent 1',
          value: '1',
          children: [
            {
              text: 'parent 1-1',
              value: '11',
            },
            {
              text: 'parent 1-2',
              value: '12',
            }
          ]
        }
      ],

      trees2: [
        {
          text: 'parent 1',
          value: '1',
          children: [
            {
              text: 'parent 1-1',
              value: '11',
            },
            {
              text: 'parent 1-2',
              value: '12',
            }
          ]
        }
      ],

      obj: {},

      code:
`<zpa-radio-group label="Radio" v-model="obj.radio" :query="queryList" />

<zpa-checkbox-group label="Checkbox" v-model="obj.checkbox" :query="queryList" />

<zpa-select label="下拉框" v-model="obj.select" :query="queryList" />

<zpa-tree-select label="树下拉框" v-model="obj.tree" :query="queryTreeAll" />`
    }
  },
}
</script>
