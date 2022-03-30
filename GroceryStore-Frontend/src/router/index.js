import Vue from "vue";
import Router from "vue-router";
import Login from "../components/Login";
import EventRegistrtion from "../components/EventRegistration";
import UpdateProfile from "../components/UpdateProfile";



Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path: '/UpdateProfile',
      name: 'UpdateProfile',
      component: UpdateProfile
    }
  ]
})
