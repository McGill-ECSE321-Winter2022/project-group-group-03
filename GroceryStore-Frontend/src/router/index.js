import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Employee from '@/components/Employee'
import Items from "@/components/Items";


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path: '/Employee',
      name: 'Employee',
      component: Employee
    },
    {
      path: '/Items',
      name: 'Items',
      component: Items
    }

  ]
})
