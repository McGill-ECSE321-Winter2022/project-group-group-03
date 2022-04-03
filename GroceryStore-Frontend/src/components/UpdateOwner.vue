<template>
  <div>
  <Header/>
  <h1 id="title">My Owner Profile</h1>
  <p id="username" class="form">Username: {{this.owner.username}}</p>
  <p id="email" class="form">Email: {{this.owner.email}}</p>
  <p id="password" class="form">Password: {{this.owner.password}}</p>
  <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button>
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
function OwnerDTO(username,password,email,address){
  this.username = username
  this.password = password
  this.email = email
}
export default {
  name: "UpdateOwner",
  data() {
    return {
      owner:{
        username: 'Roosh',
        password: '',
        email: ''
      },
      newPassword: '',
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
    this.getOwner()
  },
  methods: {
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit() {
      AXIOS.put('/update_owner?username='.concat(this.owner.username,"&password=", this.newPassword))
        .then((response) =>{
          this.owner = response.data
        }).catch(e => {
        this.error = "Cant be empty Password" /* <-- this */
      });
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    },
    getOwner: function(){
      AXIOS.get('/owner?username='.concat(this.owner.username))
        .then((response) =>{
          this.owner= response.data
        });
    },
    setAlert: function () {
      return this.error !== ""
    }
    // ,
    // createOwner: function (){
    //   AXIOS.post("/owner?username=Roosh&email=roosh@mail.com&password=1aq2w3",{},{})
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
