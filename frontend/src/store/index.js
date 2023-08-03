import { createStore } from 'vuex'
import router from '@/router'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
    logout() {
      window.sessionStorage.clear()
      window.localStorage.clear()
      router.replace('/login')
    }
  },
  modules: {
  }
})
