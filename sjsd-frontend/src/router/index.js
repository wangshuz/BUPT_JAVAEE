import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import( '../views/Login.vue')
  },
  {
    path: '/setnickname',
    name: 'SetNickname',
    component: () => import( '../components/SetNickname.vue')
  },
  {
    path: '/setpassword',
    name: 'SetPassword',
    component: () => import( '../components/SetPassword.vue')
  },
  {
    path: '/setaddress',
    name: 'SetAddress',
    component: () => import( '../components/SetAddress.vue')
  },
  {
    path: '/',
    name: 'Home',
    component: () => import( '../views/HomePage.vue')
  },
  {
    path: '/uploadpicture',
    name: 'UploadPicture',
    component: () => import( '../components/UploadPicture.vue')
  },
  {
    path: '/mch',
    name: 'MchHome',
    component: () => import( '../views/MchHome.vue'),
    children: [
      {
        path: 'main',
        name: 'MchMain',
        component: () => import( '../components/MCH/MchMain.vue')
      },
      {
        path: 'order',
        name: 'MchOrder',
        component: () => import( '../components/MCH/MchOrder.vue')
      },
      {
        path:'menu',
        name:'MchMenu',
        component: () => import( '../components/MCH/MchMenu.vue')
      },
      {
        path:'detail',
        name:'MchDetail',
        component: () => import( '../components/MCH/MchDetail.vue')
      },
      {
        path:'data',
        name:'MchData',
        component: () => import( '../components/MCH/MchData.vue')
      },
    ]
  },
  {
    path: '/clt',
    name: 'CltHome',
    component: () => import( '../views/CltHome.vue'),
    children: [
      {
        path: 'main',
        name: 'CltMain',
        component: () => import( '../components/CLT/CltMain.vue')
      },
      {
        path: 'order',
        name: 'CltOrder',
        component: () => import( '../components/CLT/CltOrder.vue')
      },
      {
        path: 'payment',
        name: 'CltPayment',
        component: () => import( '../components/CLT/CltPayment.vue')
      },
      {
        path: 'store',
        name: 'CltStore',
        component: () => import( '../components/CLT/CltStore.vue')
      },
      {
        path: 'orderDetail',
        name: 'CltOrderDetail',
        component: () => import( '../components/CLT/CltOrderDetail.vue')
      },
    ]
  },
  
  
]

const router = new VueRouter({
  routes
})

export default router
