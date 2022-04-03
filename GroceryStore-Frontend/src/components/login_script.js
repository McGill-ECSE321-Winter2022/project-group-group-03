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
  name: 'Login',
  data() {
    return {
      msg: "Welcome to Dr. Kanaan's Online Grocery Store",
      login_msg: "Please Choose Account Type to Login as",
      login_username_entered: '',
      login_password_entered: '',
      login_accountType_entered: '',
      login_accountType: '',
      login_username: '',
      login_error: '',
      logged_in: false
    }
  },
  methods: {
    login: function (){
      //customer login
      console.log("here")
      console.log(this.login_accountType_entered)
      console.log(this.login_username_entered)
      console.log(this.login_password_entered)
      if (this.login_accountType_entered==="Customer"){
        AXIOS.get('/customer_login?username='.concat(this.login_username_entered,"&password=", this.login_password_entered))
            .then((response) => {
              this.login_accountType = "Customer"
              this.login_username = this.login_username_entered
              this.logged_in = true
              sessionStorage.logged_in = true
              sessionStorage.username = response.data.username
              sessionStorage.accountType="Customer"
              this.$router.push({ name: "Items" })
            })
          .catch(e => {
            this.login_error = e.response.data;
          })
      }
      //employee login
      if (this.login_accountType_entered==="Employee"){
        AXIOS.get('/employee_login?username='.concat(this.login_username_entered,"&password=", this.login_password_entered), {responseType: "json"})
            .then((response) => {
              this.login_accountType = "Employee"
              this.login_username = this.login_username_entered
              this.logged_in = true
              sessionStorage.logged_in = true
              sessionStorage.username = response.data.username
              sessionStorage.accountType="Employee"
              this.$router.push({ name: "Items" })
            })

          .catch(e => {
            console.log(e)
            this.login_error = e.response.data /* <-- this */
            console.log(this.login_error)
          });
      }
      //owner login
      if (this.login_accountType_entered==="Owner"){
        AXIOS.get('/owner_login?username='.concat(this.login_username_entered,"&password=", this.login_password_entered), {responseType: "json"})
            .then((response) => {
              this.login_accountType = "Owner"
              this.login_username = this.login_username_entered
              this.logged_in = true
              sessionStorage.logged_in = true
              sessionStorage.username = response.data.username
              sessionStorage.accountType="Owner"
              this.$router.push({ name: "Items" })//TODO: change the owner page
            })
          .catch(e => {
            console.log(e)
            this.login_error = e.response.data /* <-- this */
            console.log(this.login_error)
          });


      }
      this.login_username_entered = ''
      this.login_password_entered = ''
      this.login_accountType_entered = ''
      console.log(this.login_error==="")
    },
    changeMessage: function (accountType) {
      if (this.login_msg===accountType) {
        this.login_msg = "Please Choose Account Type to Login as"
      }
      else {
        this.login_msg = accountType
      }
    },
    setAccountType: function (accountType) {
      this.login_accountType_entered = accountType
    },
    setAlert: function () {
      return this.login_error !== ""
    },
    setError: function(message) {
      this.login_error = message
    }
  }
}


