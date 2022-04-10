<template>
  <div>
  <Header/>
  <h1 id="title">My Owner Profile</h1>
    <div class="form">
      <b-container fluid>
        <b-row >
          <!-- displays all the elements from the owner: username, email-->
          <b-col offset-md="5" md="auto">Username:</b-col>  <!--displays the username -->
          <b-col md="auto" style="margin-left: 0;">{{this.owner.username}}</b-col>
        </b-row>
        <b-row >
          <b-col offset-md="5" md="auto"> Email:</b-col> <!--displays the email -->
          <b-col md="auto" style="margin-left: 0;">{{this.owner.email}}</b-col>
        </b-row>

      </b-container>
    </div>
    <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button> <!-- if button is pressed update password -->
    <br>
    <br>
    <div style="max-width: 50%; margin-left:25vw">
      <b-alert :show="setAlert()" @dismissed="setErrorEmpty()" dismissible variant="danger">{{error}}</b-alert>  <!-- shows the error message -->
    </div>
    <!-- pop up message for the input form to update the password  -->
  <b-modal
    id="modal-prevent-closing"
    ref="modal"
    title="Update your password"
    @ok="handleOk"
  >
    <form ref="form" @submit.stop.prevent="handleSubmit">
      <b-form-group
        label="Please enter a new password"
        label-for="password-input"
        invalid-feedback="Password is required"
      >
        <input id="password_input" type="password" v-model="newPassword" :placeholder="this.owner.password">
      </b-form-group>
    </form>
  </b-modal>
  </div>
</template>

<script>
import Header from "./Cart"

import axios from 'axios'
var config = require('../../config')


var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
function OwnerDTO(username,password,email,address){
  this.username = username
  this.password = password
  this.email = email
}
export default {
  name: "UpdateOwner",
  data() {
    // the data that we are trying to retrieve and show to
    return {
      owner:{
        username: '',
        password: '',
        email: '',

      },
      newPassword: '',
      response: '',
      error:''
    }
  },
  // the components that we are importing
  components: {
    Header
  },
  // shows all the customers' information once the page has rendered
  mounted: function () {
    // this.username = 'Mark'
    // this.password = 'password'
    // this.email = 'email'
    this.getOwner()
  },
  // method that handles the modal which asks the customer to update their password
  methods: {
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },//once the button gets pressed it changes the password in the backend also
    handleSubmit() {
      AXIOS.put('/update_owner?username='.concat(this.owner.username,"&password=", this.newPassword))
        .then((response) =>{
          this.owner = response.data
        }).catch(e => {
        this.error = e.response.data /* <-- this */
      });
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
        this.newPassword =''
      })
    },// calls the backend to get all the information of the owner
    getOwner: function(){
      let username = sessionStorage.username
      this.owner.username = username
      AXIOS.get('/owner?username='.concat(this.owner.username))
        .then((response) =>{
          this.response = response.data;
          this.owner=this.response
        });
    },// the error gets displayed
    setAlert: function () {
      return this.error !== ""
    },// refreshes the error message so a new one gets displayed
    setErrorEmpty: function() {
      this.error = ""
    }
  }
}
</script>

<style scoped>
#title{
  margin-top: 50px;
  color: #e03444;
  font-weight: bold;

}
#inline{display:inline;}

.form{
  font-size: xx-large;
}
.btn{
  margin-top: 60px;
  background-color: #e03444;
  border-color: #e03444;;
}
#username{
  margin-right: 7%;
}
#email{
  margin-right: 17%;
}
#password{
  margin-right: 14%;
}
</style>
