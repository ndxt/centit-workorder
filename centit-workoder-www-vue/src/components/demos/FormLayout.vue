<template>
  <zpa-column height="auto" :id="id">
    <h3>{{title}}</h3>
    <p>
      &nbsp;&nbsp;我们把表单平均分为 <b>12</b> 列，默认表单元素占整行。可以通过设置 <b>span</b> 属性修改表单元素宽度。
    </p>
    <zpa-row height="auto">
      <zpa-column>
        <zpa-form labelAlign="top">
          <zpa-form-group>
            <zpa-text-input label="整行" />

            <zpa-text-input :span="6" label="1 / 2" />
            <zpa-text-input :span="6" label="1 / 2" />

            <zpa-text-input :span="4" label="1 / 3" />
            <zpa-text-input :span="4" label="1 / 3" />
            <zpa-text-input :span="4" label="1 / 3" />

            <zpa-text-input :span="4" label="1 / 3" />
            <zpa-text-input :span="8" label="2 / 3" />

            <zpa-text-input :span="6" :push="6" label="1 / 2 push" />

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
  name: 'FormLayoutDemo',

  mixins: [demo],

  inject: ['main'],

  data() {
    return {
      id: 'FormLayoutDemo',

      title: '表单布局',

      code:
`
<zpa-text-input label="整行" />

<zpa-text-input :span="6" label="1 / 2" />
<zpa-text-input :span="6" label="1 / 2" />

<zpa-text-input :span="4" label="1 / 3" />
<zpa-text-input :span="4" label="1 / 3" />
<zpa-text-input :span="4" label="1 / 3" />

<zpa-text-input :span="4" label="1 / 3" />
<zpa-text-input :span="8" label="2 / 3" />

<zpa-text-input :span="6" :push="6" label="1 / 2 push" />
`
    }
  },
}
</script>
