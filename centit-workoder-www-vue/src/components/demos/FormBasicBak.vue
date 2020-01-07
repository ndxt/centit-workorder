<template>
  <zpa-column height="auto" :id="id">
    <h3>{{title}}</h3>
    <p>
      &nbsp;&nbsp;表单元素包含：文本、数字、日期等
    </p>
    <zpa-row height="auto">
      <zpa-column>
        <zpa-form>
          <zpa-form-group>
            <zpa-text-input
              label="文本"
              v-model="obj.name"
            />

            <zpa-number-input
              label="数字"
              v-model="obj.age"
            />

            <zpa-date-input
              label="日期"
              v-model="obj.birthday"
            />

            <zpa-radio-group
              label="Radio"
              v-model="obj.radio"
              :query="queryList"
            />

            <zpa-checkbox-group
              label="Checkbox"
              v-model="obj.checkbox"
              :query="queryList"
            />

            <zpa-checkbox-group
              number
              transform
              label="Checkbox"
              v-model="obj.checkbox2"
              :query="queryList"
            />

            <zpa-switch
              label="Switch"
              v-model="obj.switch"
            >
              <Icon type="md-checkmark" slot="open"></Icon>
              <Icon type="md-close" slot="close"></Icon>
            </zpa-switch>

            <zpa-switch
              label="Switch"
              v-model="obj.switch2"
              trueValue="T"
              falseValue="F"
            >
            </zpa-switch>

            <zpa-select
              label="下拉框"
              v-model="obj.select"
              :query="queryList"
            ></zpa-select>

            <zpa-select
              label="自定义下拉框"
              v-model="obj.select11"
              :query="queryList"
            >
              <template slot-scope="props">
                {{props.row.text}}({{props.row.value}})
              </template>
            </zpa-select>

            <zpa-select
              label="多选"
              multiple
              v-model="obj.select2"
              :query="queryList"
            ></zpa-select>

            <zpa-select
              label="多选转String"
              transform
              number
              multiple
              v-model="obj.select3"
              :query="queryList"
            ></zpa-select>

            <zpa-select
              label="带搜索下拉框"
              filterable
              v-model="obj.select4"
              :query="queryList"
            ></zpa-select>

            <zpa-select
              label="带搜索多选"
              required
              filterable
              multiple
              v-model="obj.select5"
              :query="queryList"
            >
              <template slot-scope="props">
                {{props.row.text}}({{props.row.value}})
              </template>
            </zpa-select>

            <zpa-tree-select
              label="树下拉(多选)"
              filterable
              multiple
              v-model="obj.tree"
              :values="trees"
            >
            </zpa-tree-select>

            <zpa-tree-select
              label="树下拉(单选)"
              v-model="obj.tree2"
              :values="trees"
            >
            </zpa-tree-select>

            <hr>

            <p>{{obj}}</p>
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
  name: 'FormBasicDemo',

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
        switch: false,

        radio: 8,

        checkbox: [1, 2, 3],

        checkbox2: '3,4',

        select: 2,

        select11: 4,

        select2: [1, 2],

        select3: '2,3',

        select4: 5,

        select5: [2, 5, 6],

        tree2: '111',

        tree: [
          '1',
          '111',
          '112',
          '121',
        ]
      }
    }, 1000)
  },

  data() {
    return {
      id: 'FormBasicDemo',

      title: '表单元素',

      values: [
        {value: 1, text: '111'},
        {value: 2, text: '222'},
        {value: 3, text: '333'},
        {value: 4, text: '444'},
        {value: 5, text: '555'},
        {value: 6, text: '666'},
        {value: 7, text: '777'},
        {value: 8, text: '888'},
        {value: 9, text: '999'},
      ],

      trees: [
        {
          text: 'parent 1',
          value: '1',
          _expand: false,
          children: [
            {
              text: 'parent 1-1',
              value: '11',
              _expand: false,
              children: [
                {
                  text: 'leaf 1-1-1',
                  value: '111',
                },
                {
                  text: 'leaf 1-1-2',
                  value: '112',
                }
              ]
            },
            {
              text: 'parent 1-2',
              value: '12',
              _expand: false,
              children: [
                {
                  text: 'leaf 1-2-1',
                  value: '121',
                },
                {
                  text: 'leaf 1-2-2',
                  value: '122',
                }
              ]
            }
          ]
        }
      ],

      obj: {},

      code: `
<!-- 文本 -->
<zpa-text-input label="文本" v-model="obj.name" />

<!-- 数字 -->
<zpa-number-input label="数字" v-model="obj.age" />

<!-- 日期 -->
<zpa-date-input label="日期" v-model="obj.birthday" />`
    }
  },
}
</script>
