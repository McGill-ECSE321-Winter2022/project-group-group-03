import Header from "./Header";

import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function HolidayDTO(name, startDate, endDate){
  this.name = name;
  this.startDate = startDate;
  this.endDate = endDate;
}

export default{
  name: 'Name',
  data (){
    return{
      name: '',
      startDate: '',
      endDate: '',
      holidays: [],
      years: [],
      months: [],
      days: [],
      searchName: '',
      visibleViewAll: false,

      dropDownMessageYear1: "Year",
      dropDownMessageMonth1: "Month",
      dropDownMessageDay1: "Day",
      dropDownMessageYear2: "Year",
      dropDownMessageMonth2: "Month",
      dropdownMessageDay2: "Day",
      startDateMessage: "Start Date",

      yearMessage1: "",
      yearMessage2: "",
      monthMessage1: "",
      monthMessage2: "",
      dayMessage1: "",
      dayMessage2: "",

      endDateMessage: "End Date",
      errorHoliday: '',
      response: []
    }
  },
  components:{
    Header
  },

  created: function (){
    //this.createStore()
    // this.createHoliday()
     this.getHolidays()

  },

  methods:{

    getHolidays: function(){
      console.log("getting holidays")
      this.holidays.length = 0
      AXIOS.get('/holiday', {responseType: "json"})
        .then((response) =>{
          this.response = response.data;
        for(const holiday in this.response){
          let h = new HolidayDTO(this.response[holiday].name, this.response[holiday].startDate, this.response[holiday].endDate)
          this.holidays.push(h)
        }
          console.log(this.holidays)
      });
    },

    searchHoliday: function(holidayName) {
      this.getHolidays()
      AXIOS.get('/holiday/'.concat(holidayName), {responseType: "json"})
        .then((response) => {
          this.response = response.data;
          this.name = this.response.name
          this.startDateMessage = this.response.startDate
          this.endDateMessage = this.response.endDate
        });
    },

    updateHoliday: function(holidayName, newStartDate, newEndDate){
      //Check what changed
        AXIOS.put('/edit_holiday_startDate/'.concat(holidayName,'/?startDate=',newStartDate))
          .then((response) => {
            console.log(response)
          })
        this.sleep(500)
        AXIOS.put('/edit_holiday_endDate/'.concat(holidayName,'/?endDate=',newEndDate))
          .then((response) => {
            console.log(response)
          })
        this.sleep(500)
      this.startDateMessage = newStartDate
      this.endDateMessage = newEndDate
      this.sleep(1000)
      this.getHolidays()
      this.sleep(500)

    },

    viewAll: function(){
      this.visibleViewAll = true
      this.getHolidays()
    },

    hideAll: function(){
      this.visibleViewAll = false
    },

    deleteHoliday: function(holidayName){
      this.getHolidays()
      AXIOS.delete('/holiday/'.concat(holidayName))
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

    createStore: function(){
      AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
        .then(response => {
          console.log(response.data)
        })
      this.sleep(1000)
    },

    createHoliday: function (holidayName, startDate, endDate){
      console.log("creating holidays")
      console.log('/holiday?'.concat('name=',holidayName, "&startDate=",startDate, "&endDate=",endDate))
      AXIOS.post('/holiday?'.concat('name=',holidayName, "&startDate=",startDate, "&endDate=",endDate))
        .catch(function (error){
          this.errorHoliday = error.data
        })
      this.sleep(500)
      this.getHolidays()
      //Reset fields for new holiday
      this.name = ''
      this.startDateMessage = ''
      this.endDateMessage = ''
    },

    changeStartDate: function(year, month, day){
      let message2Display = year
      message2Display+= '-'
      message2Display+= month
      message2Display+= '-'
      message2Display+= day
      this.startDateMessage = message2Display
    },

    changeEndDate: function(year, month, day){
      let message2Display = year
      message2Display+= '-'
      message2Display+= month
      message2Display+= '-'
      message2Display+= day
      this.endDateMessage = message2Display
    },
    changeDropdownYear1: function(year){
      this.dropDownMessageYear1 = year
    },
    changeDropdownMonth1: function(month){
      switch(month){
        case 'January':
          this.monthMessage1 = '01'
          break
        case 'February':
          this.monthMessage1 = '02'
          break
        case 'March':
          this.monthMessage1 = '03'
          break
        case 'April':
          this.monthMessage1 = '04'
          break
        case 'May':
          this.monthMessage1 = '05'
          break
        case 'June':
          this.monthMessage1 = '06'
          break
        case 'July':
          this.monthMessage1 = '07'
          break
        case 'August':
          this.monthMessage1 = '08'
          break
        case 'September':
          this.monthMessage1 = '09'
          break
        case 'October':
          this.monthMessage1 = '10'
          break
        case 'November':
          this.monthMessage1 = '11'
          break
        default:
          this.monthMessage1 = '12'
      }
      this.dropDownMessageMonth1 = month
    },
    changeDropdownDay1: function(day){
      this.dropDownMessageDay1 = day
    },
    changeDropdownYear2: function(year){
      this.dropDownMessageYear2 = year
    },
    changeDropdownMonth2: function(month){
      switch(month){
        case 'January':
          this.monthMessage2 = '01'
          break
        case 'February':
          this.monthMessage2 = '02'
          break
        case 'March':
          this.monthMessage2 = '03'
          break
        case 'April':
          this.monthMessage2 = '04'
          break
        case 'May':
          this.monthMessage2 = '05'
          break
        case 'June':
          this.monthMessage2 = '06'
          break
        case 'July':
          this.monthMessage2 = '07'
          break
        case 'August':
          this.monthMessage2 = '08'
          break
        case 'September':
          this.monthMessage2 = '09'
          break
        case 'October':
          this.monthMessage2 = '10'
          break
        case 'November':
          this.monthMessage2 = '11'
          break
        default:
          this.monthMessage2 = '12'
      }
      this.dropDownMessageMonth2 = month
    },

    changeDropdownDay2: function(day){
      this.dropdownMessageDay2 = day
    },
  },
}
