import Vuex from 'vuex'

const store = new Vuex.Store({
  state: {
    rendered: false,
    token: '',
    size: 'large',
    currentUser: '',
    packetInfo: {},
    datasetInfo: []
  },

  mutations: {
    setToken(state, token) {
      if (token) {
        state.token = token
        localStorage.setItem('accessToken', token)
      }
    },
    routeLoaded(state) {
      state.rendered = true
    },

    setScreenSize(state, size) {
      state.size = size
    },
    setCurrentUser(state, currentUser) {
      state.currentUser = currentUser
    },
    setPacketInfo(state, packetInfo) {
      state.packetInfo = packetInfo
    },
    
    setDatasetInfo(state, datasetInfo) {
      state.datasetInfo = datasetInfo
    }
  },
})

export default store
