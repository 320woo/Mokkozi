<template>
  <v-container fluid style="height: 800px" class="board-container">
    <h1 style="display: flex; justify-content: center">게시판</h1>
    <br />
    <v-icon style="position: fixed; color: #ffb4b4" @click="boardCreateClick"
      >fas fa-plus-circle</v-icon
    >
    <div class="background-div" v-for="board in boardList" :key="board.id">
      <div class="board-div">
        <v-card class="board-card" max-width="24rem" height="28rem">
          <v-card-title
            style="
              display: flex;
              justify-content: space-between;
              margin-bottom: 0.2rem;
            "
          >
            <div>
              <v-avatar size="36px" @click="userImageClick(board.userEmail)">
                <img alt="Avatar" src="@/assets/logo.png" />
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
<<<<<<< HEAD
                <v-list-item>
                  <v-list-item-title
                    style="cursor: pointer"
                    @click="boardUpdateClick(board.id)"
                    >수정하기</v-list-item-title
                  >
=======
                <v-list-item v-if="board.userEmail === loginUser">
                  <v-list-item-title style="cursor: pointer;" @click="boardUpdateClick(board.id)">수정하기</v-list-item-title>
>>>>>>> 95bba7192c6f33f24a6827acb106781657f6b325
                </v-list-item>
                <v-list-item>
                  <report-board :boardId="board.id" />
                </v-list-item>
                <v-list-item>
                  <report-user :userEmail="board.userEmail" />
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
            src="https://images.dog.ceo/breeds/bulldog-english/murphy.jpg"
            style="margin-bottom: 0.2rem"
            @click="boardDetailClick(board.id)"
          ></v-img>

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
            like
          </v-card-text>

          <v-card-text @click="boardDetailClick(board.id)">
            {{ board.content }}
          </v-card-text>
          <v-card-text> Help </v-card-text>
          <v-card-text style="color: gray" @click="commentClick"
            >댓글 더 보기..</v-card-text
          >
          <div>
            <input
              v-model="commentContent"
              style="
                height: 1.25rem;
                font-size: 0.875rem;
                border: none;
                width: 16rem;
              "
              type="text"
              placeholder="댓글 달기"
            />
            <v-btn
              color="#FFB4B4"
              width="4rem"
              height="1.25rem"
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
  </v-container>
</template>

<script>
import axios from "axios";
import InfiniteLoading from "vue-infinite-loading";
import ReportBoard from "../../components/ReportBoard";
import ReportUser from "../../components/ReportUser";

export default {
  name: "Board",
  components: {
    InfiniteLoading,
    ReportBoard,
    ReportUser,
  },
  data: () => ({
    boardList: [],
    limit: 0, // 무한 스크롤이 되면서 갱신될 페이지를 저장하는 변수
    commentContent: "",
  }),
  mounted () {
    // this.getBoardList()
  },
  computed: {
    loginUser () {
      return this.$store.state.user.email
    }
  },
  methods: {
    // infinite scroll
    infiniteHandler($state) {
      const EACH_LEN = 10;
      axios({
        url: `http://localhost:8000/api/meet/board?page=${this.limit + 1}`,
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      })
        .then((res) => {
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
    // 게시물 리스트 불러오기
    getBoardList() {
      axios({
        url: `http://localhost:8000/api/meet/board?page=${this.limit}`,
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      })
        .then((res) => {
          console.log("게시물 불러오기 성공", res);
          this.boardList = res.data.boardList.content;
        })
        .catch((err) => {
          console.log("게시물 불러오기 실패", err);
        });
    },
    // 댓글 작성
    createComment(boardId) {
      axios({
        url: "http://localhost:8000/api/meet/comment",
        method: "POST",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
        data: {
          id: boardId,
          content: this.commentContent,
        },
      })
        .then((res) => {
          console.log("댓글 작성 성공", res);
        })
        .catch((err) => {
          console.log("댓글 작성 실패", err);
        });
    },
    // 좋아요
    boardLike(boardId) {
      axios({
        url: `http://localhost:8000/api/meet/board/like?boardId=${boardId}`,
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
        url: `http://localhost:8000/api/meet/board/unlike?boardId=${boardId}`,
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
