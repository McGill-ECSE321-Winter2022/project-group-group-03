import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function EmployeeDTO (username, password){
  this.username = username
  this.password = password
}

function OwnerDTO (username, password) {
  this.username = username
  this.password = password
}

function CustomerDTO (username, password) {
  this.username = username
  this.password = password
}


export default {
  name: 'SignUp',
  data() {
    return {
      account_msg: "Please Choose Account Type to Create",
      create_username_entered: '',
      create_password_entered: '',
      create_accountType_entered: '',
      create_address_entered: '',
      create_email_entered: '',
      create_error: ''
    }
  },
  methods: {
    create: function (){
      //customer login
      console.log("here")
      console.log(this.create_accountType_entered)
      console.log(this.create_username_entered)
      console.log(this.create_password_entered)
      if (this.create_accountType_entered==="Customer"){
        AXIOS.post('/customer?username='.concat(this.create_username_entered,"&password=", this.create_password_entered,"&email=",this.create_email_entered,"address=",this.create_address_entered))
          .then((response) => {
          })
          .catch((error) => {this.create_error = "Invalid Username, Password, Email or Address"/* <-- this */ });
      }
      //employee login
      if (this.login_accountType_entered==="Employee"){
        AXIOS.post('/employee?username='.concat(this.create_username_entered,"&password=", this.create_password_entered,"&email=",this.create_email_entered,"address=",this.create_address_entered))
          .then((response) => {
          })
          .catch(e => {this.create_error = "Invalid Username, Password, Email or Address" /* <-- this */ });
      }
      //owner login
      if (this.login_accountType_entered==="Owner"){
        AXIOS.post('/owner?username='.concat(this.create_username_entered,"&password=", this.create_password_entered,"&email=",this.create_email_entered,"address=",this.create_address_entered))
          .then((response) => {
          })
          .catch(e => {this.create_error = "Invalid Username, Password, Email or Address" /* <-- this */ });
      }
      console.log(this.login_error)
    },
    changeMessage: function (accountType) {
      if (this.account_msg===accountType){
        this.account_msg="Please Choose Account Type to Create"
      }
      else{this.account_msg = accountType}
    },
    setAccountType: function (accountType) {
      this.create_accountType_entered = accountType
    }
  }
}


