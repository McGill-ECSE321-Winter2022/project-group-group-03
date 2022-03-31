<template>
  <div>
    <Header />
    <div class="row">
      <div id="col5" class="col-6">
        <table id="table">
          <tr>
              <td>Mon.</td>
              <td><b-button id="hourbutton" v-b-modal.modal-1>{{this.buttonMessage}}</b-button></td>
              <b-modal id="modal-1" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-2">
                    <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour('01:00')">01:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour('02:00')">02:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour('03:00')">03:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour('04:00')">04:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour('05:00')">05:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour('06:00')">06:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour('07:00')">07:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour('08:00')">08:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour('09:00')">09:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour('10:00')">10:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour('11:00')">11:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour('12:00')">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="am">a.m</td>
              <div id="topm">
                <td>To</td>
                <td><b-button id="hourbutton2" v-b-modal.modal-2>{{this.buttonMessage}}</b-button></td>
                <b-modal id="modal-2" title="Update Hours">
                  <div align="center">
                    <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-2">
                      <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour('01:00')">01:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour('02:00')">02:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour('03:00')">03:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour('04:00')">04:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour('05:00')">05:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour('06:00')">06:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour('07:00')">07:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour('08:00')">08:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour('09:00')">09:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour('10:00')">10:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour('11:00')">11:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour('12:00')">12:00</b-dropdown-item>
                    </b-dropdown>
                  </div>
                </b-modal>
                <td id="pm">p.m</td>
              </div>
          </tr>
          <tr>
              <td>Tues.</td>
              <td><button id="hourbutton"> {{hours[1].startTime}}</button></td>
              <td id="am">a.m</td>
              <div id="topm">
                <td>To</td>
                <td><button id="hourbutton2">{{hours[1].startTime}} </button></td>
                <td id="pm">p.m</td>
              </div>
          </tr>
          <tr>
            <td>Wed.</td>
            <td><button id="hourbutton">{{hours[2].startTime}}</button></td>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><button id="hourbutton2">{{hours[2].startTime}}</button></td>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Thurs.</td>
            <td><button id="hourbutton">{{hours[3].startTime}}</button></td>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><button id="hourbutton2">{{hours[3].startTime}}</button></td>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Fri.</td>
            <td><button id="hourbutton">{{hours[4].startTime}} </button></td>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><button id="hourbutton2">{{hours[4].startTime}}</button></td>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Sat.</td>
            <td><button id="hourbutton">{{hours[5].startTime}}</button></td>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><button id="hourbutton2">{{hours[5].startTime}}</button></td>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Sun.</td>
            <td><button id="hourbutton">{{hours[6].startTime}}</button></td>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><button id="hourbutton2">{{hours[6].startTime}}</button></td>
              <td id="pm">p.m</td>
            </div>
          </tr>
        </table>
      </div>
    </div>
  </div>


</template>


<script src="./businesshours.js">
import Header from "../components/Header.vue"
export default {
  components: {
    Header
  }
}
</script>

<style>
  #col5{
    color: red;
    margin-left: 20%;
    font-size: xxx-large;
    margin-top: 5%;
  }
  #hourbutton{
    transition-duration: 0.4s;
    font-size: xx-large;
    margin-left: 300px;
    margin-right: 5px;
  }
  #hourbutton:hover{
    color:white;
    background-color: red;
  }
  #hourbutton2{
    margin-left: 20px;
    margin-right: 5px;
    font-size: xx-large;
    transition-duration: 0.4s;
  }
  #hourbutton2:hover{
    color:white;
    background-color: red;
  }
  #am{
    font-size: xx-large;
    margin-right: 100px;
  }
  #pm{
    font-size: xx-large;
  }
  #topm{
    margin-left: 15px;
  }
</style>

