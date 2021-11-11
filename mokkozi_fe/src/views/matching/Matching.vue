<template>
  <div style="height: 500px;">
    <h1>매칭 페이지입니다.</h1>
    <v-btn
      elevation="2"
      @click="testingJWT"
    ></v-btn>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name : 'Matching',
  props: {},
  data: () => ({
    test: 'test'
  }),
  methods: {
    testingJWT () {
      console.log("테스트합니다.")
      console.log("토큰 정보 : ", this.$store.state.jwt)
      axios({
        url: 'http://localhost:8000/api/meet/user/test',
        method: "POST",
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
