<template>
  <div>
    <Header />
    <b-form @submit="onSubmit" @reset="onReset" >
      <b-form-group id="input-group-1" label="Change password:" label-for="input-1">
        <b-form-input id="input-1" v-model="form.email" type="password" placeholder="Enter password" ></b-form-input>
        <b-button type="submit" variant="primary">Confirm</b-button>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
        <b-form-input id="input-2" v-model="form.name" placeholder="Enter name"></b-form-input>
        <b-button type="submit" variant="primary">Confirm</b-button>
      </b-form-group>

      <b-button type="reset" variant="info">Reset</b-button>
    </b-form>
  </div>
</template>


<script>
import Header from "../components/Header.vue"
export default {
  data() {
    return {
      form: {
        email: '',
        name: '',
        food: null,
        checked: []
      },
      foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
      show: true
    }
  },
  components: {
    Header
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.form.email = ''
      this.form.name = ''
      this.form.food = null
      this.form.checked = []
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
</script>

<style>
</style>
<!--<template>-->
<!--  <div id="app">-->
<!--    <router-view></router-view>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: 'app'-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--@import url('https://fonts.googleapis.com/css2?family=Red+Hat+Display:wght@300&display=swap');-->
<!--#app {-->
<!--  font-family: 'Red Hat Display', sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  text-align: center;-->
<!--  color: #2c3e50;-->
<!--}-->
<!--#sideBarButton{-->
<!--  border: solid transparent;-->
<!--  color: white;-->
<!--  margin-right: 10px;-->
<!--  box-shadow: none!important;-->
<!--  elevation: 0;-->
<!--  transition-duration: 0.4s;-->
<!--  cursor: pointer;-->
<!--}-->
<!--#sideBarButton:hover{-->
<!--  background-color: red;-->
<!--  color: black;-->
<!--}-->
<!--.itemNav{-->
<!--  margin: 0px 100px;-->
<!--  font-size:20px;-->
<!--  color: white;-->
<!--}-->
<!--</style>-->
