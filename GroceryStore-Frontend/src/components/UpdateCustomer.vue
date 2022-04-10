<template>
  <div >
    <Header/>
    <h1 id="title">My Customer Profile</h1>
    <div class="form">
    <b-container fluid>
      <b-row >
<!-- displays all the elements from the customer: username, email, address-->
        <b-col offset-md="5" md="auto">Username:</b-col> <!--displays the username -->
        <b-col md="auto" style="margin-left: 0;">{{this.customer.username}}</b-col>
      </b-row>
      <b-row >
        <b-col offset-md="5" md="auto"> Email:</b-col> <!--displays the email -->
        <b-col md="auto" style="margin-left: 0;">{{this.customer.email}}</b-col>
      </b-row>
      <b-row >
        <b-col offset-md="5" md="auto"> Address: </b-col> <!--displays the address -->
        <b-col  md="auto" style="margin-left: 0;">{{this.customer.address}}</b-col>
      </b-row>
    </b-container>
  </div>
    <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button> <!-- if button is pressed update password -->
    <b-button v-b-modal.modal-prevent-closing2 class="btn">Update Address</b-button> <!-- if button is pressed update address -->
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
          <input id="password_input" type="password" v-model="newPassword" :placeholder="this.customer.password">
        </b-form-group>
      </form>
    </b-modal>
    <!-- pop up message for the input form to update the address  -->
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


function CustomerDTO(username,password,email,address){
  this.username = username
  this.password = password
  this.email = email
  this.address = address
}
export default {
  name: "UpdateCustomer",
  data() {
    // the data that we are trying to retrieve and show to
    return {
      customer:{
        username: '',
        password: '',
        email: '',
        address: '',
      },
      newPassword: '',
      newAddress: '',
      response:'',
      error:''
    }
  },
  // the components that we are importing
  components: {
    Footer,
    Header
  },
  // shows all the customers' information once the page has rendered
  created: function () {
    this.getCustomer()

  },
  // method that handles the modal which asks the customer to update their password
  methods: {
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },// method that handles the modal which asks the customer to update their address
    handleOk2(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmitAddress()
    },//once the button gets pressed it changes the password in the backend also
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
        this.newPassword =''
      })
    },//once the button gets pressed it changes the address in the backend also
    handleSubmitAddress(){
      AXIOS.put('/editAddress/'.concat(this.customer.username,"?address=", this.newAddress))
        .then((response) =>{
          this.customer = response.data
        })
        .catch(e => {
          this.error = e.response.data /* <-- this */
        });
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing2')
      })
    },// calls the backend to get all the information of that specific client
    getCustomer:function(){
      console.log(sessionStorage.username)
      AXIOS.get('/customer/'.concat(sessionStorage.username), {responseType: "json"})
        .then((response) =>{
          console.log(response)
          this.response = response.data;
          this.customer=this.response
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
  margin-top: 4%;
  margin-bottom: 3%;
  color: #e03444;
  font-weight: bold;

}
#username{
  /*margin-right: 20%;*/
}
#email{
  /*margin-right: 17%;*/
}
#address{
  /*margin-right: 15%;*/
}
#password{
  /*margin-right: 14%;*/
}
#inline{display:inline;}

.form{
  font-size: xx-large;

}
.btn{
  margin-top: 4%;
  margin-right: 2%;
}
p { display: inline;
  font-size: xx-large;}

</style>

