// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import App from './App'
import store from './store'
import routers from './router'

import FormView from 'centit-form-view'
import Layout from './zpa/layout'
import Form from './zpa/form'

import MainLayout from './components/layout/MainLayout'
import AsideLayout from './components/layout/AsideLayout'
import SearchLayout from './components/layout/SearchLayout'

import Box from './components/commons/Box'
import SearchBox from './components/commons/SearchBox'
import DataList from './components/commons/DataList'
import TreeList from './components/commons/TreeList'
import PapersList from './components/commons/PapersList'
import FAB from './components/commons/FAB'
import DrawerOperator from './components/commons/DrawerOperator'
import ModalOperator from './components/commons/ModalOperator'
import SkinOperator from './components/commons/SkinOperator'
import ConfirmOperator from './components/commons/ConfirmOperator'
import TabList from './components/commons/TabList'
import PowerList from './components/commons/PowerList'
import TreeTransfer from './components/commons/TreeTransfer'

import DictionaryRadio from './components/admin/DictionaryRadio'
import DictionaryCheckbox from './components/admin/DictionaryCheckbox'
import DictionarySelect from './components/admin/DictionarySelect'
import UserSelect from './components/admin/UserSelect'
import RoleSelect from './components/admin/RoleSelect'
import UnitTreeSelect from './components/admin/UnitTreeSelect'
import MenuTreeSelect from './components/admin/MenuTreeSelect'
import Filters from './components/commons/filters/globalFilters'
import centitIM from './plugins/centitIM/centitIM.js'
import '../static/css/colorDefault.css' /* 引入公共样式 */
import '../static/css/styleDefault.css' /* 引入公共样式 */
import 'babel-polyfill'
import es6Promise from 'es6-promise'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import VueUeditorWrap from 'vue-ueditor-wrap'

import VueCron from 'vue-cron'

Vue.use(ElementUI)
Vue.use(VueCron)
es6Promise.polyfill()

Vue.config.productionTip = false

Vue.use(FormView)
Vue.use(Layout)
Vue.use(centitIM)
Vue.use(Form)
Object.keys(Filters).forEach(key => {
  Vue.filter(key, Filters[key])
})
Vue.component('MainLayout', MainLayout)
Vue.component('AsideLayout', AsideLayout)
Vue.component('SearchLayout', SearchLayout)

Vue.component('Box', Box)
Vue.component('SearchBox', SearchBox)
Vue.component('DataList', DataList)
Vue.component('TreeList', TreeList)
Vue.component('PapersList', PapersList)
Vue.component('FAB', FAB)
Vue.component('ModalOperator', ModalOperator)
Vue.component('DrawerOperator', DrawerOperator)
Vue.component('SkinOperator', SkinOperator)
Vue.component('ConfirmOperator', ConfirmOperator)
Vue.component('TabList', TabList)
Vue.component('PowerList', PowerList)
Vue.component('TreeTransfer', TreeTransfer)

Vue.component('DictionaryRadio', DictionaryRadio)
Vue.component('DictionaryCheckbox', DictionaryCheckbox)
Vue.component('DictionarySelect', DictionarySelect)
Vue.component('UserSelect', UserSelect)
Vue.component('RoleSelect', RoleSelect)
Vue.component('UnitTreeSelect', UnitTreeSelect)
Vue.component('MenuTreeSelect', MenuTreeSelect)
Vue.component('vue-ueditor-wrap', VueUeditorWrap)
Vue.osId = 'FILE_SVR'
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: routers,
  components: {
    App,
  },
  template: '<App/>',
  store,
})
