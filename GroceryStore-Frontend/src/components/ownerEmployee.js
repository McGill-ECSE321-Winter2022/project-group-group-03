import Header from "./Header";
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function WorkShiftDTO (startTime,endTime,day, id) {
  this.startTime = startTime
  this.endTime = endTime
  this.day=day
  this.id = id
}

function EmployeeDTO (username, password, email, address,workingStatus) {
  this.username = username
  this.password = password
  this.email = email
  this.address = address
  this.workingStatus = workingStatus
}
export default {
  name: 'Schedule',
  data() {
    return {
      startTime: '',
      endTime: '',
      day: '',
      shifts: [],
      Searchusername:"",
      username: "",
      address:"",
      email:"",
      employees:"",
      id: "",
      response:"",
      EMPdropDownMessage1: "change start shift",
      EMPdropDownMessage2: "change end shift",
      EMPbuttonMessage1: "Add",
      EMPbuttonMessage2: "Add",
      EMPbuttonMessage3: "Add",
      EMPbuttonMessage4: "Add",
      EMPbuttonMessage5: "Add",
      EMPbuttonMessage6: "Add",
      EMPbuttonMessage7: "Add",
      EMPbuttonMessage8: "Add",
      EMPbuttonMessage9: "Add",
      EMPbuttonMessage10: "Add",
      EMPbuttonMessage11: "Add",
      EMPbuttonMessage12: "Add",
      EMPbuttonMessage13: "Add",
      EMPbuttonMessage14: "Add",
      mondayWS:"",
      tuesdayWS:"",
      wednesdayWS:"",
      thursdayWS:"",
      fridayWS:"",
      saturdayWS:"",
      sundayWS:""
    }
  },
  components: {
    Header
  },
  created: function() {
    // AXIOS.post("/employee?username=Ari&email=ariari@mail.com&password=55466&address=69420 ari street",{},{})
    //     .then(response => {
    //       console.log(response.data)
    //       this.username = this.response.username
    //       this.address = this.response.address
    //       this.email = this.response.email
    //
    //     })
    //this.sleep(1000)
    //this.getWorkShiftsOfEmployee()
  },
  methods: {
    searchUsername: function (username) {
      AXIOS.get('/employee?username='.concat(username), {responseType: "json"})
        .then((response) => {
          this.response = response.data;
          console.log(response)
          this.username = this.response.username
          this.address = this.response.address
          this.email = this.response.email
          this.getWorkShiftsOfEmployee()
        })
    },
    createWorkshift1: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage1, '&aEndTime=',this.EMPbuttonMessage2, '&aDay=Monday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
    },
    createWorkshift2: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage3, '&aEndTime=',this.EMPbuttonMessage4, '&aDay=Tuesday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
    },
    createWorkshift3: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage5, '&aEndTime=',this.EMPbuttonMessage6, '&aDay=Wednesday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
    },
    createWorkshift4: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage7, '&aEndTime=',this.EMPbuttonMessage8, '&aDay=Thursday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
    },
    createWorkshift5: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage9, '&aEndTime=',this.EMPbuttonMessage10, '&aDay=Friday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
    },
    createWorkshift6: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage11, '&aEndTime=',this.EMPbuttonMessage12, '&aDay=Saturday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
    },
    createWorkshift7: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage12, '&aEndTime=',this.EMPbuttonMessage14, '&aDay=Sunday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
    },

    EMPchangeOpeningHour1: function (hour) {
      this.EMPdropDownMessage1 = hour
    },
    EMPchangeOpeningHour2: function (hour) {
      this.EMPdropDownMessage2 = hour
    },
    EMPchangeButtonHour1: function (hour) {
      this.EMPbuttonMessage1 = hour
      this.setWorkShiftHours1()
    },
    EMPchangeButtonHour2: function (hour) {
      this.EMPbuttonMessage2 = hour
      this.setWorkShiftHours2()
    },
    EMPchangeButtonHour3: function (hour) {
      this.EMPbuttonMessage3 = hour
      this.setWorkShiftHours3()
    },
    EMPchangeButtonHour4: function (hour) {
      this.EMPbuttonMessage4 = hour
      this.setWorkShiftHours4()
    },
    EMPchangeButtonHour5: function (hour) {
      this.EMPbuttonMessage5 = hour
      this.setWorkShiftHours5()
    },
    EMPchangeButtonHour6: function (hour) {
      this.EMPbuttonMessage6 = hour
      this.setWorkShiftHours6()
    },
    EMPchangeButtonHour7: function (hour) {
      this.EMPbuttonMessage7 = hour
      this.setWorkShiftHours7()
    },
    EMPchangeButtonHour8: function (hour) {
      this.EMPbuttonMessage8 = hour
      this.setWorkShiftHours8()
    },
    EMPchangeButtonHour9: function (hour) {
      this.EMPbuttonMessage9 = hour
      this.setWorkShiftHours9()
    },
    EMPchangeButtonHour10: function (hour) {
      this.EMPbuttonMessage10 = hour
      this.setWorkShiftHours10()
    },
    EMPchangeButtonHour11: function (hour) {
      this.EMPbuttonMessage11 = hour
      this.setWorkShiftHours11()
    },
    EMPchangeButtonHour12: function (hour) {
      this.EMPbuttonMessage12 = hour
      this.setWorkShiftHours12()
    },
    EMPchangeButtonHour13: function (hour) {
      this.EMPbuttonMessage13 = hour
      this.setWorkShiftHours13()
    },
    EMPchangeButtonHour14: function (hour) {
      this.EMPbuttonMessage14 = hour
      this.setWorkShiftHours14()
    },
    getWorkShiftsOfEmployee: function(){
      AXIOS.get('/workshift/employee?username='.concat(this.username), {responseType: "json"})
        .then((response) => {
          this.response = response.data
          for (const shift in this.response) {
            let i = new WorkShiftDTO(this.response[shift].startTime, this.response[shift].endTime, this.response[shift].day, this.response[shift].id)
            if(i.day == "Monday"){
              this.shifts.splice(0, 0, i)
              this.mondayWS = i.id
            }
            if(i.day == "Tuesday") {
              this.shifts.splice(1, 0, i)
              this.tuesdayWS = i.id
            }
            if(i.day == "Wednesday"){
              this.shifts.splice(2,0, i)
              this.wednesdayWS = i.id
            }
            if(i.day == "Thursday"){
              this.shifts.splice(3,0, i)
              this.thursdayWS = i.id
            }
            if(i.day == "Friday"){
              this.shifts.splice(4,0, i)
              this.fridayWS = i.id
            }
            if(i.day == "Saturday"){
              this.shifts.splice(5,0, i)
              this.saturdayWS = i.id
            }
            if(i.day == "Sunday"){
              this.shifts.splice(6,0, i)
              this.sundayWS = i.id
            }
          }
          this.updateButtonShifts()
        })
    },

    setWorkShiftHours1: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_startTime/'.concat(this.mondayWS, '?startTime=',  this.EMPbuttonMessage1))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours2: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_endTime/'.concat(this.mondayWS,'?endTime=', this.EMPbuttonMessage2))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours3: function() {

      AXIOS.put('/edit_workShift_startTime/'.concat(this.tuesdayWS,'?startTime=', this.EMPbuttonMessage3))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours4: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_endTime/'.concat(this.tuesdayWS,'?endTime=', this.EMPbuttonMessage4))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours5: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_startTime/'.concat(this.wednesdayWS,'?startTime=', this.EMPbuttonMessage5))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours6: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_endTime/'.concat(this.wednesdayWS,'?endTime=', this.EMPbuttonMessage6))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours7: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_startTime/'.concat(this.thursdayWS,'?startTime=', this.EMPbuttonMessage7))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours8: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_endTime/'.concat(this.thursdayWS,'?endTime=', this.EMPbuttonMessage8))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours9: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_startTime/'.concat(this.fridayWS,'?startTime=', this.EMPbuttonMessage9))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours10: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_endTime/'.concat(this.fridayWS,'?endTime=', this.EMPbuttonMessage10))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours11: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_startTime/'.concat(this.saturdayWS,'?startTime=', this.EMPbuttonMessage11))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours12: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_endTime/'.concat(this.saturdayWS,'?endTime=', this.EMPbuttonMessage12))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours13: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_startTime/'.concat(this.sundayWS,'?startTime=', this.EMPbuttonMessage13))
        .then((response) => {
          console.log(response)
        })
    },
    setWorkShiftHours14: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_endTime/'.concat(this.sundayWS,'?endTime=', this.EMPbuttonMessage14))
        .then((response) => {
          console.log(response)
        })
    },
    parseHour: function(Time){
      let x = ""
      for(let i = 0; i < 5; i++){
        x += (Time)[i]
      }
      return x
    },
    updateButtonShifts: function(){

      this.EMPbuttonMessage1 = this.parseHour(this.shifts[0].startTime)
      this.EMPbuttonMessage2 = this.parseHour(this.shifts[0].endTime)
      this.EMPbuttonMessage3 = this.parseHour(this.shifts[1].startTime)
      this.EMPbuttonMessage4 = this.parseHour(this.shifts[1].endTime)
      this.EMPbuttonMessage5 = this.parseHour(this.shifts[2].startTime)
      this.EMPbuttonMessage6 = this.parseHour(this.shifts[2].endTime)
      this.EMPbuttonMessage7 = this.parseHour(this.shifts[3].startTime)
      this.EMPbuttonMessage8 = this.parseHour(this.shifts[3].endTime)
      this.EMPbuttonMessage9 = this.parseHour(this.shifts[4].startTime)
      this.EMPbuttonMessage10 = this.parseHour(this.shifts[4].endTime)
      this.EMPbuttonMessage11 = this.parseHour(this.shifts[5].startTime)
      this.EMPbuttonMessage12 = this.parseHour(this.shifts[5].endTime)
      this.EMPbuttonMessage13 = this.parseHour(this.shifts[6].startTime)
      this.EMPbuttonMessage14 = this.parseHour(this.shifts[6].endTime)
    },
  }
}
