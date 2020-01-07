<script>
import {createHOC} from 'vue-hoc'
import {queryMenuTree} from '@/api/admin/cp'
import Base from '../../zpa/form/tree-select'

const options = {
  name: 'MenuTreeSelect',

  props: {
    tag: {
      type: String,
      default: 'A'
    },
  },
}

const renderWith = {
  props: {
    query() {
      return () => queryMenuTree(this.tag)
    },
    valueField: 'optId',
    textField: 'optName',
  },
}

export default createHOC(Base, options, renderWith)
</script>
