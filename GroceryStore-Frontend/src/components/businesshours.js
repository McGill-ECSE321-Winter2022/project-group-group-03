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
      buttonMessage1: "09:00",
      buttonMessage2: "11:00",
      buttonMessage3: "09:00",
      buttonMessage4: "11:00",
      buttonMessage5: "09:00",
      buttonMessage6: "11:00",
      buttonMessage7: "09:00",
      buttonMessage8: "11:00",
      buttonMessage9: "09:00",
      buttonMessage10: "11:00",
      buttonMessage11: "09:00",
      buttonMessage12: "11:00",
      buttonMessage13: "09:00",
      buttonMessage14: "11:00",
    }
  },
  components: {
    Header
  },
  methods:{
    changeOpeningHour: function(hour){
      this.dropDownMessage = hour
    },
    changeButtonHour1: function(hour){
      this.buttonMessage1 = hour
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
