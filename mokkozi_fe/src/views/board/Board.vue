<template>
  <div class="board-container" style="width: 700px; height:47rem;">
    <v-btn color="#ffb4b4" class="create-btn" @click="boardCreateClick"
      >+</v-btn>
    <div class="background-div" v-for="(board, i) in boardList" :key="i">
      <div class="board-div">
        <v-card class="board-card" max-width="24rem" height="30rem">
          <v-card-title
            style="
              display: flex;
              justify-content: space-between;
              margin-bottom: 0.2rem;
            "
          >
            <div>
              <v-avatar size="36px" @click="userImageClick(board.userEmail)">
                <img alt="Avatar" :src="board.profileUrl" />
              </v-avatar>
              <span
                class="font-weight-bold"
                style="margin-left: 0.5rem"
                @click="userNicknameClick(board.userEmail)"
                >{{ board.nickName }}</span
              >
            </div>
            <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                <v-icon color="black" dark v-bind="attrs" v-on="on"
                  >fas fa-ellipsis-h</v-icon
                >
              </template>
              <v-list>
                <v-list-item v-if="board.userEmail === loginUser">
                  <v-list-item-title>
                    <v-btn
                      style="cursor: pointer;"
                      color="#FFB4B4"
                      dark
                      @click="boardUpdateClick(board.id)">수정하기</v-btn>
                  </v-list-item-title>
                </v-list-item>
                <v-list-item>
                  <report-board :boardId="board.id" />
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

          <!-- Carousel -->
          <v-carousel height="300" class="carousel" v-if="boardImgList[i].length > 1" style="margin: 1rem 0rem">
            <v-carousel-item
            v-for="(img, i) in boardImgList[i]"
            :key="i"
            :src="img.file_path"
            reverse-transition="fade-transition"
            transition="fade-transition"
            ></v-carousel-item>
          </v-carousel>

          <!-- 이미지가 1장인 경우에는 -->
          <v-img
          v-else-if="boardImgList[i].length === 1"
          width="320px"
          height="300px"
          max-height="300px"
          position="center"
          :src="boardImgList[i][0].file_path"
          style="margin-bottom: 0.2rem"
          >
          </v-img>

          <v-card-text class="like-text">
            <i
              v-if="board.boardLike"
              class="fas fa-heart"
              style="color: red"
              @click="boardUnLike(board.id)"
            ></i>
            <i
              v-else
              class="far fa-heart"
              style="color: red"
              @click="boardLike(board.id)"
            ></i>
            {{ board.likeCount }}명이 이 글을 좋아합니다.
          </v-card-text>

          <v-card-text @click="boardDetailClick(board.id)" style="font-size:15px; margin: 4px 0px">
            {{ board.content }}
          </v-card-text>
          <!-- 작성한 댓글이 존재한다면 -->
          <!-- 댓글 2개까지 출력하기 -->
          <!-- <v-card-text v-for="(comment, commentIdx) in commentList[i]" :key="comment.id" style="height: 23px">
            <p v-if="commentIdx < 2" style="float:left; font-size: 12px; margin: 0px">
              {{ comment.content }}
            </p>
          </v-card-text> -->
          <div v-if="commentList[i].length === 0" style="height: 15px; font-size: 12px;">
            <p style="float: left">아직 작성된 댓글이 없습니다 :(</p>
          </div>
          <div v-else-if="commentList[i].length === 1">
            <v-avatar size="36px" @click="userImageClick(board.userEmail)">
              <img alt="Avatar" :src="commentList[i][0].profileUrl" />
            </v-avatar>
            <v-card-text style="height: 20px; text-align:start; font-size: 12px; margin: 0px">
              {{ commentList[i][0].content }}
            </v-card-text>
          </div>
          <div v-else-if="commentList[i].length >= 2">
            <v-card-text style="height: 20px; text-align:start; font-size: 12px; margin: 0px">
              {{ commentList[i][0].content }}
            </v-card-text>
            <v-card-text style="height: 20px; text-align:start; font-size: 12px; margin: 0px">
              {{ commentList[i][1].content }}
            </v-card-text>
          </div>
          <div v-if="commentList[i].length >= 3" style="height: 15px;">
            <p style="text-align:start; color: gray; cursor: pointer; height: 15px; font-size: 12px; margin: 0px" @click="commentClick(board.id)"
            >댓글 더 보기..</p>
          </div>
          <!-- 댓글 작성란 -->
          <div style="display:flex; justify-content: space-between;">
            <input
              v-model="commentContent"
              style="
              height: 24px;
              font-size: 0.875rem;
              border: none;
              width: 16rem;
              outline-style: none;
              "
              type="text"
              placeholder="댓글 달기"
              @keydown.enter="createComment(board.id)"
            />
            <v-btn
              color="#FFB4B4"
              style="padding: 0px 5px;"
              min-width="40px"
              height="24px"
              @click="createComment(board.id)"
            >
              작성
            </v-btn>
          </div>
        </v-card>
      </div>
    </div>
    <!-- infinite scroll -->
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
      >
        목록의 끝입니다 :)
      </div>
    </infinite-loading>
  </div>
</template>

<script>
import axios from "axios"
import InfiniteLoading from "vue-infinite-loading"
import ReportBoard from "../../components/ReportBoard"

