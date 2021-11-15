<template>
  <div class="test_video">
  <video id="user_video" :width="videoWidth" :height="videoHeight"/>
  <!-- <video @play="addVideoStream"  id="user_video" autoplay :width="videoWidth" :height="videoHeight"/> -->
  </div>
</template>

<script>
import * as faceapi from 'face-api.js'
export default ({
  name: 'OvVideo',
  data: () => ({
    videoWidth: '100%',
    videoHeight: '100%'
  }),
  props: {
    streamManager: Object
  },
 watch: {
    streamManager () {
      this.streamManager.addVideoElement(document.querySelector("#user_video"))
      // this.streamManager.addVideoElement(this.$el)
    }
  },
  mounted () {
    this.videoWidthSelect()
    const video = document.querySelector("#user_video");
    // Promise.all([
    //   faceapi.nets.ssdMobilenetv1.loadFromUri('/weights'),
    //   faceapi.nets.faceLandmark68Net.loadFromUri('/weights'),
    //   faceapi.nets.faceRecognitionNet.loadFromUri('/weights')
    // ]).then(()=>{
          this.streamManager.addVideoElement(video);
    // }

    // )

  },
  methods: {
    videoWidthSelect () {
      if (this.streamManager.stream.connection.role === 'SUBSCRIBER') {
        this.videoWidth = '30%'
        this.videoHeight = '30%'
      }
    },
    addVideoStream () {

      const video = document.querySelector("#user_video");
      const testVideo = document.querySelector('.test_video')
      testVideo.append(video)
      // this.streamManager.addVideoElement(video);
      // video.enabled = true;
        if(video.paused || video.ended)
        {return setTimeout(() => onPlay())}

        if (document.querySelector("canvas")) {
        document.querySelector("canvas").remove()};

        const minConfidence = 0.3
        const maxResult = 100
        const options = new faceapi.SsdMobilenetv1Options({minConfidence,maxResult})

        const canvas = faceapi.createCanvasFromMedia(video);
        testVideo.append(canvas);

        const displaySize = { width:video.width,height:video.height };
        faceapi.matchDimensions(canvas, displaySize);

        setInterval(async ()=>{
        const detections = await faceapi
        .detectAllFaces(video,options)
        .withFaceLandmarks();

        const resizedDetections = faceapi.resizeResults(detections,displaySize);
        canvas.getContext("2d").clearRect(0,0,canvas.width,canvas.height);
        faceapi.draw.drawDetections(canvas,resizedDetections);
        faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);

      },100);

    }
  },
})
</script>
