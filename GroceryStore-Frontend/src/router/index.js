import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Employee from '@/components/Employee'
import Footer from '@/components/Footer'
import Checkout from "../components/Checkout";
import Cart from "../components/Cart";
import OwnerNav from "../components/OwnerNav";
import EmployeeNav from "../components/EmployeeNav";

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
      path: '/Footer',
      name: 'Footer',
      component: Footer
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
    {
      path: '/OwnerNav',
      name: 'OwnerNav',
      component: OwnerNav
    },
    {
      path: '/EmployeeNav',
      name: 'EmployeeNav',
      component: EmployeeNav
    },


  ]
})
