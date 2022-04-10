<template>
  <div>
    <Header />
    <!-- Holiday Search text field + Buttons !-->
    <div id ="HolidaySearchTextButton" class="col-7">
      <div id="searchRow" class="row-12">
        <div class="row g-3">
          <div class="col">
            <!-- Holiday Search text field !-->
            <input type="text" class="form-control" placeholder="Search Holiday" v-model="searchName" aria-label="Search Holiday">
          </div>
          <div class="col">
            <!-- Search button !-->
            <button type="button" id=search class="btn btn-danger" @click="searchHoliday(searchName)">Search</button>
            <!-- Create button !-->
            <button type="button" id=create class="btn btn-danger" @click="createHoliday(name,startDateMessage,endDateMessage)">Create</button>
            <!-- Update button !-->
            <button type="button" id=update class="btn btn-danger" @click="updateHoliday(name, startDateMessage, endDateMessage)">Update</button>
            <!-- Delete button !-->
            <button type="button" id=delete class="btn btn-danger" @click="deleteHoliday(searchName)">Delete</button>
            <!-- View All button !-->
            <button type="button" id=viewAll class="btn btn-danger"@click="viewAll">View All</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Holiday Information !-->
    <div id="holidayBlock">
    <div id="holidayInfo"  class="container">
      <div class="row">
        <div class="col-sm">
          <input type="text" id="Name" class="form-control" placeholder="Name" v-model="name" aria-label="Name">
          <div id="StartDateOptions" class="container">
            <div class="row">
              <div class="col-sm">
                <!-- StartDate Information !-->
                <b-form-input id="startDateText" placeholder="Start Date" v-model="startDateMessage" readonly="readonly"></b-form-input>
              </div>
              <div>
                <div>
                  <!-- StartDate Edit Button (MODAL) !-->
                  <b-button id="startDateButton" v-b-modal.modal-1>Select</b-button>
                  <b-modal id="modal-1" title="Update Date">
                    <!-- StartDate Modal YEAR dropdown !-->
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
                    <!-- StartDate Modal MONTH dropdown !-->
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
                    <!-- StartDate Modal DAY dropdown !-->
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
                    <!-- StartDate Modal cancel and save buttons !-->
                    <div slot="modal-footer" class="modal-footer">
                      <button id="cancelStart" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-1')">Cancel</button>
                      <button id="saveStart" type="button" class="btn btn-default" data-dismiss="modal" @click="changeStartDate(dropDownMessageYear1, monthMessage1, dropDownMessageDay1),$bvModal.hide('modal-1')">Save</button>
                    </div>
                  </b-modal>
                </div>
                </div>
            </div>
          </div>
          <!-- EndDate options !-->
          <div id="EndDateOptions" class="container">
            <div class="row">
              <div class="col-sm">
                <!-- EndDate text field !-->
                <b-form-input id="endDateText" placeholder="End Date" v-model="endDateMessage" readonly="readonly"></b-form-input>
              </div>
              <div>
                <!-- EndDate Edit Button (MODAL) !-->
                <b-button id="endDateButton" v-b-modal.modal-2>Select</b-button>
                <b-modal id="modal-2" title="Update Date">
                  <!-- EndDate modal YEAR dropdown !-->
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
                  <!-- EndDate modal MONTH dropdown !-->
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
                  <!-- EndDate modal DAY dropdown !-->
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
                  <!-- EndDate modal Cancel and Save buttons !-->
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
      <!-- Error handling alert  !-->
      <div align="center">
        <b-alert  style="max-width: 50%" :show="setAlert()" dismissible variant="danger" @dismissed="setErrorEmpty()">
          {{errorHoliday}}
        </b-alert>
      </div>

  </div>
    <!-- Table containing all of the holidays !-->
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
  margin-top: 7%;
  /*margin-top: 100px;*/
  margin-left: 32.5%;
  width: 105%
}
#search{
  margin-right: 5%;
}
#create{
  margin-right: 5%;
}

#update{
  margin-right: 5%;
}
#delete{
  margin-right: 5%;

}
#Name{
  margin-bottom: 5%;
  width: 27%;
}
#startDateText {
  margin-bottom: 5%;
  width: 60%;
}
#endDateText {
  margin-bottom: 5%;
  width: 60%;
}
#startDateButton{
  margin-right: 672px;
}
#endDateButton{
  margin-right: 672px;
}

#holidayInfo{
  margin-top: 8%;
  margin-left: 31.6%;
}
#Name{
  margin-left: 1%;
  width: 38%;
}
.m-md-3 .dropdown-menu{
    max-height:300px;
    overflow-y:auto;
}
#startDateButton{
  background-color: #e03444;
}

#endDateButton{
  background-color: #e03444;
}
#saveStart{
  background-color: #e03444;
  color: white;
}

#cancelStart{
  background-color: #e03444;
  color: white;
}

#saveEnd{
  background-color: #e03444;
  color: white;
}

#cancelEnd{
  background-color: #e03444;
  color: white;
}

#viewAll{
  margin-top: 6%;
  margin-right: 26%;
  width: 20%;
}
#hideAll{
  margin-top:4%;
  margin-right: 5%;
  alignment: center;
}

.tablestyle {
  margin-top: 7%;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}
</style>
