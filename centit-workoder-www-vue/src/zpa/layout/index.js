import './styles/layout.less'
import './styles/shadow.less'
import '../iview/input.less'

import zpaRow from './row'
import zpaColumn from './column'
import zpaBox from './box'

const Layout = {}
Layout.install = function(Vue) {
  Vue.component('zpaRow', zpaRow)
  Vue.component('zpaColumn', zpaColumn)
  Vue.component('zpaBox', zpaBox)
}
export default Layout
