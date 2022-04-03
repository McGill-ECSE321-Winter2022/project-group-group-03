<template>
  <div>
    <Header />
    <div id ="HolidaySearchTextButton" class="col-7">
      <div id="searchRow" class="row-12">
        <div class="row g-3">
          <div class="col">
            <input type="text" class="form-control" placeholder="Search Holiday" v-model="searchName" aria-label="Search Holiday">
          </div>
          <div class="col">
            <button type="button" id=search class="btn btn-danger" @click="searchHoliday(searchName)">Search</button>
            <button type="button" id=create class="btn btn-danger" @click="createHoliday(name,startDateMessage,endDateMessage)">Create</button>
            <button type="button" id=update class="btn btn-danger" @click="updateHoliday(name, startDateMessage, endDateMessage)">Update</button>
            <button type="button" id=delete class="btn btn-danger" @click="deleteHoliday(searchName)">Delete</button>
            <button type="button" id=viewAll class="btn btn-danger"@click="viewAll">View All</button>
          </div>
        </div>
      </div>
    </div>
    <div id="holidayBlock">
    <div id="holidayInfo"  class="container">
      <div class="row">
        <div class="col-sm">
          <input type="text" id="Name" class="form-control" placeholder="Name" v-model="name" aria-label="Name">
          <div id="StartDateOptions" class="container">
            <div class="row">
              <div class="col-sm">
                <b-form-input id="startDateText" placeholder="Start Date" v-model="startDateMessage" readonly="readonly"></b-form-input>
              </div>
              <div>
                <div>
                  <b-button id="startDateButton" v-b-modal.modal-1>Select</b-button>
                  <b-modal id="modal-1" title="Update Date">
                    <b-dropdown id="dropdown-Year" :text="dropDownMessageYear1" class="m-md-2">
                      <b-dropdown-item @click="changeDropdownYear1('2022')">2022</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2023')">2023</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2024')">2024</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2025')">2025</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2026')">2026</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2027')">2027</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2028')">2028</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2029')">2029</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownYear1('2030')">2030</b-dropdown-item>
                    </b-dropdown>

                    <b-dropdown id="dropdown-Month" :text="dropDownMessageMonth1" class="m-md-2">
                      <b-dropdown-item @click="changeDropdownMonth1('January')">January</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('February')">February</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('March')">March</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('April')">April</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('May')">May</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('June')">June</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('July')">July</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('August')">August</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('September')">September</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('October')">October</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('November')">November</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownMonth1('December')">December</b-dropdown-item>
                    </b-dropdown>

                    <b-dropdown id="dropdown-Day" :text="dropDownMessageDay1" class="m-md-3">
                      <b-dropdown-item @click="changeDropdownDay1('01')">1</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('02')">2</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('03')">3</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('04')">4</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('05')">5</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('06')">6</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('07')">7</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('08')">8</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('09')">9</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('10')">10</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('11')">11</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('12')">12</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('13')">13</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('14')">14</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('15')">15</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('16')">16</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('17')">17</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('18')">18</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('19')">19</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('20')">20</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('21')">21</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('22')">22</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('23')">23</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('24')">24</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('25')">25</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('26')">26</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('27')">27</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('28')">28</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('29')">29</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('30')">30</b-dropdown-item>
                      <b-dropdown-item @click="changeDropdownDay1('31')">31</b-dropdown-item>
                    </b-dropdown>
                    <div slot="modal-footer" class="modal-footer">
                      <button id="cancelStart" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-1')">Cancel</button>
                      <button id="saveStart" type="button" class="btn btn-default" data-dismiss="modal" @click="changeStartDate(dropDownMessageYear1, monthMessage1, dropDownMessageDay1),$bvModal.hide('modal-1')">Save</button>
                    </div>
                  </b-modal>
                </div>
                </div>
            </div>
          </div>
          <div id="EndDateOptions" class="container">
            <div class="row">
              <div class="col-sm">
                <b-form-input id="endDateText" placeholder="End Date" v-model="endDateMessage" readonly="readonly"></b-form-input>
              </div>
              <div>
                <b-button id="endDateButton" v-b-modal.modal-2>Select</b-button>
                <b-modal id="modal-2" title="Update Date">
                  <b-dropdown id="dropdown-Year" :text="dropDownMessageYear2" class="m-md-2">
                    <b-dropdown-item @click="changeDropdownYear2('2022')">2022</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2023')">2023</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2024')">2024</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2025')">2025</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2026')">2026</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2027')">2027</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2028')">2028</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2029')">2029</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownYear2('2030')">2030</b-dropdown-item>
                  </b-dropdown>

                  <b-dropdown id="dropdown-Month" :text="dropDownMessageMonth2" class="m-md-2">
                    <b-dropdown-item @click="changeDropdownMonth2('January')">January</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('February')">February</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('March')">March</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('April')">April</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('May')">May</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('June')">June</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('July')">July</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('August')">August</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('September')">September</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('October')">October</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('November')">November</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownMonth2('December')">December</b-dropdown-item>
                  </b-dropdown>

                  <b-dropdown id="dropdown-Day" :text="dropdownMessageDay2" class="m-md-3">
                    <b-dropdown-item @click="changeDropdownDay2('01')">1</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('02')">2</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('03')">3</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('04')">4</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('05')">5</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('06')">6</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('07')">7</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('08')">8</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('09')">9</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('10')">10</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('11')">11</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('12')">12</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('13')">13</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('14')">14</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('15')">15</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('16')">16</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('17')">17</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('18')">18</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('19')">19</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('20')">20</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('21')">21</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('22')">22</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('23')">23</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('24')">24</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('25')">25</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('26')">26</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('27')">27</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('28')">28</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('29')">29</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('30')">30</b-dropdown-item>
                    <b-dropdown-item @click="changeDropdownDay2('31')">31</b-dropdown-item>
                  </b-dropdown>
                  <div slot="modal-footer" class="modal-footer">
                    <button id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-2')">Cancel</button>
                    <button id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="changeEndDate(dropDownMessageYear2, monthMessage2, dropdownMessageDay2),$bvModal.hide('modal-2')">Save</button>
                  </div>
                </b-modal>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div>
    <table id="holidayTable" class="tablestyle" align="center" v-show="visibleViewAll">
      <tr class="trstyle">
        <td> Name </td>
        <td> Start Date </td>
        <td> End Date </td>
      </tr>
      <tr class="trstyle" v-for="holiday in this.holidays">
        <td>{{ holiday.name }}</td>
        <td>{{ holiday.startDate }}</td>
        <td>{{ holiday.endDate }}</td>
      </tr>
    </table>
    <button type="button" id=hideAll class="btn btn-danger" aligncenter="holidayTable"  v-show="visibleViewAll" @click="hideAll">Hide</button>
      </div>
  </div>
