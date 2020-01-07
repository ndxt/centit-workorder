import IM from './IM'
import $ from 'jquery'

const centitIM = {}

centitIM.install = function(Vue) {
  Vue.prototype.$centitIM = (user) => {
    Vue.IM = new IM(user)
  }

  Vue.prototype.$closeCentitIM = () => {
    if (Vue.IM) {
      Vue.IM.socket.close()
      delete Vue.IM
      $('.layui-layer').each((index, ele) => {
        let time = ele.getAttribute('times')
        if (time) {
          let layer = window.layer
          layer.close(time)
        }
      })
    }
  }
}

export default centitIM
