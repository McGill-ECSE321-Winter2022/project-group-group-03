<template>
  <div>
    <Header/>
    <h1 id="title">My Customer Profile</h1>
    <p class="form">Username: {{this.customer.username}}</p>
    <p class="form">Email: {{this.customer.email}}</p>
    <p class="form">Address: {{this.customer.address}} </p>
    <p class="form">Password: {{this.customer.password}}</p>
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
import Header from "./Header";
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
        this.error = "Cant be empty Password" /* <-- this */
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

