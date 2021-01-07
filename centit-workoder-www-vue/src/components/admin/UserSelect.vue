<script>
  import {createHOC} from 'vue-hoc'
  import {queryUser} from '@/api/admin/cp'
  import {queryDepartmentUser} from '@/api/admin/user'
  import Base from '../../zpa/form/select'

  const options = {
  name: 'UserSelect',

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
      return () => this.department ? queryDepartmentUser(this.tag) : queryUser(this.tag)
    },
    valueField: 'userCode',
    textField: 'userName',
  },
}

export default createHOC(Base, options, renderWith)
</script>
