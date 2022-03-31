import Vue from "vue";
import Router from "vue-router";
import Login from "../components/Login";
import UpdateProfile from "../components/UpdateProfile";
import Schedule from "../components/Schedule";
import Profile from "../components/Profile";


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
    },
    {
      path: '/Schedule',
      name: 'Schedule',
      component: Schedule,
    },
    {
      path: '/Profile',
      name: 'Profile',
      component: Profile,
    },
  ]
})
