<template>
  <div>
    <div class="signup">
      <div>
        <!-- dropdown menu to select the type of account to create-->
        <b-dropdown id="dropDown" variant="danger" :text="account_msg">
          <b-dropdown-item @click="changeMessage('Customer'); setAccountType('Customer'); setErrorEmpty()">Customer</b-dropdown-item>
          <!-- the dropdown will only include the employee option if the owner is already logged in to create it-->
          <b-dropdown-item v-if="this_accountType==='Owner'" @click="changeMessage('Employee'); setAccountType('Employee'); setErrorEmpty()">Employee</b-dropdown-item>
        </b-dropdown>
      </div>
      <!-- input field for the username of the user -->
      <h3>Username</h3>
      <input size="100" type="text" placeholder="Enter username" class="text_stuff" v-model="create_username_entered">
      <!-- input field for the email of the user -->
      <h3>Email</h3>
      <input size="100" type="email" placeholder="Enter email" class="text_stuff" v-model="create_email_entered">
      <!-- input field for the address of the user -->
      <h3>Address</h3>
      <input size="100" type="text" placeholder="Enter address" class="text_stuff" v-model="create_address_entered">
      <!-- input field for the password of the user -->
      <h3>Password</h3>
      <input size="100" type="password" placeholder="Enter Password" class="text_stuff" v-model="create_password_entered">
      <br>
      <br>
      <!-- sign up button which will redirect back to the login page -->
      <b-button type="submit" id="buttonColor" @click="create()">Sign up</b-button>
      <br>
      <br>
      <br>
      <!-- alert that will be shown only if an error has occurred while creating an account -->
      <!-- when dismissed all error message strings and account types are reset to let the user start afresh-->
      <b-alert  :show="setAlert()" dismissible variant="danger" @dismissed="changeMessage(account_msg); setAccountType(''); setErrorEmpty()">
        {{create_error}}
      </b-alert>
    </div>
  </div>
</template>

<script src = "./signup_script.js">
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1{
  font-weight: lighter;
  text-align: center;

}

.text_stuff {
  margin-bottom: 20px;
}

#buttonColor {
  background-color: #e03444;
  border-color: #e03444;
  text-align: left;
  margin-left: -720px;
}

#dropDown {
  background-color: #e03444;
  border-color: #e03444;
  margin-left: -490px;
  margin-bottom: 20px;
  min-width: 23vw;
}

h4{
  text-align: left;
  margin-left: 30px;
  margin-bottom: 50px;
}

h3 {
  text-align: left;
  margin-left: 30px;
}

a {
  color: #e03444;
}
.signup {
  position:absolute;
  width:840px;
  height:700px;
  left:50%;
  top:50%;
  margin-left:-420px;
  margin-top:-250px;
}
</style>
