<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager"/>
    <!-- <canvas id="overlay" /> -->
    <!-- <div>{{playCanvas}}</div> -->
    <div><p>{{ clientData }}</p></div>
  </div>
</template>

<script>
import OvVideo from './OvVideo'
import * as faceapi from 'face-api.js'
export default {
  name: 'UserVideo',

  components: {
    OvVideo
  },

  props: {
    streamManager: Object
  },
    created(){
    //모델 로드
          Promise.all([
  faceapi.nets.ssdMobilenetv1.loadFromUri("/weights"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/weights"),
  faceapi.nets.faceRecognitionNet.loadFromUri("/weights"),
]).then();
  },
  computed: {
    clientData () {
      const { clientData } = this.getConnectionData()
      return clientData
    },
    // playCanvas(){
    //   const canvas = document.getElementById('overlay')
    //   canvas = this.onPlay()
    //   return canvas
    // }
  },
}
</script>
