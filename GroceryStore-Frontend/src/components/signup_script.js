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
      create_error: '',
      this_accountType: '',
    }
  },
  created(){
    this.this_accountType = ''
    if (typeof sessionStorage.accountType !== 'undefined') this.this_accountType = sessionStorage.accountType
    console.log(this.this_accountType)
  },
  methods: {
    create: function (){
      //customer create
      console.log("here")
      console.log(this.create_accountType_entered)
      console.log(this.create_username_entered)
      console.log(this.create_password_entered)
      if (this.create_accountType_entered==='') this.create_error = "Please Choose Account Type"
      else if (this.create_accountType_entered==="Customer"){
        AXIOS.post('/customer?username='.concat(this.create_username_entered,"&password=", this.create_password_entered,"&email=",this.create_email_entered,"&address=",this.create_address_entered))
          .then((response) => {
            this.$router.push({ name: "Login" })
          })
          .catch((error) => {this.create_error = error.response.data/* <-- this */ });
      }
      //employee create
      else if (this.create_accountType_entered==="Employee"){
        AXIOS.post('/employee?username='.concat(this.create_username_entered,"&email=", this.create_email_entered,"&password=",this.create_password_entered,"&address=",this.create_address_entered))
          .then((response) => {
            this.$router.push({ name: "Login" })
          })
          .catch((error) => {this.create_error = error.response.data/* <-- this */ });
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
    },
    setAlert: function () {
      return this.create_error !== ""
    },
    setErrorEmpty: function() {
      this.create_error = ""
    }
  }
}


