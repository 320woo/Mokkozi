<template>
  <div class="background-div">
    <div class="comment-div">
      <v-card
        class="board-card"
        max-width="24rem"
        height="auto"
      >
        <v-card-title style="display:flex; justify-content:space-between"><v-icon>fas fa-chevron-left</v-icon>댓글<v-icon>fas fa-ellipsis-h</v-icon></v-card-title>

        <v-card-text style="padding: 1rem 0rem">
          <v-icon>fas fa-ellipsis-h</v-icon> 사용자가 적었던 글이 보입니다.
        </v-card-text>
        <v-divider></v-divider>
        <v-card-text style="padding: 1rem 0rem">
          Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
        </v-card-text>
        <v-card-text style="padding: 1rem 0rem">
          Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
        </v-card-text>
        <v-card-text style="padding: 1rem 0rem">
          Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
        </v-card-text>

        <v-card-actions>
          <v-btn color="orange lighten-2" text>
            회원사진
          </v-btn>
          <input type="text" placeholder="댓글을 입력하세요..">
          <v-btn icon>
            <v-icon>fas fa-dove</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<style scoped>
  .background-div {
      padding: 0.5rem 5rem 0.5rem 5rem;
      margin-top: 3rem;
      text-align: center;
    }
  .comment-div {
    width: 24rem;
    height: 50rem;
    display: inline-block;
    background-color: #ffe8e8;
    padding: 2rem 2rem;
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
  .v-list-item {
    align-items: center;
    display: flex;
    flex: 1 1 100%;
    letter-spacing: normal;
    min-height: 2rem;
    max-width: "24rem";
    padding: 0;
    outline: none;
    position: relative;
    text-decoration: none;
  }
  .v-list-item__content {
    align-items: center;
    align-self: center;
    display: flex;
    flex-wrap: wrap;
    flex: 0;
    overflow: hidden;
    padding: 12px;
  }
</style>
