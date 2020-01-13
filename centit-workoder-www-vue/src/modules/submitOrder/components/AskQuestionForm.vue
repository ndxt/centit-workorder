<template>
  <zpa-form-group>
    <Divider>您可能需要的文章</Divider>
    <ul class="doc-list">
      <li v-for="doc in docList" :key="doc.docId">
        {{doc.title}}
      </li>
    </ul>
    <Button type="primary" long>我有其它问题</Button>
    <QuestionForm v-model="questionInfo" :category="value"/>
  </zpa-form-group>
</template>

<script>
import QuestionForm from './QuestionForm'
import {
  listFlowOptInfo,
} from '@/api/workflow/flowDefine'

export default {
  name: 'AskQuestionForm',
  
  data() {
    return {
      questionInfo: {
        questionTitle: '',
        catalogId: this.value.catalogId,
        questionContent: ''
      }
    }
  },
  props: {
    value: Object,
    docList: Array,
  },

  components: {
    QuestionForm
  },
  methods: {
    listFlowOptInfo,
  },
}
</script>
<style scoped>
.doc-list {
  margin-bottom: 30px;
}
</style>
