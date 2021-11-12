<template>
  <v-container fluid style="height: 800px;" class="board-container">
    <h1 style="display:flex; justify-content:center">게시판</h1>
    <br>
    <v-icon style="position: fixed; color: #FFB4B4;" @click="boardCreateClick">fas fa-plus-circle</v-icon>
    <div class="background-div" v-for="image in images" :key="image.id">
      <div class="board-div">
        <v-card
          class="board-card"
          max-width="24rem"
          height="28rem"
        >
          <v-card-title style="display:flex; justify-content:space-between; margin-bottom: 0.2rem">
            <div>
              <v-avatar size="36px" @click="userImageClick">
              <img
                alt="Avatar"
                src="@/assets/logo.png"
              >
              </v-avatar>
              <span class="font-weight-bold" style="margin-left: 0.5rem" @click="userNicknameClick">MOKKOZI</span>
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
                  <v-list-item-title style="cursor: pointer;" @click="boardUpdateClick">수정하기</v-list-item-title>
                </v-list-item>
                <v-list-item>
                  <v-list-item-title style="cursor: pointer;" @click="boardReportClick">신고하기</v-list-item-title>
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
            @click="boardDetailClick"
          ></v-img>

          <v-card-text class="like-text">
            <i v-if="like" class="fas fa-heart" style="color:red" @click="boardUnLike(1)"></i>
            <i v-else class="far fa-heart" style="color:red" @click="boardLike(1)"></i>
             like
          </v-card-text>

          <v-card-text @click="boardDetailClick">
            Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
          </v-card-text>
          <v-card-text>
            Help
          </v-card-text>
          <v-card-text style="color: gray" @click="commentClick">댓글 더 보기..</v-card-text>
          <div>
            <input v-model="commentContent" style="height: 1.25rem; font-size: 0.875rem; border: none; width: 16rem"
              type="text" placeholder="댓글 달기">
            <v-btn
              color="#FFB4B4"
              width="4rem"
              height="1.25rem"
              @click="createComment"
            >
              작성
            </v-btn>
          </div>
          <v-card-actions style="padding: 0.2rem 0rem">
            <v-btn
              color="#FFB4B4"
              @click="userfollow"
            >
              Follow
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </div>
    <!-- infinite scroll -->
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
      <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
    </infinite-loading>
  </v-container>
</template>

<script>
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'

export default {
  name: 'Board',
  components: {
    InfiniteLoading
  },
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
    boardList: [],
    limit: 0, // 무한 스크롤이 되면서 갱신될 페이지를 저장하는 변수
    like: true,
    commentContent: ''
  }),
  created () {
    // infinite scroll
    async function getTopicFromApi() {
          try {
              const init = await fetch(`/api/idol/uwasa/pages/0`, {method: "GET"})
              const data = await init.json()
              return data
          } catch(exc) {
              console.error(exc)
          }
      }
      getTopicFromApi().then(data => {
          console.log("fromAPI", data)
          this.boardList = data
      })
  },
  methods: {
    // infinite scroll
    infiniteHandler($state) {
      const EACH_LEN = 30
      fetch("http://localhost:8000/api/meet/board/", {method: "get"}).then(resp => {
        return resp.json()
      }).then(data => {
        setTimeout(() => {
          if(data.length) {
            this.boardList = this.boardList.concat(data)
            $state.loaded()
            this.limit += 1
            console.log("after", this.boardList.length, this.limit)
            // 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면
            if(data.length / EACH_LEN < 1) {
              $state.complete()
            }
          } else {
            // 끝 지정(No more data)
            $state.complete()
          }
        }, 1000)
      }).catch(err => {
        console.error(err);
      })
    },
    boardUpdateClick () {
      this.$router.push({ name: 'BoardUpdate' })
    },
    boardReportClick () {
      this.$router.push({ name: 'Home' }) // 신고하는 페이지로 이동하도록 바꿔야함
    },
    userImageClick () {
      this.$router.push({ name: 'Profile' })
    },
    userNicknameClick () {
      this.$router.push({ name: 'Profile' })
    },
    boardCreateClick () {
      this.$router.push({ name: 'BoardCreate' })
    },
    boardDetailClick () {
      this.$router.push({ name: 'BoardDetail' })
    },
    commentClick () {
      this.$router.push({ name: 'Comment' })
    },
    // 게시물 리스트 불러오기
    getBoardList () {
      axios({
        url: 'http://localhost:8000/api/meet/board',
        method: 'GET',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          page: 1
        }
      }).then(res => {
        console.log('게시물 불러오기 성공', res)
      }).catch(err => {
        console.log('게시물 불러오기 실패', err)
      })
    },
    // 댓글 작성
    createComment () {
      axios({
        url: 'http://localhost:8000/api/meet/comment',
        method: 'POST',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          content: this.commentContent
        }
      }).then(res => {
        console.log('댓글 작성 성공', res)
      }).catch(err => {
        console.log('댓글 작성 실패', err)
      })
    },
    // 좋아요
    boardLike (boardId) {
      axios({
        url: 'http://localhost:8000/api/meet/board/like',
        method: 'POST',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          boardId: boardId
        }
      }).then(res => {
        console.log('좋아요 성공', res)
      }).catch(err => {
        console.log('좋아요 실패', err)
      })
    },
    // 좋아요 취소
    boardUnLike (boardId) {
      axios({
        url: 'http://localhost:8000/api/meet/board/unlike',
        method: 'POST',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          boardId: boardId
        }
      }).then(res => {
        console.log('좋아요 취소 성공', res)
      }).catch(err => {
        console.log('좋아요 취소 실패', err)
      })
    },
    // 팔로우 요청
    userfollow (userId) { // follow 신청
      axios({
        url: 'http://localhost:8000/api/meet/user/follow',
        method: 'POST',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          userId: userId
        }
      }).then(res => {
        console.log('팔로우 성공', res)
      }).catch(err => {
        console.log('팔로우 실패', err)
      })
    },
    // 언팔로우 요청
    userUnfollow (userId) { // follow 신청
      axios({
        url: 'http://localhost:8000/api/meet/user/unfollow',
        method: 'POST',
        headers:{
          Authorization:"Bearer "+ this.$store.state.jwt
        },
        data: {
          userId: userId
        }
      }).then(res => {
        console.log('언팔로우 성공', res)
      }).catch(err => {
        console.log('언팔로우 실패', err)
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
