import Header from "./Header";

function WorkShiftDTO (startTime,endTime,day) {
  this.startTime = startTime
  this.endTime = endTime
  this.day=day
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
  data () {
    return {
      startTime: '',
      endTime: '',
      day: '',
      shifts: []
    }
  },
  components: {
    Header
  },
  created: function() {
    //Initializing shift from backend
    // AXIOS.get('/shifts')
    //   .then(response => {
    //     //JSON responses are automatically parsed
    //     this.shifts = response.data
    //   })
    //   .catch(e => {
    //     this.errorShift = e
    //   })
    //Test data
    const p1 = new WorkShiftDTO('09:00','11:00','Monday')
    const p2 = new WorkShiftDTO('09:00','11:00','Tuesday')
    const p3 = new WorkShiftDTO('09:00','11:00','Wednesday')
    // Sample initial content
    this.shifts = [p1,p2,p3]
  }
}
