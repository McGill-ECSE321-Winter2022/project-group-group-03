import axios from 'axios'
var config = require('../../config')
import Header from "./Cart"
import Footer from "./Footer"

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function WorkShiftDTO(startTime, endTime, shiftID, day) {
  this.starTime = startTime
  this.endTime = endTime
  this.shiftID = shiftID
  this.day = day
}

function EmployeeDTO(username, password, email, address, workingStatus) {
  this.username = username
  this.password = password
  this.email = email
  this.address = address
  this.workingStatus = workingStatus
}

export default {
  name: 'Schedule',
  data() {
    // the data that we are trying to retrieve and show to
    return {
      startTime: '',
      endTime: '',
      shiftID: '',
      day: '',
      shifts: [],
      username: '',
      error: ''
    }
  },// the components that we are importing
  components: {
    Header,
    Footer
  },// shows all the employee' work shifts on the display
  created: function () {
    // Initializing shift from backend
    this.getWorkshift()

    // //Test data
    // const p1 = new WorkShiftDTO('09:00', '11:00', '2', 'Monday')
    // const p2 = new WorkShiftDTO('09:00', '11:00', '22', 'Tuesday')
    // const p3 = new WorkShiftDTO('09:00', '11:00', '3', 'Wednesday')
    // // Sample initial content
    // this.shifts = [p1, p2, p3]
  },
  methods:{
    createWorkshift(){
      // AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
      //   .then(response => {
      //     console.log(response.data)
      //   })
      // AXIOS.post("/employee?username=Ari&email=ariari@mail.com&password=55466&address=69420 ari street",{},{})
      //     .then(response => {
      //       console.log(response.data)
      //     })
      // AXIOS.post('workShift?aStartTime=01:30&aEndTime=08:30&aDay=Tuesday&username=Ari')
      //   .then(response => {
      //     console.log(response.data)
      //   })
      // AXIOS.post('workShift?aStartTime=08:30&aEndTime=11:30&aDay=Friday&username=Ari')
      //   .then(response => {
      //     console.log(response.data)
      //   })

    },// gets all the workshift that is associated to that specific employee
    getWorkshift(){
      let username = sessionStorage.username
      this.username = username
      AXIOS.get('/workshift/employee?username='.concat(this.username))
        .then(response => {
          //JSON responses are automatically parsed
          this.shifts = response.data
        })

    }
  }
}
