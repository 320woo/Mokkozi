<template>
  <v-container fluid>
    <!-- row의 height를 지정해야만 align을 통해 start, center, end로 배치할 수 있다. -->
    <v-row justify="center" align="center">
      <v-col class="pa-4">
        <div class="login-box" elevation="1" outlined>
          <div class="d-flex flex-wrap justify-center">
            <v-img max-width="400" max-height="200" src="@/assets/text_logo.png"></v-img>
          </div>
          <v-form v-model="formValid">
            <v-text-field label="아이디"
            v-model="credentials.email"
            :rules="[rules.mailRequired, rules.email]"
            outlined>
            </v-text-field>

            <v-text-field label="비밀번호"
            v-model="credentials.password"
            :rules="[rules.pwRequired, rules.min]"
            :type="showPW ? 'text' : 'password'"
            outlined>
            </v-text-field>

            <v-btn class="mb-3" width="100%" outlined color="#FF9292" @click="login">
              로그인
            </v-btn>
            <v-btn class="mb-3" width="100%" outlined color="#FF9292">
              <v-icon>mdi-google</v-icon>Google 계정으로 로그인
            </v-btn>
            <v-btn class="mb-3" width="100%" outlined color="#FF9292" @click="goToJoin">
              아직 계정이 없다면? 회원가입 하세요!
            </v-btn>
          </v-form>
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
    credentials: {
      email: '',
      password: ''
    },
    formValid: false,
    showPW: false,
    rules: {
      mailRequired: value => !!value || '아이디를 입력해 주세요.',
      pwRequired: value => !!value || '비밀번호를 입력해 주세요.',
      min: v => v.length >= 8 || '비밀번호는 8자 이상입니다.',
      email: v => /.+@.+/.test(v) || '유효한 이메일을 입력해 주세요'
    }
  }),
  methods: {
    goToJoin () {
      router.push('Join')
    },
    login () {
      axios({
        url: 'http://localhost:8000/api/meet/user/login',
        method: 'POST',
        data: {
          email: this.credentials.email,
          password: this.credentials.password
        }
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
