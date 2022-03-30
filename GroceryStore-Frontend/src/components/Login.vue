<template>
  <div>
  <div class="login">
    <h1>{{ msg }}</h1>
    <h4>New Here? <a href="" target="_blank" id="bold">Create Account</a></h4>
    <div>
      <b-dropdown id="dropDown" variant="outline-light" text="Login as">
        <b-dropdown-item>Customer</b-dropdown-item>
        <b-dropdown-item>Employee</b-dropdown-item>
        <b-dropdown-item>Owner</b-dropdown-item>
      </b-dropdown>
      </div>
    <h3>Username</h3>
    <input size="50" type="text" placeholder="Enter username" class="text_stuff">
    <h3>Password</h3>
    <input size="50" type="password" placeholder="Enter Password" class="text_stuff">
    <br>
    <a href="" target="_blank" id="left">Forget Password?</a>
    <br>
    <br>
    <b-button type="submit" id="buttonColor">Sign in</b-button>
  </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      msg: "Welcome to Dr. Kanaan's Online Grocery Store"

    }
  }
}
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

#buttonColor {
  background-color: red;
  border-color: red;
  text-align: left;
  margin-left: -720px;
}

#dropDown {
  background-color: red;
  border-color: red;
  margin-left: -675px;
  margin-bottom: 20px;
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
#left {
  margin-left: -660px;
}
a {
  color: red;
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
