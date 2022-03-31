import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Employee from '@/components/Employee'
import SignUp from "@/components/SignUp";
import OwnerBH from "@/components/OwnerBH";
import OwnerItem from "../components/OwnerItem";
import OwnerHoliday from "../components/OwnerHoliday";


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
      path: '/OwnerItem',
      name: 'OwnerItem',
      component: OwnerItem
    },
    {
    path: '/OwnerHoliday',
    name: 'OwnerHoliday',
    component: OwnerHoliday
    }

  ]
})
