<template>


  <div class="d-flex flex-column min-vh-100">
    <nav>
    </nav>
    <main class="flex-fill">
    </main>
    <footer id="footer">
      <b-container class="bv-example-row">
        <b-row>
          <!--   Displaying the business hour start and end times for Monday - Sunday on seperate columns  -->
          <b-col class="bh">Monday
            <br> {{hours[0].startTime}}AM - {{hours[0].endTime}}PM
          </b-col>
          <b-col  class="bh">Tuesday
            <br> {{hours[1].startTime}}AM - {{hours[1].endTime}}PM
          </b-col>
          <b-col class="bh">Wednesday
            <br> {{hours[2].startTime}}AM - {{hours[2].endTime}}PM
          </b-col>
          <b-col class="bh">Thursday
            <br> {{hours[3].startTime}}AM - {{hours[3].endTime}}PM </b-col>
          <b-col class="bh">Friday
            <br> {{hours[4].startTime}}AM - {{hours[4].endTime}}PM</b-col>
          <b-col class="bh">Saturday
            <br> {{hours[5].startTime}}AM - {{hours[5].endTime}}PM</b-col>
          <b-col class="bh">Sunday
            <br> {{hours[6].startTime}}AM - {{hours[6].endTime}}PM</b-col>

        </b-row>
      </b-container>
    </footer>
  </div>

</template>


<script>
import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
function BusinessHourDTO(hoursID, startTime, endTime, day){
  this.hoursID = hoursID;
  this.startTime = startTime;
  this.endTime = endTime;
  this.day = day;
}
export default {
  name: 'Footer',
  data () {
    return {
      hoursID: '',
      startTime: '',
      endTime: '',
      day: '',
      hours: []
    }
  },
  created: function() {
    this.hours.length = 0
    AXIOS.get('/businessHour', {responseType: "json"})
      .then((response) => {
        this.response = response.data
        for (const hour in this.response){
          let i = new BusinessHourDTO(this.response[hour].hoursID, this.parseHour(this.response[hour].startTime), this.parseHour(this.response[hour].endTime), this.response[hour].day)
          if(i.day === "Monday"){
            this.hours.splice(0, 0, i)
          }
          if(i.day === "Tuesday") {
            this.hours.splice(1, 0, i)
          }
          if(i.day === "Wednesday"){
            this.hours.splice(2,0, i)
          }
          if(i.day === "Thursday"){
            this.hours.splice(3,0, i)
          }
          if(i.day === "Friday"){
            this.hours.splice(4,0, i)
          }
          if(i.day === "Saturday"){
            this.hours.splice(5,0, i)
          }
          if(i.day === "Sunday"){
            this.hours.splice(6,0, i)
          }
        }
      })
  },
  methods:{
    parseHour: function(Time){
      let x = ""
      for(let i = 0; i < 5; i++){
        x += (Time)[i]
      }
      return x
    }
  }
}
</script>

<style>
#footer{
  background-color: #e03444;
}
.bh{
  color: white;
  max-height: 5vw;
  overflow: visible;
  white-space: nowrap;
  margin-right: -1%;
}
</style>
