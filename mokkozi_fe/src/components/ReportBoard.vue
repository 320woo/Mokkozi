<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="800px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="#FFB4B4"
          dark
          v-bind="attrs"
          v-on="on"
        >
          신고하기
        </v-btn>
      </template>

      <v-card>
        <v-card-title style="display:flex; justify-content:space-between;" class="white">
          <span>게시글 신고하기</span>
          <v-btn color="white" icon>
            <v-icon style="color: #FFB4B4;" @click="dialog = false">fas fa-times-circle</v-icon>
          </v-btn>
        </v-card-title>
        <v-divider></v-divider>
        <v-card-text style="font-size: 1rem; font-weight: bold; padding: 24px">
          사유 선택
        </v-card-text>
        <v-card-text>
          여러 사유에 해당되는 경우 대표적인 사유 1개를 선택해 주세요.
        </v-card-text>
        <p id="reason1" class="reason" @click="selectReason('reason1', '부적절한 홍보 게시글')">부적절한 홍보 게시글</p>
        <p id="reason2" class="reason" @click="selectReason('reason2', '음란성')">음란성</p>
        <p id="reason3" class="reason" @click="selectReason('reason3', '명예훼손/사생활 침해 및 저작권 침해 등')">명예훼손/사생활 침해 및 저작권 침해 등</p>
        <p id="reason4" class="reason" @click="selectReason('reason4', '혐오 발언 또는 상징')">혐오 발언 또는 상징</p>
        <p id="reason5" class="reason" @click="selectReason('reason5', '불법 또는 규제 상품 판매')">불법 또는 규제 상품 판매</p>
        <p id="reason6" class="reason" @click="selectReason('reason6', '기타')">기타</p>
        <v-divider style="margin-top: 21px"></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="#FFB4B4"
            @click="dialog = false"
          >
            신고
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: 'ReportBoard',
  data: () => ({
    dialog: false,
    reason: '',
    reasonList: [['reason1', '부적절한 홍보 게시글'], ['reason2', '음란성'],
                 ['reason3', '명예훼손/사생활 침해 및 저작권 침해 등'], ['reason4', '혐오 발언 또는 상징'],
                 ['reason5', '불법 또는 규제 상품 판매'], ['reason6', '기타'],]
  }),
  props: {
    open: {
      type: Boolean
    }
  },
  methods: {
    selectReason (id, reason) { // 하나만 선택할 수 있도록 바꿔야 한다
      const show = document.querySelector('#' + id)
      // 이미 선택한 이유가 있는 경우
      if (show.classList.contains('selected')) {
        // 1. CSS 변경
        show.classList.remove('selected')
        // 2. reason 변경
        this.reason = reason
      // 선택한 이유가 없는 경우
      } else {
        // 1. CSS 변경
        show.className += ' selected'
        // 2. hobby List 변경
        this.reason = reason
      }
    },
  }
}
</script>

<style scoped>
  .reason {
  border: 1px solid lightgrey;
  background-color: white;
  padding: 10px;
  border-radius: 10px;
  -webkit-transition: 500ms;
  margin: 3px 24px;
  }
  .reason:hover {
    background-color: #FF9292;
    cursor: pointer;
    -webkit-transition: 500ms;
  }
  .selected {
    background-color: #FF9292;
    border: 1px solid #FF9292;
  }
</style>
