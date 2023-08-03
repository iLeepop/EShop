import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: '首页',
    component: () => import('@/views/layout'),
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: '首页',
        component: () => import('@/views/home')
      },
      {
        path: '/user',
        name: '用户管理',
        component: () => import('@/views/user')
      },
      {
        path: '/bigType',
        name: '商品大类',
        component: () => import('@/views/bigType')
      },
      {
        path: '/smallType',
        name: '商品小类',
        component: () => import('@/views/smallType')
      },
      {
        path: '/product',
        name: '商品管理',
        component: () => import('@/views/product')
      },
      {
        path: 'order',
        name: '订单管理',
        component: () => import('@/views/order')
      },
      {
        path: '/modifyPassword',
        name: '修改密码',
        component: () => import('@/views/modifyPassword')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
