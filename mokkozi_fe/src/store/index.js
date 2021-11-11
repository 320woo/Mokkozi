import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    jwt: {
      type: String
    },
  },
  // Actions에서 commit() 함수를 통해 호출. state를 직접적으로 수정한다.
  mutations: {
    setJwt(state, jwt) {
      console.log("mutations.js : payload : ", jwt)
      state.jwt = jwt
    }
  },
  // 항상 context가 인자로 넘어온다. 단, 직접적으로 state를 변경하지는 않는다. dispatch()를 통해 호출한다.
  actions: {
    setJwt(context, jwt) {
      console.log("actions.js : setJwt")
      console.log("actions.js : jwt : ", jwt)
      context.commit('setJwt', jwt)
    }
  },
  modules: {

  },
  plugins: [createPersistedState()]
})
