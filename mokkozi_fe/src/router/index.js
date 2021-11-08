import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Test from '../views/test/Test.vue'
import Login from '../views/user/Login.vue'
import Join from '../views/user/Join.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/board',
    name: 'Board',
    component: () => import('../views/board/Board.vue')
  },
  {
    path: '/comment',
    name: 'Comment',
    component: () => import('../components/Comment.vue')
  },
  {
    path: '/openvidu',
    name: 'Openvidu',
    component: () => import('../views/openvidu/Openvidu.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/face',
    name: 'FaceDetection',
    component: () => import('../components/FaceDetection.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
