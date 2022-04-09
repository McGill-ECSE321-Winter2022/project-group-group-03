<template>
  <div>
    <Header/>
    <h1 id="title">Employee Information</h1>
    <table id="tablestyle" align="center">
      <tr>
        <div id="buttonseach">
          <td><b-form-input align="center" style="width:218%" id="searchemp" placeholder="search" v-model="Searchusername"> </b-form-input></td>
          <td><b-button id="searchbutton" @click="searchUsername(Searchusername)">search</b-button></td>
          <td><b-button id="searchbutton1" @click="fireEmployee()">fire</b-button></td>
          <td><b-button id="searchbutton2" @click="hireEmployee()">hire</b-button></td>
          <br>
        </div>
      </tr>
      <tr>
        <td><b-form-input style="width:61%" id="searchemp1" placeholder="username" v-model="username"> </b-form-input></td>

      </tr>
      <tr>
        <td><b-form-input style="width:61%" id="searchemp1" placeholder="address" v-model="address"> </b-form-input></td>
      </tr>
      <tr>
        <td><b-form-input style="width:61%" id="searchemp2" placeholder="email" v-model="email"> </b-form-input></td>
      </tr>
      <tr id="trstyle">
        <td> Day</td>
        <td> Start Time</td>
        <td> End Time</td>
      </tr>
      <tr id="trstyle">
        <td><button id="EMPhourbutton" v-b-modal.modal-15>Monday</button></td>
        <b-modal id="modal-15" title="Update Hours">
          <div align="center">
            <b-dropdown v-model="EMPbuttonMessage1" id="dropdown-left" :text="EMPdropDownMessage1" variant="primary" class="m-15">
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '01:00', EMPchangeOpeningHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '02:00', EMPchangeOpeningHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '03:00', EMPchangeOpeningHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '04:00', EMPchangeOpeningHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '05:00', EMPchangeOpeningHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '06:00', EMPchangeOpeningHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '07:00', EMPchangeOpeningHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '08:00', EMPchangeOpeningHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '09:00', EMPchangeOpeningHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '10:00', EMPchangeOpeningHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '11:00', EMPchangeOpeningHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = '12:00', EMPchangeOpeningHour1('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage1 = 'Add', EMPchangeOpeningHour1('change start time')">Add</b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-model="EMPbuttonMessage2" id="dropdown-left" :text="EMPdropDownMessage2" variant="primary" class="m-15">
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '01:00', EMPchangeOpeningHour2('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '02:00', EMPchangeOpeningHour2('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '03:00', EMPchangeOpeningHour2('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '04:00', EMPchangeOpeningHour2('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '05:00', EMPchangeOpeningHour2('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '06:00', EMPchangeOpeningHour2('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '07:00', EMPchangeOpeningHour2('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '08:00', EMPchangeOpeningHour2('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '09:00', EMPchangeOpeningHour2('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '10:00', EMPchangeOpeningHour2('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '11:00', EMPchangeOpeningHour2('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = '12:00', EMPchangeOpeningHour2('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage2 = 'Add', EMPchangeOpeningHour2('change end time')">Add</b-dropdown-item>
            </b-dropdown>
          </div>
          <div slot="modal-footer" class="modal-footer">
            <button  id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="mondaydisable" @click="$bvModal.hide('modal-15'), createWorkshift1(username)">Create</button>
            <button  id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-15'), EMPchangeButtonHour1(EMPdropDownMessage1, EMPdropDownMessage2)">Update</button>
            <button style="background-color: #e03444 ; color: white" id="deleteEnd" type="button" class="btn btn-default" data-dismiss="modal" :disable="mondaydisable1" @click="$bvModal.hide('modal-15'), deleteWorkshift1()" >Delete</button>
          </div>
        </b-modal>
        <td>{{EMPbuttonMessage1}}</td>
        <td>{{EMPbuttonMessage2}}</td>
      </tr>
      <tr id="trstyle">
        <td><button id="EMPhourbutton" v-b-modal.modal-16>Tuesday</button></td>
        <b-modal id="modal-16" title="Update Hours">
          <div align="center">
            <b-dropdown v-model="EMPbuttonMessage3" id="dropdown-left" :text="EMPdropDownMessage1" variant="primary" class="m-16">
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '01:00', EMPchangeOpeningHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '02:00', EMPchangeOpeningHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '03:00', EMPchangeOpeningHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '04:00', EMPchangeOpeningHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '05:00', EMPchangeOpeningHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '06:00', EMPchangeOpeningHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '07:00', EMPchangeOpeningHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '08:00', EMPchangeOpeningHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '09:00', EMPchangeOpeningHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '10:00', EMPchangeOpeningHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '11:00', EMPchangeOpeningHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = '12:00', EMPchangeOpeningHour1('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage3 = 'Add', EMPchangeOpeningHour1('change start time')">Add</b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-model="EMPbuttonMessage4" id="dropdown-left" :text="EMPdropDownMessage2" variant="primary" class="m-16">
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '01:00', EMPchangeOpeningHour2('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '02:00', EMPchangeOpeningHour2('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '03:00', EMPchangeOpeningHour2('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '04:00', EMPchangeOpeningHour2('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '05:00', EMPchangeOpeningHour2('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '06:00', EMPchangeOpeningHour2('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '07:00', EMPchangeOpeningHour2('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '08:00', EMPchangeOpeningHour2('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '09:00', EMPchangeOpeningHour2('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '10:00', EMPchangeOpeningHour2('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '11:00', EMPchangeOpeningHour2('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = '12:00', EMPchangeOpeningHour2('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage4 = 'Add', EMPchangeOpeningHour2('change end time')">Add</b-dropdown-item>
            </b-dropdown>
          </div>
          <div slot="modal-footer" class="modal-footer">
            <button id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="tuesdaydisable" @click="$bvModal.hide('modal-16'), createWorkshift2(username)">Create</button>
            <button id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-16'), EMPchangeButtonHour2(EMPdropDownMessage1, EMPdropDownMessage2)">Update</button>
            <button style="background-color: #e03444 ; color: white" id="deleteEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="tuesdaydisable1" @click="$bvModal.hide('modal-16'), deleteWorkshift2()" >Delete</button>
          </div>
        </b-modal>
        <td>{{EMPbuttonMessage3}}</td>
        <td>{{EMPbuttonMessage4}}</td>
      </tr>
      <tr id="trstyle">
        <td><button id="EMPhourbutton" v-b-modal.modal-17>Wednesday</button></td>
        <b-modal id="modal-17" title="Update Hours">
          <div align="center">
            <b-dropdown v-model="EMPbuttonMessage5" id="dropdown-left" :text="EMPdropDownMessage1" variant="primary" class="m-17">
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '01:00', EMPchangeOpeningHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '02:00', EMPchangeOpeningHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '03:00', EMPchangeOpeningHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '04:00', EMPchangeOpeningHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '05:00', EMPchangeOpeningHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '06:00', EMPchangeOpeningHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '07:00', EMPchangeOpeningHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '08:00', EMPchangeOpeningHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '09:00', EMPchangeOpeningHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '10:00', EMPchangeOpeningHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '11:00', EMPchangeOpeningHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = '12:00', EMPchangeOpeningHour1('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage5 = 'Add', EMPchangeOpeningHour1('change start time')">Add</b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-model="EMPbuttonMessage6" id="dropdown-left" :text="EMPdropDownMessage2" variant="primary" class="m-17">
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '01:00', EMPchangeOpeningHour2('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '02:00', EMPchangeOpeningHour2('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '03:00', EMPchangeOpeningHour2('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '04:00', EMPchangeOpeningHour2('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '05:00', EMPchangeOpeningHour2('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '06:00', EMPchangeOpeningHour2('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '07:00', EMPchangeOpeningHour2('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '08:00', EMPchangeOpeningHour2('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '09:00', EMPchangeOpeningHour2('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '10:00', EMPchangeOpeningHour2('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '11:00', EMPchangeOpeningHour2('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = '12:00', EMPchangeOpeningHour2('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage6 = 'Add', EMPchangeOpeningHour2('change end time')">Add</b-dropdown-item>
            </b-dropdown>
          </div>
          <div slot="modal-footer" class="modal-footer">
            <button id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="wednesdaydisable" @click="$bvModal.hide('modal-17'), createWorkshift3(username)">Create</button>
            <button id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-17'), EMPchangeButtonHour3(EMPdropDownMessage1, EMPdropDownMessage2)">Update</button>
            <button style="background-color: #e03444 ; color: white" id="deleteEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="wednesdaydisable1" @click="$bvModal.hide('modal-17'), deleteWorkshift3()" >Delete</button>
          </div>
        </b-modal>
        <td>{{EMPbuttonMessage5}}</td>
        <td>{{EMPbuttonMessage6}}</td>
      </tr>
      <tr id="trstyle">
        <td><button id="EMPhourbutton" v-b-modal.modal-18>Thursday</button></td>
        <b-modal id="modal-18" title="Update Hours">
          <div align="center">
            <b-dropdown v-model="EMPbuttonMessage7" id="dropdown-left" :text="EMPdropDownMessage1" variant="primary" class="m-18">
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '01:00', EMPchangeOpeningHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '02:00', EMPchangeOpeningHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '03:00', EMPchangeOpeningHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '04:00', EMPchangeOpeningHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '05:00', EMPchangeOpeningHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '06:00', EMPchangeOpeningHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '07:00', EMPchangeOpeningHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '08:00', EMPchangeOpeningHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '09:00', EMPchangeOpeningHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '10:00', EMPchangeOpeningHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '11:00', EMPchangeOpeningHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = '12:00', EMPchangeOpeningHour1('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage7 = 'Add', EMPchangeOpeningHour1('change start time')">Add</b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-model="EMPbuttonMessage8" id="dropdown-left" :text="EMPdropDownMessage2" variant="primary" class="m-18">
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '01:00', EMPchangeOpeningHour2('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '02:00', EMPchangeOpeningHour2('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '03:00', EMPchangeOpeningHour2('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '04:00', EMPchangeOpeningHour2('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '05:00', EMPchangeOpeningHour2('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '06:00', EMPchangeOpeningHour2('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '07:00', EMPchangeOpeningHour2('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '08:00', EMPchangeOpeningHour2('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '09:00', EMPchangeOpeningHour2('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '10:00', EMPchangeOpeningHour2('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '11:00', EMPchangeOpeningHour2('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = '12:00', EMPchangeOpeningHour2('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage8 = 'Add', EMPchangeOpeningHour2('change end time')">Add</b-dropdown-item>
            </b-dropdown>
          </div>
          <div slot="modal-footer" class="modal-footer">
            <button id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="thursdaydisable" @click="$bvModal.hide('modal-18'), createWorkshift4(username)">Create</button>
            <button id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-18'), EMPchangeButtonHour4(EMPdropDownMessage1, EMPdropDownMessage2)">Update</button>
            <button style="background-color: #e03444 ; color: white" id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="thursdaydisable1" @click="$bvModal.hide('modal-18'), deleteWorkshift4()" >Delete</button>
          </div>
        </b-modal>
        <td>{{EMPbuttonMessage7}}</td>
        <td>{{EMPbuttonMessage8}}</td>
      </tr>
      <tr id="trstyle">
        <td><button id="EMPhourbutton" v-b-modal.modal-19>Friday</button></td>
        <b-modal id="modal-19" title="Update Hours">
          <div align="center">
            <b-dropdown v-model="EMPbuttonMessage9" id="dropdown-left" :text="EMPdropDownMessage1" variant="primary" class="m-19">
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '01:00', EMPchangeOpeningHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '02:00', EMPchangeOpeningHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '03:00', EMPchangeOpeningHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '04:00', EMPchangeOpeningHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '05:00', EMPchangeOpeningHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '06:00', EMPchangeOpeningHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '07:00', EMPchangeOpeningHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '08:00', EMPchangeOpeningHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '09:00', EMPchangeOpeningHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '10:00', EMPchangeOpeningHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '11:00', EMPchangeOpeningHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = '12:00', EMPchangeOpeningHour1('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage9 = 'Add', EMPchangeOpeningHour1('change start time')">Add</b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-model="EMPbuttonMessage10" id="dropdown-left" :text="EMPdropDownMessage2" variant="primary" class="m-19">
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '01:00', EMPchangeOpeningHour2('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '02:00', EMPchangeOpeningHour2('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '03:00', EMPchangeOpeningHour2('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '04:00', EMPchangeOpeningHour2('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '05:00', EMPchangeOpeningHour2('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '06:00', EMPchangeOpeningHour2('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '07:00', EMPchangeOpeningHour2('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '08:00', EMPchangeOpeningHour2('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '09:00', EMPchangeOpeningHour2('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '10:00', EMPchangeOpeningHour2('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '11:00', EMPchangeOpeningHour2('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = '12:00', EMPchangeOpeningHour2('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage10 = 'Add', EMPchangeOpeningHour2('change end time')">Add</b-dropdown-item>
            </b-dropdown>
          </div>
          <div slot="modal-footer" class="modal-footer">
            <button id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="fridaydisable" @click="$bvModal.hide('modal-19'), createWorkshift5(username)">Create</button>
            <button id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-19'), EMPchangeButtonHour5(EMPdropDownMessage1, EMPdropDownMessage2)">Update</button>
            <button style="background-color: #e03444 ; color: white" id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="fridaydisable1" @click="$bvModal.hide('modal-19'), deleteWorkshift5()" >Delete</button>
          </div>
        </b-modal>
        <td>{{EMPbuttonMessage9}}</td>
        <td>{{EMPbuttonMessage10}}</td>
      </tr>
      <tr id="trstyle">
        <td><button id="EMPhourbutton" v-b-modal.modal-20>Saturday</button></td>
        <b-modal id="modal-20" title="Update Hours">
          <div align="center">
            <b-dropdown v-model="EMPbuttonMessage11" id="dropdown-left" :text="EMPdropDownMessage1" variant="primary" class="m-20">
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '01:00', EMPchangeOpeningHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '02:00', EMPchangeOpeningHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '03:00', EMPchangeOpeningHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '04:00', EMPchangeOpeningHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '05:00', EMPchangeOpeningHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '06:00', EMPchangeOpeningHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '07:00', EMPchangeOpeningHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '08:00', EMPchangeOpeningHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '09:00', EMPchangeOpeningHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '10:00', EMPchangeOpeningHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '11:00', EMPchangeOpeningHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = '12:00', EMPchangeOpeningHour1('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage11 = 'Add', EMPchangeOpeningHour1('change start time')">Add</b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-model="EMPbuttonMessage12" id="dropdown-left" :text="EMPdropDownMessage2" variant="primary" class="m-17">
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '01:00', EMPchangeOpeningHour2('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '02:00', EMPchangeOpeningHour2('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '03:00', EMPchangeOpeningHour2('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '04:00', EMPchangeOpeningHour2('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '05:00', EMPchangeOpeningHour2('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '06:00', EMPchangeOpeningHour2('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '07:00', EMPchangeOpeningHour2('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '08:00', EMPchangeOpeningHour2('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '09:00', EMPchangeOpeningHour2('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '10:00', EMPchangeOpeningHour2('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '11:00', EMPchangeOpeningHour2('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = '12:00', EMPchangeOpeningHour2('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage12 = 'Add', EMPchangeOpeningHour2('change end time')">Add</b-dropdown-item>
            </b-dropdown>
          </div>
          <div slot="modal-footer" class="modal-footer">
            <button id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="saturdaydisable" @click="$bvModal.hide('modal-20'), createWorkshift6(username)">Create</button>
            <button id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-20'), EMPchangeButtonHour6(EMPdropDownMessage1, EMPdropDownMessage2)">Update</button>
            <button style="background-color: #e03444 ; color: white" id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="saturdaydisable1" @click="$bvModal.hide('modal-20'), deleteWorkshift6()" >Delete</button>
          </div>
        </b-modal>
        <td>{{EMPbuttonMessage11}}</td>
        <td>{{EMPbuttonMessage12}}</td>
      </tr>
      <tr id="trstyle">
        <td><button id="EMPhourbutton" v-b-modal.modal-21>Sunday</button></td>
        <b-modal id="modal-21" title="Update Hours">
          <div align="center">
            <b-dropdown v-model="EMPbuttonMessage13" id="dropdown-left" :text="EMPdropDownMessage1" variant="primary" class="m-21">
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '01:00', EMPchangeOpeningHour1('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '02:00', EMPchangeOpeningHour1('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '03:00', EMPchangeOpeningHour1('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '04:00', EMPchangeOpeningHour1('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '05:00', EMPchangeOpeningHour1('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '06:00', EMPchangeOpeningHour1('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '07:00', EMPchangeOpeningHour1('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '08:00', EMPchangeOpeningHour1('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '09:00', EMPchangeOpeningHour1('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '10:00', EMPchangeOpeningHour1('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '11:00', EMPchangeOpeningHour1('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = '12:00', EMPchangeOpeningHour1('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage13 = 'Add', EMPchangeOpeningHour1('change start time')">Add</b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-model="EMPbuttonMessage14" id="dropdown-left" :text="EMPdropDownMessage2" variant="primary" class="m-21">
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '01:00', EMPchangeOpeningHour2('01:00')">01:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '02:00', EMPchangeOpeningHour2('02:00')">02:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '03:00', EMPchangeOpeningHour2('03:00')">03:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '04:00', EMPchangeOpeningHour2('04:00')">04:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '05:00', EMPchangeOpeningHour2('05:00')">05:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '06:00', EMPchangeOpeningHour2('06:00')">06:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '07:00', EMPchangeOpeningHour2('07:00')">07:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '08:00', EMPchangeOpeningHour2('08:00')">08:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '09:00', EMPchangeOpeningHour2('09:00')">09:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '10:00', EMPchangeOpeningHour2('10:00')">10:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '11:00', EMPchangeOpeningHour2('11:00')">11:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = '12:00', EMPchangeOpeningHour2('12:00')">12:00</b-dropdown-item>
              <b-dropdown-item href="#" @click="EMPbuttonMessage14 = 'Add', EMPchangeOpeningHour2('change end time')">Add</b-dropdown-item>
            </b-dropdown>
          </div>
          <div slot="modal-footer" class="modal-footer">
            <button id="cancelEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="sundaydisable" @click="$bvModal.hide('modal-21'), createWorkshift7(username)">Create</button>
            <button id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" @click="$bvModal.hide('modal-21'), EMPchangeButtonHour7(EMPdropDownMessage1, EMPdropDownMessage2)">Update</button>
            <button style="background-color: #e03444 ; color: white" id="saveEnd" type="button" class="btn btn-default" data-dismiss="modal" :disabled="sundaydisable1" @click="$bvModal.hide('modal-21'), deleteWorkshift7()" >Delete</button>
          </div>
        </b-modal>
        <td>{{EMPbuttonMessage13}}</td>
        <td>{{EMPbuttonMessage14}}</td>
      </tr>
    </table>
    <br>
    <br>
    <div align="center">
      <b-alert style="max-width: 50%" :show="setAlert()" dismissible variant="danger" @dismissed="setErrorEmpty()">
        {{create_error}}
      </b-alert>
    </div>


  </div>
</template>

<script src="./ownerEmployee.js">
</script>

<style>
#title {
  margin-top: 10px;
}

#tablestyle {
  margin-top: 60px;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}
#searchbutton2{
  margin-left: 1010%
}

#trstyle:nth-child(even) {
  background-color: #dddddd;
}
#searchemp{
  margin-left: 120%;
}
#searchbutton{
  margin-left: 710%;
}
#buttonseach{
  margin-left: 3.5%;
}
#searchbutton1{
  margin-left: 1095%;
}
#searchemp1{
  margin-left: 37%;
}
#searchemp2 {
  margin-left: 37%;
  margin-bottom: 3%;
}
#create{
 margin-left: 740%;
}
#EMPhourbutton{
  background-color: Transparent;
  background-repeat:no-repeat;
  border: none;
  cursor:pointer;
  overflow: hidden;

}

</style>
