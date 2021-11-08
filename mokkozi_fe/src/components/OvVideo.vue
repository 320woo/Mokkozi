<template>
  <video autoplay />
</template>

<script>
export default ({
  name: 'OvVideo',

  props: {
    streamManager: Object
  },

  // watch: {
  //   streamManager () {
  //     this.streamManager.addVideoElement(this.$el)
  //   }
  // },
  mounted () {
    console.log('this.streamManager.addVideoElement(this.$el): ', this.streamManager.addVideoElement(this.$el))
    // this.streamManager.addVideoElement(this.$el).play()
  }
})
</script>
