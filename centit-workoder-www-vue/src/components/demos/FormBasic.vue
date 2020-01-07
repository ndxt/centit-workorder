<template>
  <zpa-column height="auto" :id="id">
    <h3>{{title}}</h3>
    <p>
      &nbsp;&nbsp;表单元素包含：文本、数字、日期、Radio、Checkbox、Switch、下拉框、树形下拉框等
    </p>
    <zpa-row height="auto">
      <zpa-column>
        <zpa-form>
          <zpa-form-group>
            <zpa-text-input :span="6" label="文本" v-model="obj.name" />

            <zpa-number-input :span="6" label="数字" v-model="obj.age" />

            <zpa-date-input :span="6" label="日期" v-model="obj.birthday" />

            <zpa-date-range-input :span="6" label="日期范围" v-model="obj" startField="startDate" endField="endDate"/>

            <zpa-textarea :span="6" label="描述" v-model="obj.desc" />

            <zpa-radio-group :span="6" label="Radio" v-model="obj.radio" :values="values" />

            <zpa-checkbox-group :span="6" label="Checkbox" v-model="obj.checkbox" :values="values" />

            <zpa-switch :span="6" label="Switch" v-model="obj.switch" />

            <zpa-select :span="6" label="下拉框" v-model="obj.select" :values="values" />

            <zpa-tree-select :span="6" label="树下拉框" v-model="obj.tree" :values="trees" />

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
        <codemirror v-model="code" style="height: 100px;"></codemirror>
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
        name: 'hello world',

        age: 18,

        birthday: '2018-08-08',

        switch: false,

        radio: 1,

        checkbox: [1, 2, 3],

        select: 2,

        tree: '12',
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
`<zpa-text-input label="文本" v-model="obj.name" />

<zpa-number-input label="数字" v-model="obj.age" />

<zpa-date-input label="日期" v-model="obj.birthday" />

<zpa-date-range-input label="日期范围" v-model="obj" startField="startDate" endField="endDate"/>

<zpa-textarea label="描述" v-model="obj.desc" />

<zpa-radio-group label="Radio" v-model="obj.radio" :values="values" />

<zpa-checkbox-group label="Checkbox" v-model="obj.checkbox" :values="values" />

<zpa-switch label="Switch" v-model="obj.switch" />

<zpa-select label="下拉框" v-model="obj.select" :values="values" />

<zpa-tree-select label="树下拉框" v-model="obj.tree" :values="trees" />`
    }
  },
}
</script>
