import Header from "./Header";
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
  name: 'Hours',
  data () {
    return {
      hoursID: '',
      startTime: '',
      endTime: '',
      day: '',
      hours: [],
      dropDownMessage: "Change opening hour",
      buttonMessage1: "",
      buttonMessage2: "",
      buttonMessage3: "",
      buttonMessage4: "",
      buttonMessage5: "",
      buttonMessage6: "",
      buttonMessage7: "",
      buttonMessage8: "",
      buttonMessage9: "",
      buttonMessage10: "",
      buttonMessage11: "",
      buttonMessage12: "",
      buttonMessage13: "",
      buttonMessage14: "",
      monday:"",
      tuesday:"",
      wednesday:"",
      thursday:"",
      friday:"",
      saturday:"",
      sunday:""
    }
  },
  components: {
    Header
  },
  created: function () {
    //this.createBusinessHours()
    //this.sleep(1000)
    this.getBusinessHours()
  },
  methods:{
    getBusinessHours: function(){
      console.log("getting business hours")
      this.hours.length = 0
      AXIOS.get('/businessHour', {responseType: "json"})
        .then((response) => {
          this.response = response.data

          for (const hour in this.response){
            let i = new BusinessHourDTO(this.response[hour].hoursID, this.response[hour].startTime, this.response[hour].endTime, this.response[hour].day)
            if(i.day == "Monday"){
              this.hours.splice(0, 0, i)
              this.monday = i.hoursID
            }
            if(i.day == "Tuesday") {
              this.hours.splice(1, 0, i)
              this.tuesday = i.hoursID
            }
            if(i.day == "Wednesday"){
              this.hours.splice(2,0, i)
              this.wednesday = i.hoursID
            }
            if(i.day == "Thursday"){
              this.hours.splice(3,0, i)
              this.thursday = i.hoursID
            }
            if(i.day == "Friday"){
              this.hours.splice(4,0, i)
              this.friday = i.hoursID
            }
            if(i.day == "Saturday"){
              this.hours.splice(5,0, i)
              this.saturday = i.hoursID
            }
            if(i.day == "Sunday"){
              this.hours.splice(6,0, i)
              this.sunday = i.hoursID
            }
          }
          console.log(this.hours)
          this.updateButtonHours()
        })
    },

    createBusinessHours: function(){
      // AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
      //   .then(response => {
      //     console.log(response.data)
      //   })
      this.sleep(1000);
      console.log("creating business hours")
      AXIOS.post('businessHour?startTime=02:35&endTime=04:24&day=Sunday')
        .then((response) => {
          console.log(response)
        })
      AXIOS.post('businessHour?startTime=02:35&endTime=04:24&day=Monday')
        .then((response) => {
          console.log(response)
        })
      AXIOS.post('businessHour?startTime=02:35&endTime=04:24&day=Tuesday')
        .then((response) => {
          console.log(response)
        })
      AXIOS.post('businessHour?startTime=02:35&endTime=04:24&day=Wednesday')
        .then((response) => {
          console.log(response)
        })
      AXIOS.post('businessHour?startTime=02:35&endTime=04:24&day=Thursday')
        .then((response) => {
          console.log(response)
        })
      AXIOS.post('businessHour?startTime=02:35&endTime=04:24&day=Friday')
        .then((response) => {
          console.log(response)
        })
      AXIOS.post('businessHour?startTime=02:35&endTime=04:24&day=Saturday')
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours1: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourStartTime/'.concat(this.monday,'?startTime=', this.buttonMessage1))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours2: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourEndTime/'.concat(this.monday,'?endTime=', this.buttonMessage2))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours3: function() {

      AXIOS.put('/editBusinessHourStartTime/'.concat(this.tuesday,'?startTime=', this.buttonMessage3))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours4: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourEndTime/'.concat(this.tuesday,'?endTime=', this.buttonMessage4))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours5: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourStartTime/'.concat(this.wednesday,'?startTime=', this.buttonMessage5))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours6: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourEndTime/'.concat(this.wednesday,'?endTime=', this.buttonMessage6))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours7: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourStartTime/'.concat(this.thursday,'?startTime=', this.buttonMessage7))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours8: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourEndTime/'.concat(this.thursday,'?endTime=', this.buttonMessage8))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours9: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourStartTime/'.concat(this.friday,'?startTime=', this.buttonMessage9))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours10: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourEndTime/'.concat(this.friday,'?endTime=', this.buttonMessage10))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours11: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourStartTime/'.concat(this.saturday,'?startTime=', this.buttonMessage11))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours12: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourEndTime/'.concat(this.saturday,'?endTime=', this.buttonMessage12))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours13: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourStartTime/'.concat(this.sunday,'?startTime=', this.buttonMessage13))
        .then((response) => {
          console.log(response)
        })
    },
    setBusinessHours14: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourEndTime/'.concat(this.sunday,'?endTime=', this.buttonMessage14))
        .then((response) => {
          console.log(response)
        })
    },
    sleep: function (milliseconds) {
      const date = Date.now();
      let currentDate = null;
      do {
        currentDate = Date.now();
      }
      while (currentDate - date < milliseconds);
    },
    updateButtonHours: function(){
      console.log(this.hours)
      this.buttonMessage1 = this.parseHour(this.hours[0].startTime)
      this.buttonMessage2 = this.parseHour(this.hours[0].endTime)
      this.buttonMessage3 = this.parseHour(this.hours[1].startTime)
      this.buttonMessage4 = this.parseHour(this.hours[1].endTime)
      this.buttonMessage5 = this.parseHour(this.hours[2].startTime)
      this.buttonMessage6 = this.parseHour(this.hours[2].endTime)
      this.buttonMessage7 = this.parseHour(this.hours[3].startTime)
      this.buttonMessage8 = this.parseHour(this.hours[3].endTime)
      this.buttonMessage9 = this.parseHour(this.hours[4].startTime)
      this.buttonMessage10 = this.parseHour(this.hours[4].endTime)
      this.buttonMessage11 = this.parseHour(this.hours[5].startTime)
      this.buttonMessage12 = this.parseHour(this.hours[5].endTime)
      this.buttonMessage13 = this.parseHour(this.hours[6].startTime)
      this.buttonMessage14 = this.parseHour(this.hours[6].endTime)
    },
    parseHour: function(Time){
      let x = ""
      for(let i = 0; i < 5; i++){
        x += (Time)[i]
      }
      return x
    },
    changeOpeningHour: function(hour){
      this.dropDownMessage = hour
    },
    changeButtonHour1: function(hour){
      this.buttonMessage1 = hour
      this.setBusinessHours1()
    },
    changeButtonHour2: function(hour){
      this.buttonMessage2 = hour
      this.setBusinessHours2()
    },
    changeButtonHour3: function(hour){
      this.buttonMessage3 = hour
      this.setBusinessHours3()
    },
    changeButtonHour4: function(hour){
      this.buttonMessage4 = hour
      this.setBusinessHours4()
    },
    changeButtonHour5: function(hour){
      this.buttonMessage5 = hour
      this.setBusinessHours5()
    },
    changeButtonHour6: function(hour){
      this.buttonMessage6 = hour
      this.setBusinessHours6()
    },
    changeButtonHour7: function(hour){
      this.buttonMessage7 = hour
      this.setBusinessHours7()
    },
    changeButtonHour8: function(hour){
      this.buttonMessage8 = hour
      this.setBusinessHours8()
    },
    changeButtonHour9: function(hour){
      this.buttonMessage9 = hour
      this.setBusinessHours9()
    },
    changeButtonHour10: function(hour){
      this.buttonMessage10 = hour
      this.setBusinessHours10()
    },
    changeButtonHour11: function(hour){
      this.buttonMessage11 = hour
      this.setBusinessHours11()
    },
    changeButtonHour12: function(hour){
      this.buttonMessage12 = hour
      this.setBusinessHours12()
    },
    changeButtonHour13: function(hour){
      this.buttonMessage13 = hour
      this.setBusinessHours13()
    },
    changeButtonHour14: function(hour){
      this.buttonMessage14 = hour
      this.setBusinessHours14()
    }
  },
}
