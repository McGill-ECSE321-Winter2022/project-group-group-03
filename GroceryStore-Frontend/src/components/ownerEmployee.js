import Header from "./Cart"
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function WorkShiftDTO (startTime,endTime,day, shiftID) {
  this.startTime = startTime
  this.endTime = endTime
  this.day=day
  this.shiftID = shiftID
}

function EmployeeDTO (username, password, email, address,workingStatus) {
  this.username = username
  this.password = password
  this.email = email
  this.address = address
  this.workingStatus = workingStatus
}
export default {
  name: 'OwnerEmployee',
  data() {
    return {
      create_error:'',
      startTime: '',
      endTime: '',
      day: '',
      shifts: [null, null, null, null, null, null, null],
      Searchusername:"",
      username: "",
      address:"",
      email:"",
      employees:"",
      shiftID: "",
      response:"",
      mondaydisable:false,
      tuesdaydisable:false,
      wednesdaydisable:false,
      thursdaydisable:false,
      fridaydisable:false,
      saturdaydisable:false,
      sundaydisable:false,
      mondaydisable1:false,
      tuesdaydisable1:false,
      wednesdaydisable1:false,
      thursdaydisable1:false,
      fridaydisable1:false,
      saturdaydisable1:false,
      sundaydisable1:false,
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

    // AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
    //   .then(response => {
    //     console.log(response.data)
    //   })

    //this.sleep(1000)
    //this.getWorkShiftsOfEmployee()
  },
  methods: {
    searchUsername: function (username) {
      AXIOS.get('/employee?username='.concat(username), {responseType: "json"})
        .then((response) => {
          this.response = response.data
          console.log(response)
          this.username = this.response.username
          this.address = this.response.address
          this.email = this.response.email
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    sleep: function (milliseconds) {
      const date = Date.now();
      let currentDate = null;
      do {
        currentDate = Date.now();
      }
      while (currentDate - date < milliseconds);
    },
    fireEmployee: function(){
      AXIOS.put('/fire_employee?username='.concat(this.username),{responseType: "json"})
        .then((response) => {
          this.response = response.data

        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    hireEmployee: function(){
      AXIOS.put('/hire_employee?username='.concat(this.username),{responseType: "json"})
        .then((response) => {
          this.response = response.data

        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    deleteWorkshift1: function(){
      AXIOS.delete('/workShift/'.concat(this.mondayWS),{responseType: "json"})
        .then((response) => {
          console.log(this.mondayWS)
          this.response = response.data
        })
        this.EMPbuttonMessage1 = "Add"
        this.EMPbuttonMessage2 = "Add"
        this.mondaydisable1 = true
        this.mondaydisable = false
    },
    deleteWorkshift2: function(){
      AXIOS.delete('/workShift/'.concat(this.tuesdayWS),{responseType: "json"})
        .then((response) => {
          this.response = response.data

        })
        this.EMPbuttonMessage3 = "Add"
        this.EMPbuttonMessage4 = "Add"
        this.tuesdaydisable1 = true
        this.tuesdaydisable = false
    },
    deleteWorkshift3: function(){
      AXIOS.delete('/workShift/'.concat(this.wednesdayWS),{responseType: "json"})
        .then((response) => {
          console.log(this.wednesdayWS)
          this.response = response.data
        })
      this.EMPbuttonMessage5 = "Add"
      this.EMPbuttonMessage6 = "Add"
      this.wednesdaydisable1 = true
      this.wednesdaydisable = false

    },
    deleteWorkshift4: function(){
      AXIOS.delete('/workShift/'.concat(this.thursdayWS),{responseType: "json"})
        .then((response) => {
          this.response = response.data
        })
      this.EMPbuttonMessage7 = "Add"
      this.EMPbuttonMessage8 = "Add"
      this.thursdaydisable1 = true
      this.thursdaydisable = false
    },
    deleteWorkshift5: function(){
      AXIOS.delete('/workShift/'.concat(this.fridayWS),{responseType: "json"})
        .then((response) => {
          this.response = response.data

        })
      this.EMPbuttonMessage9 = "Add"
      this.EMPbuttonMessage10 = "Add"
      this.fridaydisable1 = true
      this.fridaydisable = false

    },
    deleteWorkshift6: function(){
      AXIOS.delete('/workShift/'.concat(this.saturdayWS),{responseType: "json"})
        .then((response) => {
          this.response = response.data

        })
      this.EMPbuttonMessage11 = "Add"
      this.EMPbuttonMessage12 = "Add"
      this.saturdaydisable1 = true
      this.saturdaydisable = false

    },
    deleteWorkshift7: function(){
      AXIOS.delete('/workShift/'.concat(this.sundayWS),{responseType: "json"})
        .then((response) => {
          this.response = response.data

        })

      this.EMPbuttonMessage13 = "Add"
      this.EMPbuttonMessage14 = "Add"
      this.sundaydisable1 = true
      this.sundaydisable = false

    },
    createWorkshift1: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage1, '&aEndTime=',this.EMPbuttonMessage2, '&aDay=Monday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    createWorkshift2: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage3, '&aEndTime=',this.EMPbuttonMessage4, '&aDay=Tuesday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    createWorkshift3: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage5, '&aEndTime=',this.EMPbuttonMessage6, '&aDay=Wednesday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    createWorkshift4: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage7, '&aEndTime=',this.EMPbuttonMessage8, '&aDay=Thursday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    createWorkshift5: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage9, '&aEndTime=',this.EMPbuttonMessage10, '&aDay=Friday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    createWorkshift6: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage11, '&aEndTime=',this.EMPbuttonMessage12, '&aDay=Saturday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    createWorkshift7: function (username){
      AXIOS.post('workShift?aStartTime='.concat(this.EMPbuttonMessage13, '&aEndTime=',this.EMPbuttonMessage14, '&aDay=Sunday&username=',username))
        .then(response => {
          console.log(response.data)
          this.getWorkShiftsOfEmployee()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },

    EMPchangeOpeningHour1: function (hour) {
      this.EMPdropDownMessage1 = hour
    },
    EMPchangeOpeningHour2: function (hour) {
      this.EMPdropDownMessage2 = hour
    },
    EMPchangeButtonHour1: function (hour1, hour2) {

      this.EMPbuttonMessage1 = hour1
      this.EMPbuttonMessage2 = hour2
      this.setWorkShiftHours1()
    },
    EMPchangeButtonHour2: function (hour1, hour2) {

      this.EMPbuttonMessage3 = hour1
      this.EMPbuttonMessage4 = hour2
      this.setWorkShiftHours2()
    },
    EMPchangeButtonHour3: function (hour1, hour2) {

      this.EMPbuttonMessage5 = hour1
      this.EMPbuttonMessage6 = hour2
      this.setWorkShiftHours3()
    },
    EMPchangeButtonHour4: function (hour1, hour2) {

      this.EMPbuttonMessage7 = hour1
      this.EMPbuttonMessage8 = hour2
      this.setWorkShiftHours4()
    },
    EMPchangeButtonHour5: function (hour1, hour2) {

      this.EMPbuttonMessage9 = hour1
      this.EMPbuttonMessage10 = hour2
      this.setWorkShiftHours5()
    },
    EMPchangeButtonHour6: function (hour1, hour2) {

      this.EMPbuttonMessage11 = hour1
      this.EMPbuttonMessage12 = hour2
      this.setWorkShiftHours6()
    },
    EMPchangeButtonHour7: function (hour1, hour2) {

      this.EMPbuttonMessage13 = hour1
      this.EMPbuttonMessage14 = hour2
      this.setWorkShiftHours7()
    },
    getWorkShiftsOfEmployee: function(){
      AXIOS.get('/workshift/employee?username='.concat(this.username), {responseType: "json"})
        .then((response) => {
          this.response = response.data
          console.log(this.shifts)
          for (const shift in this.response) {
            let i = new WorkShiftDTO(this.response[shift].startTime, this.response[shift].endTime, this.response[shift].day, this.response[shift].shiftID)
            if(i.day === "Monday"){
              this.shifts.splice(0, 1, i)
              this.mondayWS = i.shiftID
            }
            if(i.day === "Tuesday") {
              this.shifts.splice(1, 1, i)
              this.tuesdayWS = i.shiftID
            }
            if(i.day === "Wednesday"){
              this.shifts.splice(2,1, i)
              this.wednesdayWS = i.shiftID
            }
            if(i.day === "Thursday"){
              this.shifts.splice(3,1, i)
              this.thursdayWS = i.shiftID
            }
            if(i.day === "Friday"){
              this.shifts.splice(4,1, i)
              this.fridayWS = i.shiftID
            }
            if(i.day === "Saturday"){
              this.shifts.splice(5,1, i)
              this.saturdayWS = i.shiftID
            }
            if(i.day === "Sunday"){
              this.shifts.splice(6,1, i)
              this.sundayWS = i.shiftID
            }
          }
          console.log(this.shifts)
          this.updateButtonShifts()
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },

    setWorkShiftHours1: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_hours/'.concat(this.mondayWS, '?startTime=',  this.EMPbuttonMessage1, '&endTime=', this.EMPbuttonMessage2))
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    setWorkShiftHours2: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_hours/'.concat(this.tuesdayWS, '?startTime=',  this.EMPbuttonMessage3, '&endTime=', this.EMPbuttonMessage4))
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    setWorkShiftHours3: function() {

      AXIOS.put('/edit_workShift_hours/'.concat(this.wednesdayWS, '?startTime=',  this.EMPbuttonMessage5, '&endTime=', this.EMPbuttonMessage6))
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    setWorkShiftHours4: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_hours/'.concat(this.thursdayWS, '?startTime=',  this.EMPbuttonMessage7, '&endTime=', this.EMPbuttonMessage8))
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    setWorkShiftHours5: function() {
      //console.log(this.hours[0].hoursID)
      console.log(this.EMPbuttonMessage5)
      AXIOS.put('/edit_workShift_hours/'.concat(this.fridayWS, '?startTime=',  this.EMPbuttonMessage9, '&endTime=', this.EMPbuttonMessage10))
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    setWorkShiftHours6: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_hours/'.concat(this.saturdayWS, '?startTime=',  this.EMPbuttonMessage11, '&endTime=', this.EMPbuttonMessage12))
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    setWorkShiftHours7: function() {
      //console.log(this.hours[0].hoursID)
      AXIOS.put('/edit_workShift_hours/'.concat(this.sundayWS, '?startTime=',  this.EMPbuttonMessage13, '&endTime=', this.EMPbuttonMessage14))
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {this.create_error = error.response.data/* <-- this */ });
    },
    parseHour: function(Time){
      let x = ""
      for(let i = 0; i < 5; i++){
        x += (Time)[i]
      }
      return x
    },
    updateButtonShifts: function(){
      if(this.shifts[0] !== null){
        this.EMPbuttonMessage1 = this.parseHour(this.shifts[0].startTime)
        this.EMPbuttonMessage2 = this.parseHour(this.shifts[0].endTime)
        this.mondaydisable = true
        this.mondaydisable1 = false
      }
      if(this.shifts[1] !== null){
        this.EMPbuttonMessage3 = this.parseHour(this.shifts[1].startTime)
        this.EMPbuttonMessage4 = this.parseHour(this.shifts[1].endTime)
        this.tuesdaydisable = true
        this.tuesdaydisable1 = false
      }
      if(this.shifts[2] !== null){
        this.EMPbuttonMessage5 = this.parseHour(this.shifts[2].startTime)
        this.EMPbuttonMessage6 = this.parseHour(this.shifts[2].endTime)
        this.wednesdaydisable = true
        this.wednesdaydisable1 = false
      }
      if(this.shifts[3] !== null){
        this.EMPbuttonMessage7 = this.parseHour(this.shifts[3].startTime)
        this.EMPbuttonMessage8 = this.parseHour(this.shifts[3].endTime)
        this.thursdaydisable = true
        this.thursdaydisable1 = false
      }
      if(this.shifts[4] !== null){
        this.EMPbuttonMessage9 = this.parseHour(this.shifts[4].startTime)
        this.EMPbuttonMessage10 = this.parseHour(this.shifts[4].endTime)
        this.fridaydisable = true
        this.fridaydisable1 = false
      }
      if(this.shifts[5] !== null){
        this.EMPbuttonMessage11 = this.parseHour(this.shifts[5].startTime)
        this.EMPbuttonMessage12 = this.parseHour(this.shifts[5].endTime)
        this.saturdaydisable = true
        this.saturdaydisable1 = false
      }
      if(this.shifts[6] !== null){
        this.EMPbuttonMessage13 = this.parseHour(this.shifts[6].startTime)
        this.EMPbuttonMessage14 = this.parseHour(this.shifts[6].endTime)
        this.sundaydisable = true
        this.sundaydisable1 = false
      }
    },
    setAlert: function () {
      return this.create_error !== ""
    },
    setErrorEmpty: function() {
      this.create_error = ""
    }
  }
}
