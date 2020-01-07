<script>
import {createHOC} from 'vue-hoc'
import {queryDictionaryDetails} from '@/api/admin/cp'
import Base from '../../zpa/form/checkbox-group'

const options = {
  name: 'DictionaryCheckbox',

  props: {
    code: {
      type: String,
      required: true,
    },
  },
}

const renderWith = {
  props: {
    query() {
      return () => queryDictionaryDetails(this.code)
    },
    valueField: 'dataCode',
    textField: 'dataValue',
  },
}

export default createHOC(Base, options, renderWith)
</script>
