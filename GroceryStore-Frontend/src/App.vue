<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Red+Hat+Display:wght@300&display=swap');
#app {
  font-family: 'Red Hat Display', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#sideBarButton{
  border: solid transparent;
  color: white;
  margin-right: 10px;
  box-shadow: none!important;
  elevation: 0;
  transition-duration: 0.4s;
  cursor: pointer;
}
#sideBarButton:hover{
  background-color: red;
  color: black;
}
.itemNav{
  margin: 0px 100px;
  font-size:20px;
  color: white;
}
</style>
