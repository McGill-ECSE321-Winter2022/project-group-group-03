import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Employee from '@/components/Employee'
import SignUp from "@/components/SignUp";
import OwnerBH from "@/components/OwnerBH";
import OwnerHoliday from "../components/OwnerHoliday";
import OwnerEmployee from "../components/OwnerEmployee.vue";
import OwnerItemCreate from "../components/OwnerItemCreate.vue";
import OwnerItemUpdate from "../components/OwnerItemUpdate.vue";



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
      path: '/OwnerItemCreate',
      name: 'OwnerItemCreate',
      component: OwnerItemCreate
    },
    {
      path:'/OwnerItemUpdate',
      name: 'OwnerItemUpdate',
      component: OwnerItemUpdate
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
    }

  ]
})
