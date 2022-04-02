<template>
  <div>
    <div class="login">
      <h1>{{ msg }}</h1>
      <h4>New Here? <a href="http://127.0.0.1:8087/#/signup" target="_blank" id="bold">Create Account</a></h4>
      <div>
        <b-dropdown id="dropDown" variant="danger" :text="login_msg">
          <b-dropdown-item @click="changeMessage('Customer'); setAccountType('Customer'); setError('')">Customer</b-dropdown-item>
          <b-dropdown-item @click="changeMessage('Employee'); setAccountType('Employee'); setError('')">Employee</b-dropdown-item>
          <b-dropdown-item @click="changeMessage('Owner'); setAccountType('Owner'); setError('')">Owner</b-dropdown-item>
        </b-dropdown>
      </div>
      <h3>Username</h3>
      <input size="50" type="text" placeholder="Enter username" class="text_stuff" v-model="login_username_entered">
      <h3>Password</h3>
      <input size="50" type="password" placeholder="Enter Password" class="text_stuff" v-model="login_password_entered">
      <br>
      <b-button variant="outline_light" @click="setError('Relax and try to remember your password')" target="_blank" id="left">Forget Password?</b-button>
      <br>
      <br>
      <b-button type="submit" id="buttonColor" @click="login();">Sign in</b-button>
      <br>
      <br>
      <br>
      <b-alert  :show="setAlert()" dismissible variant="danger" @dismissed="changeMessage(login_msg)">
        {{login_error}}
      </b-alert>
    </div>
  </div>
</template>

<script src="./login_script.js">
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1{
  font-weight: lighter;
  text-align: center;

}

.text_stuff {
  margin-left: -380px;
  margin-bottom: 20px;
}

#left {
  margin-left: -660px;
  background-color: Transparent;
  background-repeat:no-repeat;
  border: none;
  cursor:pointer;
  overflow: hidden;
  color: #e03444;
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
  margin-left: -460px;
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

#bold {
  font-weight: 900;
}

a {
  color: #e03444;
}
.login {
  position:absolute;
  width:840px;
  height:700px;
  left:50%;
  top:50%;
  margin-left:-420px;
  margin-top:-250px;
}
</style>
