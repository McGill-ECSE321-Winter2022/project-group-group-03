<template>
  <div>
    <br>
    <br>
    <br>
    <div class="logout">
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <h1>You have successfully logged out!</h1>
      <h4>We are sad to see you go 😢 To login again, please click <router-link id="bold" :to="{ name: 'Login' }">here</router-link></h4>
    </div>
  </div>
</template>

<script>
export default{
  created(){
    sessionStorage.clear()
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  font-weight: lighter;
  text-align: center;
}

#bold {
  font-weight: 900;
  color: #e03444;

}

.logout {
  position:absolute;
  width:840px;
  height:700px;
  left:50%;
  top:50%;
  margin-left:-420px;
  margin-top:-250px;
}
</style>
