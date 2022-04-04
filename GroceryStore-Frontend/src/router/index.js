
import Vue from "vue";
import Router from "vue-router";

import Footer from '@/components/Footer'
import Checkout from "../components/Checkout";
import Cart from "../components/Cart";
import OwnerNav from "../components/OwnerNav";
import EmployeeNav from "../components/EmployeeNav";


import Login from "../components/Login";
import Schedule from "../components/Schedule";
import UpdateEmployee from "../components/UpdateEmployee";
import UpdateCustomer from "../components/UpdateCustomer";
import UpdateOwner from "../components/UpdateOwner";
import Items from "@/components/Items";
import SignUp from "@/components/SignUp";
import OwnerBH from "@/components/OwnerBH";
import OwnerHoliday from "../components/OwnerHoliday";
import OwnerEmployee from "../components/OwnerEmployee.vue";
import OwnerItemCreate from "../components/OwnerItemCreate.vue";




Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path: '/Items',
      name: 'Items',
      component: Items
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
    {
      path: '/Schedule',
      name: 'Schedule',
      component: Schedule,
    },
    {
      path: '/UpdateEmployee',
      name: 'UpdateEmployee',
      component: UpdateEmployee,
    },
    {
      path: '/signup',
      name: 'SignUp',

      component: SignUp
    },
    {
      path: '/OwnerBH',
      name: 'OwnerBH',
      component: OwnerBH
    },
    {
      path: '/OwnerItemCreate',
      name: 'OwnerItemCreate',
      component: OwnerItemCreate
    },
    {
      path: '/OwnerHoliday',
      name: 'OwnerHoliday',
      component: OwnerHoliday
    },
    {
      path: '/OwnerEmployee',
      name: 'OwnerEmployee',
      component: OwnerEmployee

    },
    {
      path: '/UpdateCustomer',
      name: 'UpdateCustomer',
      component: UpdateCustomer,
    },
    {
      path: '/UpdateOwner',
      name: 'UpdateOwner',
      component: UpdateOwner,

    }

  ]
})



