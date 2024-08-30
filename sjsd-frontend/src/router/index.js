import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/mch',
    name: 'MchHome',
    component: () => import( '../views/MchHome.vue')
  },
  {
    path: '/mch/main',
    name: 'MchMain',
    component: () => import( '../components/MCH/MchMain.vue')
  },
  {
    path: '/mch/order',
    name: 'MchOrder',
    component: () => import( '../components/MCH/MchOrder.vue')
  },
  {
    path: '/mch/menu',
    name: 'MchMenu',
    component: () => import( '../components/MCH/MchMenu.vue')
  },
  {
    path: '/mch/detail',
    name: 'MchDetail',
    component: () => import( '../components/MCH/MchDetail.vue')
  },
  {
    path: '/mch/data',
    name: 'MchData',
    component: () => import( '../components/MCH/MchData.vue')
  },
  {
    path: '/clt',
    name: 'CltHome',
    component: () => import( '../views/CltHome.vue')
  },
  {
    path: '/clt/main',
    name: 'CltMain',
    component: () => import( '../components/CLT/CltMain.vue')
  },
  {
    path: '/clt/order',
    name: 'CltOrder',
    component: () => import( '../components/CLT/CltOrder.vue')
  },
  {
    path: '/clt/payment',
    name: 'CltPayment',
    component: () => import( '../components/CLT/CltPayment.vue')
  },
  {
    path: '/clt/store',
    name: 'CltStore',
    component: () => import( '../components/CLT/CltStore.vue')
  },
  {
    path: '/clt/order/detail',
    name: 'CltOrderDetail',
    component: () => import( '../components/CLT/CltOrderDetail.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import( '../views/Login.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router