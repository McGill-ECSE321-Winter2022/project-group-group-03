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
        </div>
      </tr>
      <tr>
        <td><b-form-input style="width:61%" id="searchemp1" placeholder="username" v-model="username"> </b-form-input></td>

      </tr>
      <tr>
        <td><b-form-input style="width:61%" id="searchemp1" placeholder="address" v-model="address"> </b-form-input></td>
      </tr>
      <tr>
        <td><b-form-input style="width:61%" id="searchemp2" placeholder="email" v-model="email"> </b-form-input></td>
      </tr>
      <tr id="trstyle">
        <td> Day</td>
        <td> Start Time</td>
        <td> End Time</td>
      </tr>
      <tr id="trstyle" v-for="shift in shifts">
        <td>{{ shift.day }}</td>
        <td><button id="EMPhourbutton" v-b-modal.modal-1>{{ shift.startTime }}</button></td>
        <b-modal id="modal-1" title="Update Hours">
          <div align="center">
            <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-1">
              <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour1('12:00')">12:00</b-dropdown-item>
            </b-dropdown>
          </div>
        </b-modal>
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
  margin-left: 37%;
}
#searchemp2 {
  margin-left: 37%;
  margin-bottom: 5%;
}
#create{
 margin-left: 740%;
}
#EMPhourbutton{
  background-color: Transparent;
  background-repeat:no-repeat;
  border: none;
  cursor:pointer;
  overflow: hidden;
}

</style>
