import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Employee from '@/components/Employee'
import Bottom from '@/components/Bottom'
import Checkout from "../components/Checkout";
import Cart from "../components/Cart";

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
      path: '/Bottom',
      name: 'Bottom',
      component: Bottom
    },
    {
      path: '/Checkout',
      name: 'Checkout',
      component: Checkout
    },
    {
      path: '/Cart',
      name: 'Cart',
      component: Cart
    },


  ]
})
