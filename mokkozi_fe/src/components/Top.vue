<template>
    <v-footer class="top" color="#FFB4B4" padless="padless" elevation="4">
        <v-row justify="center" no-gutters="no-gutters">
            <v-btn
                dark
                dense
                v-for="link in links"
                :key="link"
                color="white"
                text="text"
                rounded="rounded"
                class="my-2">
                {{ link }}
            </v-btn>
        </v-row>
    </v-footer>
</template>
<script>
export default {
  data: () => ({
    links: [
      '게시판',
      '랜덤 미팅',
      '공지사항'
    ]
  })
}
</script>

<style>
.top {
    position: fixed;
    left: auto;
    top: 0;
    width: 37rem;
    z-index: 999;
    }
</style>
