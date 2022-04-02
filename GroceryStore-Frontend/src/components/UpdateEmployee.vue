<template>
  <div>
    <Header/>
    <h1 id="title">My Employee Profile</h1>
    <p class="form">Username: {{this.employee.username}}</p>
    <p class="form">Email: {{this.employee.email}}</p>
    <p class="form">Address: {{this.employee.address}} </p>
    <p class="form">Password: {{this.employee.password}}</p>
    <p class="form">Working Status:{{this.employee.workingStatus}}</p>
    <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button>
    <b-button v-b-modal.modal-prevent-closing2 class="btn">Update Address</b-button>
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
          <input id="password_input" type="password" v-model="newPassword" placeholder="New Password">
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
  </div>

</template>

<script>
import Header from "./Header";
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
      username: 'Jeet',
      password: '',
      email: '',
      address: '',
      workingStatus: '',
      },
      newPassword: '',
      newAddress: '',
      error:''
    }
  },
  components: {
    Header
  },
  mounted: function () {
    this.getEmployee()
    // this.username = 'Mark'
    // this.password = 'password'
    // this.email = 'email'
    // this.address = '998 rue Address'
    // this.workingStatus = 'Hired'
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
      this.employee.password=this.newPassword
      // Hide the modal manually
      // this.$nextTick(() => {
      //   this.$bvModal.hide('modal-prevent-closing')
      // })
    },
    handleSubmitAddress(){
      this.employee.address=this.newAddress
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    },
    getEmployee: function(){
      this.items.length = 0;
      AXIOS.get('/employee?username=Jeet')
        .then((response) =>{
          this.employee= response.data
        .catch(error => {this.error = error /* <-- this */ });
        });
    }
    // ,
    // createEmployee: function (){
    //   AXIOS.post("/employee?username=Jeet&email=jeetjeet@mail.com&password=1aq2w3&address=69420 jeet street",{},{})
    //     .then(response => {
    //       console.log(response.data)
    //     })
    // }
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

</style>
