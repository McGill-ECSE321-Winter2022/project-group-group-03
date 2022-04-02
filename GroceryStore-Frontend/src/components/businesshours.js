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
    }
  },
  components: {
    Header
  },
  created: function () {
    //this.createBusinessHours()
    //this.sleep(1000);
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
            this.hours.push(i)
            console.log(i)
          }
          this.updateButtonHours()
        })
    },
    createBusinessHours: function(){
      // AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
      //   .then(response => {
      //     console.log(response.data)
      //   })
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
    setBusinessHours: function(){
      console.log(this.hours[0].hoursID)
      AXIOS.put('/editBusinessHourStartTime/10001?startTime='.concat(this.buttonMessage1))
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
        //console.log(this.hours[0].startTime)
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
      this.setBusinessHours()
    },
    changeButtonHour2: function(hour){
      this.buttonMessage2 = hour
    },
    changeButtonHour3: function(hour){
      this.buttonMessage3 = hour
    },
    changeButtonHour4: function(hour){
      this.buttonMessage4 = hour
    },
    changeButtonHour5: function(hour){
      this.buttonMessage5 = hour
    },
    changeButtonHour6: function(hour){
      this.buttonMessage6 = hour
    },
    changeButtonHour7: function(hour){
      this.buttonMessage7 = hour
    },
    changeButtonHour8: function(hour){
      this.buttonMessage8 = hour
    },
    changeButtonHour9: function(hour){
      this.buttonMessage9 = hour
    },
    changeButtonHour10: function(hour){
      this.buttonMessage10 = hour
    },
    changeButtonHour11: function(hour){
      this.buttonMessage11 = hour
    },
    changeButtonHour12: function(hour){
      this.buttonMessage12 = hour
    },
    changeButtonHour13: function(hour){
      this.buttonMessage13 = hour
    },
    changeButtonHour14: function(hour){
      this.buttonMessage14 = hour
    }
  },
}
