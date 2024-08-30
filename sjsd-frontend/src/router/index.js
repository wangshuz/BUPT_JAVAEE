import Vue from 'vue'
import VueRouter from 'vue-router'
import App from '../App.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import( '../views/AboutView.vue')
  },
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
]

const router = new VueRouter({
  routes
})

export default router
