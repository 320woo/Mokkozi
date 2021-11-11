<template>
<!-- <div> -->
  <video autoplay/>
  <!-- <canvas id="overlay" />
  </div> -->
</template>

<script>
// import * as faceapi from 'face-api.js'
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
    //             this.streamManager.stream.applyFilter("FaceOverlayFilter")
    // .then(filter => {
    //   filter.execMethod(
    //     "setOverlayedImage",{
    //             "uri":"https://cdn.pixabay.com/photo/2013/07/12/14/14/derby-148046_960_720.png",
    //             "offsetXPercent":"-0.2F",
    //             "offsetYPercent":"-0.8F",
    //             "widthPercent":"1.3F",
    //             "heightPercent":"1.0F"
    //     });
    // });
    // console.log("manager",this.streamManager.stream)
    // console.log("stream",this.streamManager.stream)
    },
  methods:{

  }

})
</script>
