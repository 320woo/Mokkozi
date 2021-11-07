<template>
  <v-container fluid style="height: 800px;" class="board-container">
    <h1 style="display:flex; justify-content:center">게시판</h1>
    <br>
    <v-icon style="position: fixed; color: #fdb4b5;" @click="BoardCreateClick">fas fa-plus-circle</v-icon>
    <div class="background-div" v-for="image in images" :key="image.id">
      <div class="board-div">
        <v-card
          class="board-card"
          max-width="24rem"
          height="28rem"
        >
          <v-card-title style="display:flex; justify-content:space-between; margin-bottom: 0.2rem">
            <div>
              <v-avatar size="36px" @click="UserImageClick">
              <img
                alt="Avatar"
                src="@/assets/logo.png"
              >
              </v-avatar>
              <span class="font-weight-bold" style="margin-left: 0.5rem" @click="UserNicknameClick">MOKKOZI</span>
            </div>
            <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                  <v-icon
                  color="black"
                  dark
                  v-bind="attrs"
                  v-on="on"
                  >fas fa-ellipsis-h</v-icon>
              </template>
              <v-list>
                <v-list-item>
                  <v-list-item-title style="cursor: pointer;" @click="BoardUpdateClick">수정하기</v-list-item-title>
                </v-list-item>
                <v-list-item>
                  <v-list-item-title style="cursor: pointer;" @click="BoardReportClick">신고하기</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-card-title>
          <!-- <i class="fas fa-ellipsis-h"></i> -->
          <template slot="progress">
            <v-progress-linear
              color="deep-purple"
              height="10"
              indeterminate
            ></v-progress-linear>
          </template>

          <v-img
            width="24rem"
            height="auto"
            max-height="15rem"
            position="center"
            :src="image"
            style="margin-bottom: 0.2rem"
            @click="BoardDetailClick"
          ></v-img>

          <v-card-text class="like-text">
            <i v-if="like" class="fas fa-heart" style="color:red" @click="BoardLike(1)"></i>
            <i v-else class="far fa-heart" style="color:red" @click="BoardLike(1)"></i>
             like
          </v-card-text>

          <v-card-text @click="BoardDetailClick">
            Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
          </v-card-text>
          <v-card-text>
            Help
          </v-card-text>
          <v-card-text style="color: gray" @click="CommentClick">댓글 더 보기..</v-card-text>
          <div>
            <input style="height: 1.25rem; font-size: 0.875rem; border: none; width: 16rem" type="text" placeholder="댓글 달기">
            <v-btn
              color="#fdb4b5"
              width="4rem"
              height="1.25rem"
            >
              작성
            </v-btn>
          </div>
          <v-card-actions style="padding: 0.2rem 0rem">
            <v-btn
              color="#fdb4b5"
              @click="Userfollow"
            >
              Follow
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Board',
  components: {},
  data: () => ({
    images: [
      'https://images.dog.ceo/breeds/bulldog-english/murphy.jpg',
      'https://images.dog.ceo/breeds/spaniel-japanese/n02085782_2690.jpg',
      'https://images.dog.ceo/breeds/stbernard/n02109525_13702.jpg',
      'https://images.dog.ceo/breeds/papillon/n02086910_933.jpg',
      'https://images.dog.ceo/breeds/ovcharka-caucasian/IMG_20190611_152047.jpg',
      'https://images.dog.ceo/breeds/terrier-toy/n02087046_2843.jpg',
      'https://images.dog.ceo/breeds/cockapoo/Scout.jpg',
      'https://cdn.vuetifyjs.com/images/cards/cooking.png',
      '@/assets/images/main.png',
      'https://dog.ceo/api/breeds/image/random'
    ],
    like: true
  }),
  methods: {
    BoardUpdateClick () {
      this.$router.push({ name: 'BoardUpdate' })
    },
    BoardReportClick () {
      this.$router.push({ name: 'Home' }) // 신고하는 페이지로 이동하도록 바꿔야함
    },
    UserImageClick () {
      this.$router.push({ name: 'Profile' })
    },
    UserNicknameClick () {
      this.$router.push({ name: 'Profile' })
    },
    BoardCreateClick () {
      this.$router.push({ name: 'BoardCreate' })
    },
    BoardDetailClick () {
      this.$router.push({ name: 'BoardDetail' })
    },
    CommentClick () {
      this.$router.push({ name: 'Comment' })
    },
    BoardLike (boardId) {
      axios({
        url: 'api/meet/board/like',
        method: 'POST',
        data: {
          boardId: boardId
        }
      })
    },
    Userfollow (userId) { // follow 신청
      axios({
        url: 'api/meet/user/follow',
        method: 'POST',
        data: {
          userId: userId
        }
      })
    }
  }
}
</script>

<style scoped>
  .board-container {
    overflow-y: scroll;
  }
  .board-container::-webkit-scrollbar {
    display: none;
  }
  .background-div {
    padding: 0.5rem 5rem 0.5rem 5rem;
    text-align: center;
  }
  .board-div {
    width: 24rem;
    height: 32rem;
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
</style>
