<template>

<div style="position: relative" class="margin">
  <video @play="onPlay()" width="720" height="560" id="inputVideo" autoplay muted></video>
  <canvas id="overlay" />
  <h1>hello</h1>
</div>
</template>
<script>
// import router from '../../router'
// import axios from 'axios'
import * as faceapi from 'face-api.js'
export default {
  name: 'FaceDetection',
  data (){
    return {
    }
  },
  created(){
    //모델 로드
          Promise.all([
  faceapi.nets.ssdMobilenetv1.loadFromUri("/weights"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/weights"),
  faceapi.nets.faceRecognitionNet.loadFromUri("/weights"),
]).then(
  this.run);
  },
  methods:{
    run(){

      const video = document.getElementById('inputVideo')
    navigator.mediaDevices
    .getUserMedia({ video: true, audio: true })
    .then(function (stream) {
      video.srcObject = stream;
    })
    .catch(function (err) {
      console.log(err);
    });

    },
    onPlay(event){
    console.log("hello?")
      const videoEl = document.getElementById('inputVideo')
      if(videoEl.paused || videoEl.ended)
        {return setTimeout(() => onPlay())}
    const minConfidence = 0.3
    const maxResult = 100
    const options = new faceapi.SsdMobilenetv1Options({minConfidence,maxResult})


      // const result = faceapi.detectSingleFace(videoEl, options)

      const displaySize = {width:videoEl.width,height:videoEl.height};

      // console.log(result)

      // if (result) {
      //   const canvas = document.getElementById('overlay')
      //   const dims = faceapi.matchDimensions(canvas, displaySize)
      //   faceapi.draw.drawDetections(canvas, faceapi.resizeResults(result, dims))
      // }

      // setTimeout(() => onPlay())
      const canvas = document.getElementById('overlay')
      console.log(canvas);
      console.log(videoEl)
      faceapi.matchDimensions(canvas, displaySize,true);
      setInterval(async ()=>{
        const detections = await faceapi
        .detectAllFaces(videoEl,options)
        .withFaceLandmarks();

        const resizedDetections = faceapi.resizeResults(detections,displaySize);
        canvas.getContext("2d").clearRect(0,0,canvas.width,canvas.height);
        faceapi.draw.drawDetections(canvas,resizedDetections);
        faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);

      },100);


    },
//     onInputSizeChanged(e) {
//   changeInputSize(e.target.value)
//   updateResults()
// },
//     changeInputSize(size) {
//   let inputSize = 512

//   const inputSizeSelect = $('#inputSize')
//   inputSizeSelect.val(inputSize)
//   inputSizeSelect.material_select()
// },
// initFaceDetectionControls() {
//   let selectedFaceDetector = SSD_MOBILENETV1
//   const faceDetectorSelect = $('#selectFaceDetector')
//   faceDetectorSelect.val(selectedFaceDetector)
//   faceDetectorSelect.on('change', onSelectedFaceDetectorChanged)
//   faceDetectorSelect.material_select()

//   const inputSizeSelect = $('#inputSize')
//   inputSizeSelect.val(inputSize)
//   inputSizeSelect.on('change', onInputSizeChanged)
//   inputSizeSelect.material_select()
// }
  },

}
</script>
<style>

</style>
