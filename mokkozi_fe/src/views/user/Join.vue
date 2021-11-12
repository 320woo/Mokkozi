<template>
    <v-container fluid>
      <v-row justify="center" align="center" style="width: 600px; height: 750px; overflow-y: auto;">
        <v-col>
          <h3>기본 정보 입력</h3>
          <validation-observer
            ref="observer"
            v-slot="{ invalid }"
          >
            <form @submit.prevent="submit">
              <!-- 이메일 입력란 -->
              <ValidationProvider name="이메일" rules="required|emailValidate" v-slot="{ errors }">
                <v-text-field
                label="이메일"
                v-model="joinInfo.email"
                :error-messages="errors"
                placeholder="이메일을 입력하세요."
                required
              ></v-text-field>
              </ValidationProvider>

              <!-- 닉네임 -->
              <ValidationProvider name="닉네임" rules="required" v-slot="{ errors }">
                <v-text-field
                  label="닉네임"
                  v-model="joinInfo.nickName"
                  :error-messages="errors"
                  placeholder="닉네임을 입력하세요."
                ></v-text-field>
              </ValidationProvider>

              <ValidationProvider name="비밀번호" rules="required" v-slot="{ errors }">
                <v-text-field
                  label="비밀번호"
                  v-model="joinInfo.password"
                  :error-messages="errors"
                  placeholder="최소 8자, 숫자와 특수문자 결합"
                  type="password"
                ></v-text-field>
              </ValidationProvider>

              <v-row no-gutters>
                <v-col cols="12">
                  <ValidationProvider name="주소" rules="required" v-slot="{ errors }">
                    <v-text-field
                    label="주소"
                    v-model="joinInfo.address"
                    :error-messages="errors"
                    placeholder="'주소찾기'를 클릭해 주세요" readonly
                    @click="daumPostCode" style="cursor: pointer;"
                    ></v-text-field>
                  </ValidationProvider>

                  <ValidationProvider name="상세주소" rules="required" v-slot="{ errors }">
                    <v-text-field
                    label="상세주소"
                    v-model="joinInfo.extAddress"
                    :error-messages="errors"
                    placeholder="상세 주소를 입력하세요."
                    ></v-text-field>
                  </ValidationProvider>
                </v-col>
                <v-col cols="8">
                  <v-btn
                    class="mb-2"
                    rounded
                    color="#FF9292"
                    @click="daumPostCode"
                  >
                    주소찾기
                  </v-btn>
                </v-col>
                <ValidationProvider name="성별" rules="required" v-slot="{ errors }">
                  <v-select
                    :items="genderItems"
                    v-model="joinInfo.gender"
                    :error-messages="errors"
                    label="성별"
                  ></v-select>
                </ValidationProvider>
                <v-file-input
                  v-model="joinInfo.profile"
                  truncate-length="15"
                  accept="image/*"
                  prepend-icon="mdi-camera"
                  :rules="ProfileRules"
                  placeholder="프로필 이미지를 설정해 주세요"
                  show-size="1024"
                >
                <template v-slot:selection="{ text }">
                  <v-chip
                    small
                    label
                    color="#FF9292"
                  >
                    {{ text }}
                  </v-chip>
                </template>
                </v-file-input>
                <ValidationProvider name="생년월일" rules="required" v-slot="{ errors }">
                  <v-menu
                  v-model="birthMenu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }" class="mb-3">

                        <v-text-field
                          v-model="joinInfo.birth"
                          label="생년월일"
                          prepend-icon="mdi-calendar"
                          :error-messages="errors"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="joinInfo.birth"
                      @input="birthMenu = false"
                    ></v-date-picker>
                  </v-menu>
                </ValidationProvider>
                <!-- 관심사 선택하기 -->
                <v-col class="d-flex-column mx-5 mb-5" cols="10">
                  <h3>관심사 선택</h3>
                  <span id="애니" class="hobby" @click="changeHobby('애니', '애니')">애니</span>
                  <span id="글쓰기" class="hobby" @click="changeHobby('글쓰기', '글쓰기')">글쓰기</span>
                  <span id="자기계발" class="hobby" @click="changeHobby('자기계발', '자기계발')">자기계발</span>
                  <span id="재테크" class="hobby" @click="changeHobby('재테크', '재테크')">재테크</span>
                  <span id="그림그리기" class="hobby" @click="changeHobby('그림그리기', '그림그리기')">그림그리기</span>
                  <span id="봉사활동" class="hobby" @click="changeHobby('봉사활동', '봉사활동')">봉사활동</span>
                  <span id="댄스" class="hobby" @click="changeHobby('댄스', '댄스')">댄스</span>
                  <span id="IT" class="hobby" @click="changeHobby('IT', 'IT')">IT</span>
                  <span id="산책" class="hobby" @click="changeHobby('산책', '산책')">산책</span>
                  <span id="자전거타기" class="hobby" @click="changeHobby('자전거타기', '자전거타기')">자전거타기</span>
                  <span id="드라이브" class="hobby" @click="changeHobby('드라이브', '드라이브')">드라이브</span>
                  <span id="쇼핑" class="hobby" @click="changeHobby('쇼핑', '쇼핑')">쇼핑</span>
                  <span id="독서" class="hobby" @click="changeHobby('독서', '독서')">독서</span>
                  <span id="패션" class="hobby" @click="changeHobby('패션', '패션')">패션</span>
                  <span id="술" class="hobby" @click="changeHobby('술', '술')">술</span>
                  <span id="노래" class="hobby" @click="changeHobby('노래', '노래')">노래</span>
                  <span id="운동" class="hobby" @click="changeHobby('운동', '운동')">운동</span>
                  <span id="요리" class="hobby" @click="changeHobby('요리', '요리')">요리</span>
                  <span id="악기연주" class="hobby" @click="changeHobby('악기연주', '악기연주')">악기연주</span>
                  <span id="여행" class="hobby" @click="changeHobby('여행', '여행')">여행</span>
                  <span id="드라마" class="hobby" @click="changeHobby('드라마', '드라마')">드라마</span>
                  <span id="영화" class="hobby" @click="changeHobby('영화', '영화')">영화</span>
                  <span id="사진촬영" class="hobby" @click="changeHobby('사진촬영', '사진촬영')">사진촬영</span>
                  <span id="게임" class="hobby" @click="changeHobby('게임', '게임')">게임</span>
                  <span id="공연관람" class="hobby" @click="changeHobby('공연관람', '공연관람')">공연관람</span>
                  <span id="맛집탐방" class="hobby" @click="changeHobby('맛집탐방', '맛집탐방')">맛집탐방</span>
                  <span id="음악" class="hobby" @click="changeHobby('음악', '음악')">음악</span>
                  <span id="외국어" class="hobby" @click="changeHobby('외국어', '외국어')">외국어</span>
                </v-col>
                <v-col>
                  <v-btn
                    @click="join()"
                    elevation="2"
                    :disabled="invalid"
                    color = "#FF9292"
                  >회원가입</v-btn>
                </v-col>
              </v-row>
            </form>
          </validation-observer>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
