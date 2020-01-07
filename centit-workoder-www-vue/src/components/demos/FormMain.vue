<template>
  <zpa-column height="auto" :id="id">
    <h3>{{title}}</h3>
    <p>
      &nbsp;&nbsp;一个表单由 <b>Form</b> <b>FormGroup</b> 和 <b>表单元素</b> 组成
    </p>
    <p>
      &nbsp;&nbsp;一个 <b>Form</b> 可以包含多个 <b>FormGroup</b>
    </p>
    <zpa-row height="auto">
      <zpa-column>
        <zpa-form>
          <zpa-form-group title="信息" >
            <zpa-text-input label="姓名" v-model="obj.name" />

            <zpa-number-input label="年龄" v-model="obj.age" />

            <zpa-date-input label="生日" v-model="obj.birthday" />
          </zpa-form-group>

          <zpa-form-group title="成绩" >
            <zpa-number-input label="语文" v-model="obj.score1" />

            <zpa-number-input label="数学" v-model="obj.score2" />

            <zpa-number-input label="英语" v-model="obj.score3" />
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

  data() {
    return {
      id: 'FormMainDemo',

      title: '基本结构',

      obj: {},

      code:
`
<zpa-form>
  <zpa-form-group title="基本信息" >
    <zpa-text-input label="姓名" v-model="obj.name" />
    <zpa-number-input label="年龄" v-model="obj.age" />
    <zpa-date-input label="生日" v-model="obj.birthday" />
  </zpa-form-group>

  <zpa-form-group title="成绩信息" >
    <zpa-number-input label="语文" v-model="obj.score1" />
    <zpa-number-input label="数学" v-model="obj.score2" />
    <zpa-number-input label="英语" v-model="obj.score3" />
  </zpa-form-group>
</zpa-form>
`
    }
  },
}
</script>
