import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


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
      // if no account type has been selected, an error message is thrown
      if (this.login_accountType_entered==='') this.login_error = "Please Choose Account Type"
      //customer login
      if (this.login_accountType_entered==="Customer"){
        //this is the login customer method from the CustomerRestController
        AXIOS.get('/customer_login?username='.concat(this.login_username_entered,"&password=", this.login_password_entered))
            .then((response) => {
              this.login_accountType = "Customer"
              this.login_username = this.login_username_entered
              this.logged_in = true
              sessionStorage.logged_in = true
              sessionStorage.username = response.data.username
              sessionStorage.accountType="Customer"
              this.$router.push({ name: "Items" }) //customer is redirected to the items page
            })
          .catch(e => {
            //catch any error that is thrown by the rest controller
            this.login_error = e.response.data;
            console.log(this.login_error)
          })
      }
      //employee login
      if (this.login_accountType_entered==="Employee"){
        //this is the login employee method from the EmployeeRestController
        AXIOS.get('/employee_login?username='.concat(this.login_username_entered,"&password=", this.login_password_entered), {responseType: "json"})
            .then((response) => {
              this.login_accountType = "Employee"
              this.login_username = this.login_username_entered
              this.logged_in = true
              sessionStorage.logged_in = true
              sessionStorage.username = response.data.username
              sessionStorage.accountType="Employee"
              console.log(sessionStorage.accountType)
              this.$router.push({ name: "Items" }) //employee is redirected to the items page
            })

          .catch(e => {
            //catch any error that is thrown by the rest controller
            this.login_error = e.response.data /* <-- this */
            console.log(this.login_error)
          });
      }
      //owner login
      if (this.login_accountType_entered==="Owner"){
        //this is the login owner method from the OwnerRestController
        AXIOS.get('/owner_login?username='.concat(this.login_username_entered,"&password=", this.login_password_entered), {responseType: "json"})
            .then((response) => {
              this.login_accountType = "Owner"
              this.login_username = this.login_username_entered
              this.logged_in = true
              sessionStorage.logged_in = true
              sessionStorage.username = response.data.username
              sessionStorage.accountType="Owner"
              console.log(sessionStorage.accountType)
              this.$router.push({ name: "UpdateOwner" }) //owner is redirected to his profile page
            })
          .catch(e => {
            //catch any error thrown by the rest controller
            this.login_error = e.response.data /* <-- this */
            console.log(this.login_error)
          });


      }
      //make sure user's personal information is not used again
      //session storage already takes care of the user's required info
      this.login_username_entered = ''
      this.login_password_entered = ''
      this.login_accountType_entered = ''
    },
    //method to change the string shown in the dropdown menu when an account type is selected
    changeMessage: function (accountType) {
      if (this.login_msg===accountType) {
        //if the same account type has already been chosen, the prompt is brought back to choose again
        this.login_msg = "Please Choose Account Type to Login as"
      }
      else {
        //else it is set to the one the user has selected
        this.login_msg = accountType
      }
    },
    //sets the account type to the one the user has selected
    setAccountType: function (accountType) {
      this.login_accountType_entered = accountType
    },
    //function to see if an error has occurred, and therefore an alert must be shown
    setAlert: function () {
      return this.login_error !== ""
    },
    //sets the error back to empty when the alert is dismissed
    setError: function(message) {
      this.login_error = message
    }
  }
}


