import Vue from "vue";
import Router from "vue-router";
import Login from "../components/Login";
import Schedule from "../components/Schedule";
import UpdateEmployee from "../components/UpdateEmployee";
import UpdateCustomer from "../components/UpdateCustomer";
import UpdateOwner from "../components/UpdateOwner";
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
	      path: '/Items',
	      name: 'Items',
	      component: Items
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