export default {
  name: "Board",
  components: {
    InfiniteLoading,
    ReportBoard,
  },
  data: () => ({
    boardList: [],
    boardImgList: [],
    limit: 0, // 무한 스크롤이 되면서 갱신될 페이지를 저장하는 변수
    commentContent: '', // 새롭게 작성할 댓글의 내용
    commentList: "",
  }),
  computed: {
    loginUser() {
      return this.$store.state.user.email;
    },
  },
  methods: {
    // infinite scroll
    infiniteHandler($state) {
      const EACH_LEN = 10;
      axios({
        url: process.env.VUE_APP_API_URL + `/api/meet/board?page=${this.limit + 1}`,
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      })
        .then((res) => {
          console.log(res)
          // 이미지 목록 지정하자.
          // if (res.data.galleryListDto.galleryList.length === 0) {

          // }
          this.boardImgList = res.data.galleryListDto.galleryList
          console.log('this.boardImgList', res.data.galleryListDto.galleryList)
          this.boardImgList.filter((boardImg) => {
            if (boardImg.length === 0) {
              return boardImg.push({'file_path' : 'https://mokkozi.s3.ap-northeast-2.amazonaws.com/mokkozi_default_img.jpg'})
            }
          })
          console.log('this.boardImgList', res.data.galleryListDto.galleryList)
          console.log("댓글 내용은..!", res.data.commentLists)
          this.commentList = res.data.commentLists

          console.log("인피니트 스크롤롤 받아온 데이터", res.data.boardList);
          console.log(
            "인피니트 스크롤롤 받아온 데이터",
            res.data.boardList.content.length
          );

          setTimeout(() => {
            if (res.data.boardList.content.length) {
              this.boardList = this.boardList.concat(
                res.data.boardList.content
              );
              $state.loaded();
              this.limit += 1;
              console.log(
                "after",
                res.data.boardList.content.length,
                this.limit
              );
              // 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면
              if (res.data.boardList.content.length / EACH_LEN < 1) {
                $state.complete();
              }
            } else {
              // 끝 지정(No more data)
              $state.complete();
            }
          }, 1000);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    boardUpdateClick(boardId) {
      this.$router.push({ name: "BoardUpdate", params: { boardId: boardId } });
    },
    boardReportClick(boardId) {
      this.$router.push({ name: "Home", params: { boardId: boardId } }); // 신고하는 페이지로 이동하도록 바꿔야함
    },
    userImageClick(userEmail) {
      this.$router.push({ name: "Profile", params: { userEmail: userEmail } });
    },
    userNicknameClick(userEmail) {
      this.$router.push({ name: "Profile", params: { userEmail: userEmail } });
    },
    boardCreateClick() {
      this.$router.push({ name: "BoardCreate" });
    },
    boardDetailClick(boardId) {
      this.$router.push({ name: "BoardDetail", params: { boardId: boardId } });
    },
    commentClick(boardId) {
      this.$router.push({ name: "Comment", params: { boardId: boardId } });
    },
    // 댓글 작성
    createComment(boardId) {
      if (this.commentContent.trim() !== "") {
        console.log("댓글 정보 - 게시글 아이디 : ", boardId, ", 댓글 내용 : ", this.commentContent)
      axios({
        url: process.env.VUE_APP_API_URL + "/api/meet/comment",
        method: "POST",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
        data: {
          boardId: boardId,
          content: this.commentContent,
        },
      })
        .then((res) => {
          console.log("댓글 작성 성공", res);
          this.$router.go()
        })
        .catch((err) => {
          console.log("댓글 작성 실패", err);
        });
      }
    },
    // 좋아요
    boardLike(boardId) {
      axios({
        url: process.env.VUE_APP_API_URL + `/api/meet/board/like?boardId=${boardId}`,
        method: "POST",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      })
        .then((res) => {
          console.log("좋아요 성공", res);
          this.boardList.filter((board) => {
            if (board.id === boardId) {
              board.boardLike = !board.boardLike;
              board.likeCount += 1
            }
            return board;
          });
        })
        .catch((err) => {
          console.log("좋아요 실패", err);
        });
    },
    // 좋아요 취소
    boardUnLike(boardId) {
      axios({
        url: process.env.VUE_APP_API_URL + `/api/meet/board/unlike?boardId=${boardId}`,
        method: "DELETE",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      })
        .then((res) => {
          console.log("좋아요 취소 성공", res);
          this.boardList.filter((board) => {
            if (board.id === boardId) {
              board.boardLike = !board.boardLike;
              board.likeCount -= 1
            }
            return board;
          });
        })
        .catch((err) => {
          console.log("좋아요 취소 실패", err);
        });
    },
  },
};
</script>

<style scoped>
.board-container {
  overflow-y: scroll;
  position: relative;
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
  height: 34rem;
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
  line-height: 15px;
  word-break: break-all;
  padding: 0;
}
.board-card {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-content: center;
  background-color: #5f5050;
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
  -webkit-line-clamp: 1;
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
.create-btn {
  border-radius: 60rem;
  min-width: 60px;
  min-height: 60px;
  position: absolute;
  right: 20px;
  top: 50px;
  color: white;
  font-size: 40px;
}
</style>
