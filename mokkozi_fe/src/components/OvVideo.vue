<template>
  <video id="user_video" autoplay :width="videoWidth" :height="videoHeight"/>
</template>

<script>

export default ({
  name: 'OvVideo',
  data: () => ({
    videoWidth: '100%',
    videoHeight: '100%',
  }),
  props: {
    streamManager: Object
  },
 watch: {
    streamManager () {
      // this.streamManager.addVideoElement(document.querySelector("#user_video"))
      this.streamManager.addVideoElement(this.$el)
    }
  },
  mounted () {
    this.videoWidthSelect()
    this.streamManager.addVideoElement(this.$el)
  },
  methods: {
    videoWidthSelect () {
      if (this.streamManager.stream.connection.role === 'SUBSCRIBER') {
        this.videoWidth = '30%'
        this.videoHeight = '30%'
      }
    },
  },
})
</script>
