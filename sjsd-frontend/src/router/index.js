import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/mch',
    name: 'MchHome',
    component: () => import( '../views/MCH/MchHome.vue')
  },
  {
    path: '/mch/order',
    name: 'MchOrder',
    component: () => import( '../views/MCH/MchOrder.vue')
  },
  {
    path: '/mch/menu',
    name: 'MchMenu',
    component: () => import( '../views/MCH/MchMenu.vue')
  },
  {
    path: '/mch/detail',
    name: 'MchDetail',
    component: () => import( '../views/MCH/MchDetail.vue')
  },
  {
    path: '/mch/data',
    name: 'MchData',
    component: () => import( '../views/MCH/MchData.vue')
  },
  {
    path: '/clt',
    name: 'CltHome',
    component: () => import( '../views/CLT/CltHome.vue')
  },
  {
    path: '/clt/order',
    name: 'CltOrder',
    component: () => import( '../views/CLT/CltOrder.vue')
  },
  {
    path: '/clt/payment',
    name: 'CltPayment',
    component: () => import( '../views/CLT/CltPayment.vue')
  },
  {
    path: '/clt/store',
    name: 'CltStore',
    component: () => import( '../views/CLT/CltStore.vue')
  },
  {
    path: '/clt/order/detail',
    name: 'CltOrderDetail',
    component: () => import( '../views/CLT/CltOrderDetail.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
