<template>
  <div>
    <Header/>
    <h1 id="title">My Employee Profile</h1>
    <div class="form">
      <b-container fluid>
        <b-row >
          <b-col offset-md="5" md="auto">Username:</b-col>
          <b-col md="auto" style="margin-left: 0;">{{this.employee.username}}</b-col>
        </b-row>
        <b-row >
          <b-col offset-md="5" md="auto"> Email:</b-col>
          <b-col md="auto" style="margin-left: 0;">{{this.employee.email}}</b-col>
        </b-row>
        <b-row >
          <b-col offset-md="5" md="auto"> Address: </b-col>
          <b-col  md="auto" style="margin-left: 0;">{{this.employee.address}}</b-col>
        </b-row>
        <b-row >
          <b-col offset-md="5" md="auto">Working Status: </b-col>
          <b-col md="auto" style="margin-left: -1%;">{{this.employee.workingStatus}}</b-col>
        </b-row>
      </b-container>
      </div>
      <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button>
      <b-button v-b-modal.modal-prevent-closing2 class="btn">Update Address</b-button>
      <br>
      <br>
      <div style="max-width: 50%; margin-left:25vw">
        <b-alert :show="setAlert()" @dismissed="setErrorEmpty()" dismissible variant="danger">{{error}}</b-alert>
      </div>
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
          <input id="password_input" type="password" v-model="newPassword" :placeholder="this.employee.password">
        </b-form-group>
      </form>
    </b-modal>

    <b-modal
      id="modal-prevent-closing2"
      ref="modal"
      title="Update your address"
      @ok="handleOk2"
    >
      <form ref="form" @submit.stop.prevent="handleSubmitAddress">
        <b-form-group
          label="Please enter a new address"
          label-for="address-input"
          invalid-feedback="Address is required"
        >
          <input id="address_input" type="text" v-model="newAddress" placeholder="New Address">
        </b-form-group>
      </form>
    </b-modal>
    <Footer/>
  </div>

</template>

<script>
import Header from "./Cart"
import Footer from "./Footer"

import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function EmployeeDTO(username,password,email,address,workingStatus){
  this.username = username
  this.password = password
  this.email = email
  this.address = address
  this.workingStatus = workingStatus
}
export default {
  name: "UpdateEmployee",
  data() {
    return {
      employee: {
        username: '',
        password: '',
        email: '',
        address: '',
        workingStatus: '',
      },
      newPassword: '',
      newAddress: '',
      response:'',
      error:''
    }
  },
  components: {
    Header,
    Footer
  },
  mounted: function () {
    this.getEmployee()
  },
  methods: {
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleOk2(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmitAddress()
    },
    handleSubmit() {
      // Hide the modal manually
      AXIOS.put('/update_employee_password?username='.concat(this.employee.username,"&password=", this.newPassword))
        .then((response) =>{
          this.employee = response.data
        }).catch(e => {
        this.error =  e.response.data /* <-- this */

      });
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
        this.newPassword =''
      })
    },
    handleSubmitAddress(){
      AXIOS.put('/update_employee_address?username='.concat(this.employee.username,"&address=", this.newAddress))
        .then((response) =>{
          this.employee = response.data
        })
        .catch(e => {
        this.error =  e.response.data /* <-- this */
      });
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing2')
      })
    },
    getEmployee: function(){
      let username = sessionStorage.username
      this.employee.username = username
      AXIOS.get('/employee?username='.concat(sessionStorage.username), {responseType: "json"})
        .then((response) =>{
          this.response = response.data;
          console.log(this.response)
          this.employee=this.response
        });
    },
    setAlert: function () {
      return this.error !== ""
    },
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
}
#username{
  margin-right: 9%;
}
#email{
  margin-right: 17%;
}
#address{
  margin-right: 15%;
}
#password{
  margin-right: 14%;
}
#work{
  margin-right: 9%;
}

</style>
