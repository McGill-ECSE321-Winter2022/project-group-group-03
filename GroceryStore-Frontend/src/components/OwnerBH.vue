<template>
  <div>
    <Header />
    <div class="row">
      <div id="col5" class="col-6">
        <table id="table">
          <tr>
              <td>Mon.</td>
              <td><b-button id="hourbutton" v-b-modal.modal-1>{{hours[0].startTime}}</b-button></td>
              <b-modal id="modal-1" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" text="Change opening hour" variant="primary" class="m-2">
                    <b-dropdown-item href="#">01:00</b-dropdown-item>
                    <b-dropdown-item href="#">02:00</b-dropdown-item>
                    <b-dropdown-item href="#">03:00</b-dropdown-item>
                    <b-dropdown-item href="#">04:00</b-dropdown-item>
                    <b-dropdown-item href="#">05:00</b-dropdown-item>
                    <b-dropdown-item href="#">06:00</b-dropdown-item>
                    <b-dropdown-item href="#">07:00</b-dropdown-item>
                    <b-dropdown-item href="#">08:00</b-dropdown-item>
                    <b-dropdown-item href="#">09:00</b-dropdown-item>
                    <b-dropdown-item href="#">10:00</b-dropdown-item>
                    <b-dropdown-item href="#">11:00</b-dropdown-item>
                    <b-dropdown-item href="#">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="am">a.m</td>
              <div id="topm">
                <td>To</td>
                <td><b-button id="hourbutton2" v-b-modal.modal-2>{{hours[0].startTime}}</b-button></td>
                <b-modal id="modal-2" title="BootstrapVue">
                <p class="my-4">Hello from !</p>
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
    margin-left: 330px;
    font-size: xxx-large;
    margin-top: 60px;
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