import { required, email } from 'vee-validate/dist/rules'
import { ValidationObserver, ValidationProvider, extend } from 'vee-validate'
import axios from 'axios'
import router from '../../router'

extend('emailValidate', {
  ...email,
  message: '이메일을 입력해 주세요.'
})

extend('required', {
  ...required,
  message: '{_field_}을(를) 입력해 주세요'
})

export default {
  components: {
    ValidationObserver,
    ValidationProvider
  },
  name: 'Join',
  props: {},
  data: () => ({
    joinInfo: {
      email: 'tofan@naver.com',
      nickName: '조웅현',
      password: 'dkstkstl1#@',
      address: '대전광역시 서구 월평동로 83',
      extAddress: '101-1106',
      gender: '남',
      birth: '1995-04-12',
      profile: [],
      role: '사용자',
      hobby: [],
    },
    zcode: '',
    genderItems: ['남', '여'],
    birthMenu: false,
    ProfileRules: [
      value => !value || value.size < 2000000 || 'Avatar size should be less than 2 MB!'
    ]
  }),
  methods: {
    daumPostCode () {
      new window.daum.Postcode({
        oncomplete: (data) => {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
          // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          let fullRoadAddr = data.roadAddress // 도로명 주소 변수
          let extraRoadAddr = '' // 도로명 조합형 주소 변수
          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== '' && data.apartment === 'Y') {
            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName)
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraRoadAddr !== '') {
            extraRoadAddr = ' (' + extraRoadAddr + ')'
          }
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          if (fullRoadAddr !== '') {
            fullRoadAddr += extraRoadAddr
          }
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          this.zcode = data.zonecode
          this.joinInfo.addr = fullRoadAddr
        }
      }).open()
    },
    changeHobby (id, hobbyName) {
      const show = document.querySelector('#' + id)
      // 이미 추가한 관심사인 경우
      if (show.classList.contains('selected')) {
        // 1. CSS 변경
        show.classList.remove('selected')
        // 2. hobby list 변경
        this.joinInfo.hobby.splice(this.joinInfo.hobby.indexOf(hobbyName), 1)

      // 아직 추가하지 않은 관심사인 경우
      } else {
        // 1. CSS 변경
        show.className += ' selected'
        // 2. hobby List 변경
        this.joinInfo.hobby.push(hobbyName)
      }
    },
    join () {
      console.log("회원가입 요청합니다.")

      axios({
          url: 'http://localhost:8000/api/meet/user/join',
          method: 'POST',
          data: {
            email: this.joinInfo.email,
            nickname: this.joinInfo.nickName,
            password: this.joinInfo.password,
            address: this.joinInfo.address,
            extAddress: this.joinInfo.extAddress,
            gender: this.joinInfo.gender,
            birth: this.joinInfo.birth,
            profile: this.joinInfo.profile.name, // S3 업로드의 경우 별도로 진행해야 한다.
            role: this.joinInfo.role,
            hobby: this.joinInfo.hobby,
          }
        }).then(resp => {
          router.push("/Login")
        })
      // this.$refs.observer.validate().then(

      // )
    }
  }

}
</script>

<style lang="scss" scoped>
.hobby {
  border: 1px solid lightgrey;
  background-color: white;
  display: inline-block;
  margin: 5px;
  padding: 10px;
  border-radius: 10px;
  -webkit-transition: 500ms;
}
.hobby:hover {
  background-color: #FF9292;
  cursor: pointer;
  -webkit-transition: 500ms;
}
.selected {
  background-color: #FF9292;
  border: 1px solid #FF9292;
}
</style>
