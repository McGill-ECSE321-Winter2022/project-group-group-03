<template>
  <div>
    <Header/>
    <h1 id="title"> My Work Shift Schedule</h1>
    <table class="tablestyle" align="center">
      <tr class="trstyle">
        <td> Shift ID </td>
        <td> Day </td>
        <td> Start Time </td>
        <td> End Time </td>
      </tr>
      <tr class="trstyle" v-for="shift in shifts">
        <td>{{shift.shiftID}}</td>
        <td>{{ shift.day }}</td>
        <td>{{ shift.startTime }}</td>
        <td>{{ shift.endTime }}</td>
      </tr>
    </table>
    <Footer/>
  </div>
</template>

<script src="./workshift.js">
</script>

<style>

#title{
  margin-top: 5%;
}
.tablestyle {
  margin-top: 100px;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}
.trstyle:nth-child(even) {
  background-color: #dddddd;
}

</style>
