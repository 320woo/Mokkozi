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
              <v-avatar size="36px" @click="UserImageClick">
              <img
                alt="Avatar"
                src="@/assets/logo.png"
              >
              </v-avatar>
              <span class="font-weight-bold" style="margin-left: 0.5rem" @click="UserNicknameClick">MOKKOZI</span>
            </div>
            <v-icon @click="BackToBoardClick">fas fa-chevron-left</v-icon>
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
          v-model="content"
          placeholder="내용을 입력하세요.."
        ></v-textarea>
        <v-btn
          color="#FFB4B4"
          style="float: right;"
          @click="createBoard">
          작성
        </v-btn>
      </div>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardCreate',
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
  methods: {
    UserImageClick () {
      this.$router.push({ name: 'Profile' })
    },
    UserNicknameClick () {
      this.$router.push({ name: 'Profile' })
    },
    BackToBoardClick () {
      this.$router.push({ name: 'Board' })
    },
    // 게시글 작성 요청
    // Swagger로 보면 title과 content가 필요하지만 content만 있으면 될 듯?
    // 사진 보내는 url 필요, FormData() 이용
    createBoard () {
      const formData = new FormData()
      formData.append('files', this.uploadImage)
      axios({
        url: '/api/meet/board',
        method: 'POST',
        data: {
          content: this.content
        }
      }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
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
