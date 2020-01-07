<script>
import {createHOC} from 'vue-hoc'
import {queryRole} from '@/api/admin/cp'
import {queryDepartmentRole} from '@/api/admin/role'
import Base from '../../zpa/form/select'

const options = {
  name: 'RoleSelect',

  props: {
    tag: {
      type: String,
      default: 'G'
    },

    department: Boolean,
  },
}

const renderWith = {
  props: {
    query() {
      return () => this.department ? queryDepartmentRole() : queryRole(this.tag)
    },
    valueField: 'roleCode',
    textField: 'roleName',
  },
}

export default createHOC(Base, options, renderWith)
</script>
