import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    jwt: {
      type: String
    },
    user: {
      nickname: '',
      profile: '',
      email: '',
      address: '',
      show: false,
      followers: '',
      following: ''
    },
    role: '',
  },
  // Actions에서 commit() 함수를 통해 호출. state를 직접적으로 수정한다.
  mutations: {
    setJwt(state, jwt) {
      state.jwt = jwt
      state.user.show = true;
    },
    setNickname(state, nickname) {
      state.user.nickname = nickname
    },
    setProfile(state, profile) {
      state.user.profile = profile
    },
    setEmail(state, email) {
      state.user.email = email;
    },
    setAddress(state, address) {
      state.user.address = address;
    },
    logOut(state) {
      state.user.show = false;
    },
    setFollowers(state, followers) {
      state.user.followers = followers;
    },
    setFollowing(state, following) {
      state.user.following = following;
    }
  },
  // 항상 context가 인자로 넘어온다. 단, 직접적으로 state를 변경하지는 않는다. dispatch()를 통해 호출한다.
  actions: {
    setJwt(context, jwt) {
      context.commit('setJwt', jwt)
    },
    setNickname(context, nickname) {
      context.commit('setNickname', nickname)
    },
    setProfile(context, profile) {
      context.commit('setProfile', profile)
    },
    setEmail(context, email) {
      context.commit('setEmail', email)
    },
    setAddress(context, address) {
      context.commit('setAddress', address)
    },
    logout(context) {
      context.commit('logOut');
    },
    setFollowers(context,followers) {
      context.commit('setFollowers', followers)
    },
    setFollowing(context,following) {
      context.commit('setFollowing', following)
    }
  },
  modules: {

  },
  plugins: [createPersistedState()]
})