</template>

<script src="./holiday.js">
import Header from "../components/Header.vue"
export default {
  components: {
    Header
  }

}
</script>

<style>
#HolidaySearchTextButton{
  margin-top: 100px;
  margin-left: 500px;
}
#search{
  margin-right: 20px;
}
#create{
  margin-right: 20px;
}

#update{
  margin-right: 20px;
}
#delete{
  margin-right: 20px;

}
#Name{
  margin-bottom: 20px;
  width: 27%;
}
#startDateText {
  margin-bottom: 20px;
  width: 60%;
}
#endDateText {
  margin-bottom: 20px;
  width: 60%;
}
#startDateButton{
  margin-right: 672px;
}
#endDateButton{
  margin-right: 672px;
}

#holidayInfo{
  margin-top: 100px;
  margin-left: 485px;
}
#Name{
  margin-left: 15px;
  width: 38%;
}
.m-md-3 .dropdown-menu{
    max-height:300px;
    overflow-y:auto;
}
#startDateButton{
  background-color: red;
}

#endDateButton{
  background-color: red;
}
#saveStart{
  background-color: red;
  color: white;
}

#cancelStart{
  background-color: red;
  color: white;
}

#saveEnd{
  background-color: red;
  color: white;
}

#cancelEnd{
  background-color: red;
  color: white;
}

#viewAll{
  margin-top: 30px;
  margin-right: 305px;
  width: 20%;
}
#hideAll{
  margin-top:20px;
  margin-right: 195px;
  alignment: center;
}

.tablestyle {
  margin-top: 100px;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}
</style>
