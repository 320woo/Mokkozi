<template>
  <v-container fluid>
    <!-- row의 height를 지정해야만 align을 통해 start, center, end로 배치할 수 있다. -->
    <v-row align="center" style="height: 937px;">
      <v-col class="pr-15">
        <div class="login-box" elevation="1" outlined style="height: 400px;">
          <div class="d-flex flex-wrap justify-center">
            <v-img max-width="400" max-height="200" src="@/assets/text_logo.png"></v-img>
          </div>
          <div>
            <v-text-field label="아이디" outlined></v-text-field>
            <v-text-field label="비밀번호" outlined></v-text-field>
            <v-btn class="mb-3" width="100%" outlined color="#FF9292" @click="login">
              로그인
            </v-btn>
            <v-btn class="mb-3" width="100%" outlined color="#FF9292">
              <v-icon>fab fa-google</v-icon>Google 계정으로 로그인
            </v-btn>
            <v-btn width="100%" outlined color="#FF9292" @click="goToJoin">
              아직 회원이 아니세요? 회원가입하기!
            </v-btn>
          </div>
        </div>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import router from '../../router'
import axios from 'axios'

export default {
  name: 'Login',
  props: {},
  data: () => ({
    user: {
      email: '',
      password: ''
    }
  }),
  methods: {
    goToJoin () {
      router.push('Join')
    },
    login () {
      axios({
        url: 'http://localhost:8000/api/meet/user/login',
        method: 'POST'
      }).then(resp => {
        console.log(resp)
      })
    }

  }
}
</script>

<style lang="scss" scoped>
.login-box {
  border-radius: 30px;
}
</style>
