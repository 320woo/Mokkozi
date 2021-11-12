<template>
  <v-container fluid style="height: 800px;">
    <div class="background-div">
      <div class="board-div">
        <v-card
          class="board-card"
          max-width="24rem"
          height="22rem"
        >
          <v-card-title style="display:flex; justify-content:space-between">
            <div>
              <v-avatar size="36px" @click="userImageClick">
              <img
                alt="Avatar"
                src="@/assets/logo.png"
              >
              </v-avatar>
              <span class="font-weight-bold" style="margin-left: 0.5rem" @click="userNicknameClick">MOKKOZI</span>
            </div>
            <v-icon @click="backToBoardClick">fas fa-chevron-left</v-icon>
          </v-card-title>
          <template slot="progress">
            <v-progress-linear
              color="deep-purple"
              height="10"
              indeterminate
            ></v-progress-linear>
          </template>

          <v-file-input
           :rules="rules"
            accept="image/png, image/jpeg, image/bmp"
            placeholder="Pick your img"
            prepend-icon="mdi-camera"
            v-model="uploadImage" />
          <v-img
            width="24rem"
            height="auto"
            max-height="15rem"
            position="center"
            :src="url"
            style="margin-bottom: 0.2rem"
          ></v-img>
        </v-card>
        <v-textarea
          class="textarea"
          filled
          name="input-7-4"
          value=""
          placeholder="내용을 입력하세요.."
        ></v-textarea>
        <div style="float: right;">
          <v-btn
            color="#FFB4B4"
            @click="boardUpdate">
            수정
          </v-btn>
          <v-btn
            color="#FFB4B4"
            @click="boardDelete">
            삭제
          </v-btn>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardUpdae',
  components: {
  },
  data: () => ({
    rules: [
      value => !value || value.size < 2000000 || 'Avatar size should be less than 2 MB!'
    ],
    uploadImage: null,
    content: ''
  }),
  computed: {
    url () {
      if (!this.uploadImage) return
      return URL.createObjectURL(this.uploadImage)
    }
  },
  mounted () {
    getSelectBoard()
  },
  methods: {
    userImageClick () {
      this.$router.push({ name: 'Profile' })
    },
    userNicknameClick () {
      this.$router.push({ name: 'Profile' })
    },
    backToBoardClick () {
      this.$router.push({ name: 'Board' })
    },
    // 게시물 불러오기
    getSelectBoard (boardId) {
      axios({
        url: `http://localhost:8000/api/meet/board/${boardId}`,
        methods: 'GET',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        }
      }).then(res => {
        console.log('게시물 불러오기', res)
      }).catch(err => {
        console.log('게시물 불러오기 실패', err)
      })
    },
    // 게시물 수정
    boardUpdate (boardId) { // 이미지 업데이트 부분 필요
      axios({
        url: 'http://localhost:8000/api/meet/board',
        methods: 'PATCH',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          id: boardId,
          content: this.content
        }
      }).then(res => {
        console.log('게시물 수정', res)
      }).catch(err => {
        console.log('게시물 수정 실패', err)
      })
    },
    // 게시물 삭제
    boardDelete (boardId) {
      axios({
        url: 'http://localhost:8000/api/meet/board',
        methods: 'DELETE',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          id: boardId
        }
      }).then(res => {
        console.log('게시물 삭제', res)
      }).catch(err => {
        console.log('게시물 삭제 실패', err)
      })
    }
  }
}
</script>

<style scoped>
  .background-div {
    max-width: auto;
    padding: 0.5rem 5rem 0.5rem 5rem;
    text-align: center;
  }
  .board-div {
    width: 24rem;
    height: 39rem;
    display: inline-block;
    background-color: #ffe8e8;
    padding: 2rem 2rem;
    border-radius: 1rem;
  }
  .v-card__title {
    align-items: center;
    display: flex;
    font-size: 1rem;
    font-weight: 500;
    letter-spacing: 0.0125em;
    line-height: 2rem;
    word-break: break-all;
    padding: 0;
  }
  .board-card {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-content: center;
    background-color: #ffe8e8;
    padding-bottom: 1rem;
  }
  .theme--light.v-card {
    background-color: #ffe8e8;
  }
  .v-sheet.v-card:not(.v-sheet--outlined) {
    box-shadow: none;
  }
  .theme--light.v-card > .v-card__text {
    color: black;
  }
  .v-card__text {
    text-align: start;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.375rem;
    letter-spacing: 0.0071rem;
    padding: 0rem;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
  }
  .v-card__actions {
    display: flex;
    align-items: center;
    padding: 0.2rem;
    justify-content: flex-end;
  }
  .like-text {
    font-weight: 500;
  }
  .v-input {
    align-items: flex-start;
    display: flex;
    flex: 0;
    font-size: 16px;
    letter-spacing: normal;
    max-width: 100%;
    max-height: 2rem;
    text-align: left;
  }
  .v-text-field {
    padding-top: 0;
    margin-top: 0;
  }
  .textarea {
    min-height: 10rem;
    overflow-y: scroll;
  }
  .textarea::-webkit-scrollbar {
    display: none;
  }
</style>
