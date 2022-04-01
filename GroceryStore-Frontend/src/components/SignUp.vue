<template>
  <div>
    <div class="signup">
      <div>
        <b-dropdown id="dropDown" variant="outline-light" text="Please Choose Account type to Create">
          <b-dropdown-item>Customer</b-dropdown-item>
          <b-dropdown-item>Employee</b-dropdown-item>
          <b-dropdown-item>Owner</b-dropdown-item>
        </b-dropdown>
      </div>
      <h3>Username</h3>
      <input size="100" type="text" placeholder="Enter username" class="text_stuff">
      <h3>Email</h3>
      <input size="100" type="email" placeholder="Enter email" class="text_stuff">
      <h3>Address</h3>
      <input size="100" type="text" placeholder="Enter address" class="text_stuff">
      <h3>Password</h3>
      <input size="100" type="password" placeholder="Enter Password" class="text_stuff">
      <br>
      <br>
      <b-button type="submit" id="buttonColor">Sign up</b-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'signup',
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
  margin-left: -490px;
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

a {
  color: red;
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
