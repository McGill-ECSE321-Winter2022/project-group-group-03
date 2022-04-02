<template>
  <div>
    <Header/>
    <h1 id="title">Employee Work Schedule</h1>
    <table id="tablestyle" align="center">
      <tr>
        <div id="buttonseach">
          <td><b-form-input align="center" style="width:210%" id="searchemp" placeholder="search" v-model="usernameSearch"> </b-form-input></td>
          <td><b-button id="searchbutton">search</b-button></td>
          <td><b-button id="searchbutton1">fire</b-button></td>
          <td><b-button id="create">create</b-button></td>
        </div>
      </tr>
      <tr>
        <td><b-form-input style="width:58%" id="searchemp1" placeholder="username" v-model="username"> </b-form-input></td>

      </tr>
      <tr>
        <td><b-form-input style="width:58%" id="searchemp1" placeholder="address" v-model="address"> </b-form-input></td>
      </tr>
      <tr>
        <td><b-form-input style="width:58%" id="searchemp2" placeholder="email" v-model="email"> </b-form-input></td>
      </tr>
      <tr id="trstyle">
        <td> Day</td>
        <td> Start Time</td>
        <td> End Time</td>
      </tr>
      <tr id="trstyle" v-for="shift in shifts">
        <td>{{ shift.day }}</td>
        <td>{{ shift.startTime }}</td>
        <td>{{ shift.endTime }}</td>
      </tr>
    </table>

  </div>
</template>

<script src="./ownerEmployee.js">
</script>

<style>
#title {
  margin-top: 50px;
}

#tablestyle {
  margin-top: 60px;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}

#trstyle:nth-child(even) {
  background-color: #dddddd;
}
#searchemp{
  margin-left: 120%;
}
#searchbutton{
  margin-left: 690%;
}
#buttonseach{
  margin-left: 2%;
}
#searchbutton1{
  margin-left: 1070%;
}
#searchemp1{
  margin-left: 35%;
}
#searchemp2 {
  margin-left: 35%;
  margin-bottom: 5%;
}
#create{
 margin-left: 740%;
}


</style>
