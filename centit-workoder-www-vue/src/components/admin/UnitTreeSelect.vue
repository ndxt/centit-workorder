<script>
import {createHOC} from 'vue-hoc'
import {queryUnitTree} from '@/api/admin/cp'
import {queryDepartmentUnit} from '@/api/admin/unit'
import Base from '../../zpa/form/tree-select'

const options = {
  name: 'UnitTreeSelect',

  props: {
    tag: {
      type: String,
      default: 'T'
    },

    department: Boolean,
  },
}

const renderWith = {
  props: {
    query() {
      return () => this.department ? queryDepartmentUnit() : queryUnitTree(this.tag)
    },
    valueField: 'unitCode',
    textField: 'unitName',
  },
}

export default createHOC(Base, options, renderWith)
</script>
