<template>
  <div>
    <div class="signup">
      <div>
        <b-dropdown id="dropDown" variant="danger" :text="account_msg">
          <b-dropdown-item @click="changeMessage('Customer'); setAccountType('Customer')">Customer</b-dropdown-item>
          <b-dropdown-item @click="changeMessage('Employee'); setAccountType('Employee')">Employee</b-dropdown-item>
          <b-dropdown-item @click="changeMessage('Owner'); setAccountType('Owner')">Owner</b-dropdown-item>
        </b-dropdown>
      </div>
      <h3>Username</h3>
      <input size="100" type="text" placeholder="Enter username" class="text_stuff" v-model="create_username_entered">
      <h3>Email</h3>
      <input size="100" type="email" placeholder="Enter email" class="text_stuff" v-model="create_email_entered">
      <h3>Address</h3>
      <input size="100" type="text" placeholder="Enter address" class="text_stuff" v-model="create_address_entered">
      <h3>Password</h3>
      <input size="100" type="password" placeholder="Enter Password" class="text_stuff" v-model="create_password_entered">
      <br>
      <br>
      <b-button type="submit" id="buttonColor" @click="create()">Sign up</b-button>
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
