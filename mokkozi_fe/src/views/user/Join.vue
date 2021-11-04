<template>
    <v-container fluid style="height: 800px;">
      <v-row justify="center" align="center" style="height: 924px; width: 500px;">
        <v-col>
          <h3>기본 정보 입력</h3>
          <v-form>
            <v-text-field
              label="이메일"
              v-model="signUpInfo.email"
              placeholder="이메일을 입력하세요."
            ></v-text-field>
            <v-text-field
              label="닉네임"
              v-model="signUpInfo.nickName"
              placeholder="닉네임을 입력하세요."
            ></v-text-field>
            <v-text-field
              label="비밀번호"
              v-model="signUpInfo.password"
              type="password"
              placeholder="최소 8자, 숫자와 특수문자 결합"
            ></v-text-field>
            <v-row no-gutters>
              <v-col cols="12">
                <v-text-field
                label="주소"
                v-model="signUpInfo.addr"
                placeholder="'주소찾기'를 클릭해 주세요" readonly
                ></v-text-field>
                <v-text-field
                label="상세주소"
                v-model="signUpInfo.extAddr"
                placeholder="상세 주소를 입력하세요."
                ></v-text-field>
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
              <v-select
                :items="genderItems"
                v-model="signUpInfo.gender"
                label="성별"
              ></v-select>
              <v-file-input
                v-model="signUpInfo.profile"
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
              <v-menu
              v-model="birthMenu"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="signUpInfo.birth"
                    label="생년월일"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="signUpInfo.birth"
                  @input="birthMenu = false"
                ></v-date-picker>
              </v-menu>
              <!-- 관심사 선택하기 -->
              <v-col class="d-flex-column mx-5" cols="12">
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
            </v-row>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
export default {
  name: 'Join',
  props: {},
  data: () => ({
    signUpInfo: {
      email: '',
      password: '',
      addr: '',
      extAddr: '',
      gender: '',
      nickName: '',
      birth: '',
      profile: [],
      type: '사용자',
      hobby: []
    },
    addr: '',
    extAddr: '',
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
          this.signUpInfo.addr = fullRoadAddr
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
        this.signUpInfo.hobby.splice(this.signUpInfo.hobby.indexOf(hobbyName), 1)

      // 아직 추가하지 않은 관심사인 경우
      } else {
        // 1. CSS 변경
        show.className += ' selected'
        // 2. hobby List 변경
        this.signUpInfo.hobby.push(hobbyName)
      }
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
  -webkit-transition: 500ms;
}
.selected {
  background-color: #FF9292;
  border: 1px solid #FF9292;
}
</style>
