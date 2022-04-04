<template>
  <div>
    <Header/>
    <h1 id="title">My Customer Profile</h1>
    <p id="username" class="form">Username: {{this.customer.username}}</p>
    <p id="email" class="form">Email: {{this.customer.email}}</p>
    <p id="address" class="form">Address: {{this.customer.address}} </p>
    <p id="password" class="form">Password: {{this.customer.password}}</p>
    <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button>
    <b-button v-b-modal.modal-prevent-closing2 class="btn">Update Address</b-button>
    <br>
    <br>
    <b-alert :show="setAlert()" dismissible variant="danger">{{error}}</b-alert>
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
import Header from "./EmployeeNav"
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function CustomerDTO(username,password,email,address){
  this.username = username
  this.password = password
  this.email = email
  this.address = address
}
export default {
  name: "UpdateCustomer",
  data() {
    return {
      customer:{
        username: 'Seb',
        password: '',
        email: '',
        address: '',
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
    // this.username = 'Mark'
    // this.password = 'password'
    // this.email = 'email'
    // this.address = '998 rue Address'
    this.getCustomer()

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
      AXIOS.put('/editPassword/'.concat(this.customer.username,"?password=", this.newPassword))
        .then((response) =>{
          this.customer = response.data
        }).catch(e => {
        this.error = e.response.data /* <-- this */
      });
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    },
    handleSubmitAddress(){
      AXIOS.put('/editAddress/'.concat(this.customer.username,"?address=", this.newAddress))
        .then((response) =>{
          this.customer = response.data
        })
        .catch(e => {
          this.error = "Cant be empty Address" /* <-- this */
        });
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing2')
      })
    },
    getCustomer:function(){
      AXIOS.get('/customer/'.concat(this.customer.username))
        .then((response) =>{
          this.customer= response.data
        });
    },
    setAlert: function () {
      return this.error !== ""
    }
    // ,
    // createCustomer: function (){
    //   AXIOS.post("/customer?username=Seb&password=jeetisnice&email=Lmao@mmks.ca&address=Bobsville",{},{})
    //     .then(response => {
    //       console.log(response.data)
    //     })
    // }
  }
}
</script>

<style scoped>
#title{
  margin-top: 4%;
  color: #e03444;
  font-weight: bold;

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
#inline{display:inline;}

.form{
  font-size: xx-large;
}
.btn{
  margin-top: 4%;
}

</style>

