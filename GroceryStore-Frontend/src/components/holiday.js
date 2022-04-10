import Header from "./Cart"

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
  pageName: 'HolidayOwner',
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
      errorHoliday: "",
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
    /**
     * Retrieves all holidays and puts them into a list.
     */
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
      })
        .catch(error => {
          this.errorHoliday = error.response.data;
        })
    },

    /**
     * Retrieves specific holiday with a given name.
     * @param {String} holidayName - name of the holiday we are trying to search
     * @throws {NotFoundError} When the holiday is not found.
     */
    searchHoliday: function(holidayName) {
      this.getHolidays()
      AXIOS.get('/holiday/'.concat(holidayName), {responseType: "json"})
        .then((response) => {
          this.response = response.data;
          this.name = this.response.name
          this.startDateMessage = this.response.startDate
          this.endDateMessage = this.response.endDate
        })
        .catch(error => {
          this.errorHoliday = error.response.data;
        })
    },
    /**
     * Updates startDate and endDate of a specific holiday.
     * @param {String} holidayName
     * @param {Date} newStartDate
     * @param {Date} newEndDate
     * @throws {NotFoundError} When the holiday is not found.
     * @throws {IllegalArgumentException} When the startDate is after the endDate
     */
    updateHoliday: function(holidayName, newStartDate, newEndDate){
      //Check what changed
      if(holidayName.trim().length != 0) {
        AXIOS.put('/edit_holiday_startDate/'.concat(holidayName, '/?startDate=', newStartDate))
          .then((response) => {
            console.log(response)
          })
          .catch(error => {
            this.errorHoliday = error.response.data;
          })
        this.sleep(500)
        AXIOS.put('/edit_holiday_endDate/'.concat(holidayName, '/?endDate=', newEndDate))
          .catch(error => {
            this.errorHoliday = error.response.data;
          })
        this.sleep(500)
        this.getHolidays()
        //Reset fields for new holiday
        this.name = ''
        this.startDateMessage = 'Start Date'
        this.endDateMessage = 'End Date'
      }
      else{
        if(holidayName.trim().length == 0) this.errorHoliday = 'Fill out name field'
        this.name = ''
        this.startDateMessage = 'Start Date'
        this.endDateMessage = 'End Date'
      }
    },
    /**
     * Enables list of holidays to be displayed
     */
    viewAll: function(){
      this.visibleViewAll = true
      this.getHolidays()
    },
    /**
     * Hides list of all holidays
     */
    hideAll: function(){
      this.visibleViewAll = false
    },
    /**
     * Deletes specific holiday with a given name.
     * @param {String} holidayName - name of the holiday we are trying to search
     * @throws {NotFoundError} When the holiday is not found.
     */
    deleteHoliday: function(holidayName){
      this.getHolidays()
      AXIOS.delete('/holiday/'.concat(holidayName))
        .then((response) => {
          console.log(response)
          this.startDateMessage = 'Start Date'
          this.endDateMessage = 'End Date'
          this.name = 'Name'

        })
    },
    /**
     * Pauses the program for a given amount of milliseconds
     * @param {int} milliseconds - milliseconds for which the program will be paused
     */
    sleep: function (milliseconds) {
      const date = Date.now();
      let currentDate = null;
      do {
        currentDate = Date.now();
      }
      while (currentDate - date < milliseconds);
    },

    // createStore: function(){
    //   AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
    //     .then(response => {
    //       console.log(response.data)
    //     })
    //     .catch(error => {
    //       this.errorHoliday = error.response.data;
    //     })
    //   this.sleep(1000)
    // },
    /**
     * Creates a holiday with specific attributes.
     * @param {String} username
     * @param {Date} startDate
     * @param {Date} endDate
     * @throws {IllegalArgumentException} When parameters passed no not respect given constraints.
     */
    createHoliday: function (holidayName, startDate, endDate){
      console.log("creating holidays")
      console.log(holidayName.trim().length)
      console.log(startDate)
      console.log(endDate)
      if(holidayName.trim().length != 0 && startDate !== 'Start Date' && endDate !== 'End Date'){
        console.log("HI")
        console.log('/holiday?'.concat('name=',holidayName, "&startDate=",startDate, "&endDate=",endDate))
        AXIOS.post('/holiday?'.concat('name=',holidayName, "&startDate=",startDate, "&endDate=",endDate))
          .catch(error => {
            this.errorHoliday = error.response.data;
          })
        this.sleep(500)
        this.getHolidays()
        //Reset fields for new holiday
        this.name = ''
        this.startDateMessage = 'Start Date'
        this.endDateMessage = 'End Date'
      }
      else{
        if(holidayName.trim().length == 0) this.errorHoliday = 'Fill out name field'
        else if(startDate === 'Start Date') this.errorHoliday = 'Start date cannot be empty'
        else if(endDate === 'End Date') this.errorHoliday = 'End date cannot be empty'
        this.name = ''
        this.startDateMessage = 'Start Date'
        this.endDateMessage = 'End Date'
      }
    },
    /**
     * Formatting and display of startDate.
     */
    changeStartDate: function(year, month, day){
      let message2Display = year
      message2Display+= '-'
      message2Display+= month
      message2Display+= '-'
      message2Display+= day
      this.startDateMessage = message2Display
    },
    /**
     * Formatting and display of endDate.
     */
    changeEndDate: function(year, month, day){
      let message2Display = year
      message2Display+= '-'
      message2Display+= month
      message2Display+= '-'
      message2Display+= day
      this.endDateMessage = message2Display
    },
    /**
     * Formatting and display of startDate modal button - year dropdown.
     */
    changeDropdownYear1: function(year){
      this.dropDownMessageYear1 = year
    },
    /**
     * Formatting and display of startDate modal button - month dropdown.
     */
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
    /**
     * Formatting and display of startDate modal button - day dropdown.
     */
    changeDropdownDay1: function(day){
      this.dropDownMessageDay1 = day
    },
    /**
     * Formatting and display of endDate modal button - year dropdown.
     */
    changeDropdownYear2: function(year){
      this.dropDownMessageYear2 = year
    },
    /**
     * Formatting and display of endDate modal button - month dropdown.
     */
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
    /**
     * Formatting and display of endDate modal button - day dropdown.
     */
    changeDropdownDay2: function(day){
      this.dropdownMessageDay2 = day
    },
    /**
     * Returns status of error message
     */
    setAlert: function () {
      return this.errorHoliday !== ""
    },
    /**
     * Resets error status by setting the error message to an empty string
     */
    setErrorEmpty(){
      this.errorHoliday = ""
    }

  }
}
