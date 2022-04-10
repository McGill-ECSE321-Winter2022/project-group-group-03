import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

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
  created() {
    //set the account type to be empty again when someone who is already logged in is redirected to this page
    //For example when the owner is logged but wishes to create an employee account
    this.this_accountType = ''
    if (typeof sessionStorage.accountType !== 'undefined') this.this_accountType = sessionStorage.accountType
  },
  methods: {
    create: function (){
      //if no account type has been selected, an error message is shown as an alert
      if (this.create_accountType_entered==='') this.create_error = "Please Choose Account Type"
      //customer create
      else if (this.create_accountType_entered==="Customer"){
        //this is the create customer method from the CustomerRestController
        AXIOS.post('/customer?username='.concat(this.create_username_entered,"&password=", this.create_password_entered,"&email=",this.create_email_entered,"&address=",this.create_address_entered))
          .then((response) => {
            this.$router.push({ name: "Login" }) //after the account has been created, the user is redirected to the login page
          })
          //catches any error thrown by the rest controller
          .catch((error) => {this.create_error = error.response.data/* <-- this */ });
      }
      //employee create
      else if (this.create_accountType_entered==="Employee"){
        //this is the create employee method from the EmployeeRestController
        AXIOS.post('/employee?username='.concat(this.create_username_entered,"&email=", this.create_email_entered,"&password=",this.create_password_entered,"&address=",this.create_address_entered))
          .then((response) => {
            this.$router.push({ name: "Login" }) //after the account has been created, the user is redirected to the login page
          })
          //catches any error thrown by the rest controller
          .catch((error) => {this.create_error = error.response.data/* <-- this */ });
      }
    },
    //method to change the string shown in the dropdown menu when an account type is selected
    changeMessage: function (accountType) {
      //if the same account type has already been chosen, the prompt is brought back to choose again
      if (this.account_msg===accountType){
        this.account_msg="Please Choose Account Type to Create"
      }
      //else it is set to the one the user has selected
      else{this.account_msg = accountType}
    },
    //sets the account type to the one the user has selected
    setAccountType: function (accountType) {
      this.create_accountType_entered = accountType
    },
    //function to see if an error has occurred, and therefore an alert must be shown
    setAlert: function () {
      return this.create_error !== ""
    },
    //sets the error back to empty when the alert is dismissed
    setErrorEmpty: function() {
      this.create_error = ""
    }
  }
}


