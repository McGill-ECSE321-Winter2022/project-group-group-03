import Header from "./Header";

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
      buttonMessage: "09:00"
    }
  },
  components: {
    Header
  },
  methods:{
    changeOpeningHour: function(hour){
      this.dropDownMessage = hour
    },
    changeButtonHour: function(hour){
      this.buttonMessage = hour
    }
  },
  created: function() {

    //Test data
    const p1 = new BusinessHourDTO('123','09:00','11:00','Monday')
    const p2 = new BusinessHourDTO('132','09:00','11:00','Tuesday')
    const p3 = new BusinessHourDTO('231','09:00','11:00','Wednesday')
    const p4 = new BusinessHourDTO('321','09:00','11:00','Thursday')
    const p5 = new BusinessHourDTO('213','09:00','11:00','Friday')
    const p6 = new BusinessHourDTO('2321','09:00','11:00','Saturday')
    const p7 = new BusinessHourDTO('21131','09:00','11:00','Sunday')
    // Sample initial content
    this.hours = [p1,p2,p3, p4, p5, p6, p7]
  }
}
