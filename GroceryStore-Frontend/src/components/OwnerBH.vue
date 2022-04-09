<template>
  <div>
    <Header />
    <div class="row">
      <div id="col5" class="col-6">

        <table id="table">
          <tr>
              <td>Mon.</td>
              <td><b-button id="hourbutton" v-b-modal.modal-1>{{this.buttonMessage1}}</b-button></td>
            <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store via a dropdown  -->
              <b-modal id="modal-1" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-1">
                    <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour1('01:00')">01:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour1('02:00')">02:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour1('03:00')">03:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour1('04:00')">04:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour1('05:00')">05:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour1('06:00')">06:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour1('07:00')">07:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour1('08:00')">08:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour1('09:00')">09:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour1('10:00')">10:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour1('11:00')">11:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour1('12:00')">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="am">a.m</td>
              <div id="topm">
                <td>To</td>
                <td><b-button id="hourbutton2" v-b-modal.modal-2>{{this.buttonMessage2}}</b-button></td>
                <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
                <b-modal id="modal-2" title="Update Hours">
                  <div align="center">
                    <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-2">
                      <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour2('01:00')">01:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour2('02:00')">02:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour2('03:00')">03:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour2('04:00')">04:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour2('05:00')">05:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour2('06:00')">06:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour2('07:00')">07:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour2('08:00')">08:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour2('09:00')">09:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour2('10:00')">10:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour2('11:00')">11:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour2('12:00')">12:00</b-dropdown-item>
                    </b-dropdown>
                  </div>
                </b-modal>
                <td id="pm">p.m</td>
              </div>
          </tr>
          <tr>
              <td>Tues.</td>
              <td><b-button id="hourbutton" v-b-modal.modal-3> {{this.buttonMessage3}}</b-button></td>
            <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
            <b-modal id="modal-3" title="Update Hours">
              <div align="center">
                <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-3">
                  <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour3('01:00')">01:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour3('02:00')">02:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour3('03:00')">03:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour3('04:00')">04:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour3('05:00')">05:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour3('06:00')">06:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour3('07:00')">07:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour3('08:00')">08:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour3('09:00')">09:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour3('10:00')">10:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour3('11:00')">11:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour3('12:00')">12:00</b-dropdown-item>
                </b-dropdown>
              </div>
            </b-modal>
              <td id="am">a.m</td>
              <div id="topm">
                <td>To</td>
                <td><b-button id="hourbutton2" v-b-modal.modal-4>{{this.buttonMessage4}} </b-button></td>
                <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
                <b-modal id="modal-4" title="Update Hours">
                  <div align="center">
                    <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-4">
                      <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour4('01:00')">01:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour4('02:00')">02:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour4('03:00')">03:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour4('04:00')">04:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour4('05:00')">05:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour4('06:00')">06:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour4('07:00')">07:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour4('08:00')">08:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour4('09:00')">09:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour4('10:00')">10:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour4('11:00')">11:00</b-dropdown-item>
                      <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour4('12:00')">12:00</b-dropdown-item>
                    </b-dropdown>
                  </div>
                </b-modal>
                <td id="pm">p.m</td>
              </div>
          </tr>
          <tr>
            <td>Wed.</td>
            <td><b-button id="hourbutton" v-b-modal.modal-5>{{this.buttonMessage5}}</b-button></td>
            <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
            <b-modal id="modal-5" title="Update Hours">
              <div align="center">
                <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-5">
                  <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour5('01:00')">01:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour5('02:00')">02:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour5('03:00')">03:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour5('04:00')">04:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour5('05:00')">05:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour5('06:00')">06:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour5('07:00')">07:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour5('08:00')">08:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour5('09:00')">09:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour5('10:00')">10:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour5('11:00')">11:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour5('12:00')">12:00</b-dropdown-item>
                </b-dropdown>
              </div>
            </b-modal>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><b-button id="hourbutton2" v-b-modal.modal-6>{{this.buttonMessage6}}</b-button></td>
              <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
              <b-modal id="modal-6" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-6">
                    <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour6('01:00')">01:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour6('02:00')">02:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour6('03:00')">03:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour6('04:00')">04:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour6('05:00')">05:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour6('06:00')">06:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour6('07:00')">07:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour6('08:00')">08:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour6('09:00')">09:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour6('10:00')">10:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour6('11:00')">11:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour6('12:00')">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Thurs.</td>
            <td><b-button id="hourbutton" v-b-modal.modal-7>{{this.buttonMessage7}}</b-button></td>
            <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
            <b-modal id="modal-7" title="Update Hours">
              <div align="center">
                <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-7">
                  <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour7('01:00')">01:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour7('02:00')">02:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour7('03:00')">03:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour7('04:00')">04:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour7('05:00')">05:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour7('06:00')">06:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour7('07:00')">07:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour7('08:00')">08:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour7('09:00')">09:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour7('10:00')">10:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour7('11:00')">11:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour7('12:00')">12:00</b-dropdown-item>
                </b-dropdown>
              </div>
            </b-modal>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><b-button id="hourbutton2" v-b-modal.modal-8>{{this.buttonMessage8}}</b-button></td>
              <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
              <b-modal id="modal-8" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-8">
                    <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour8('01:00')">01:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour8('02:00')">02:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour8('03:00')">03:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour8('04:00')">04:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour8('05:00')">05:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour8('06:00')">06:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour8('07:00')">07:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour8('08:00')">08:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour8('09:00')">09:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour8('10:00')">10:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour8('11:00')">11:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour8('12:00')">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Fri.</td>
            <td><b-button id="hourbutton" v-b-modal.modal-9>{{this.buttonMessage9}} </b-button></td>
            <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
            <b-modal id="modal-9" title="Update Hours">
              <div align="center">
                <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-9">
                  <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour9('01:00')">01:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour9('02:00')">02:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour9('03:00')">03:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour9('04:00')">04:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour9('05:00')">05:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour9('06:00')">06:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour9('07:00')">07:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour9('08:00')">08:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour9('09:00')">09:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour9('10:00')">10:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour9('11:00')">11:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour9('12:00')">12:00</b-dropdown-item>
                </b-dropdown>
              </div>
            </b-modal>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><b-button id="hourbutton2" v-b-modal.modal-10>{{this.buttonMessage10}}</b-button></td>
              <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
              <b-modal id="modal-10" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-10">
                    <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour10('01:00')">01:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour10('02:00')">02:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour10('03:00')">03:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour10('04:00')">04:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour10('05:00')">05:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour10('06:00')">06:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour10('07:00')">07:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour10('08:00')">08:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour10('09:00')">09:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour10('10:00')">10:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour10('11:00')">11:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour10('12:00')">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Sat.</td>
            <td><b-button id="hourbutton" v-b-modal.modal-11>{{this.buttonMessage11}}</b-button></td>
            <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
            <b-modal id="modal-11" title="Update Hours">
              <div align="center">
                <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-11">
                  <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour11('01:00')">01:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour11('02:00')">02:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour11('03:00')">03:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour11('04:00')">04:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour11('05:00')">05:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour11('06:00')">06:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour11('07:00')">07:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour11('08:00')">08:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour11('09:00')">09:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour11('10:00')">10:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour11('11:00')">11:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour11('12:00')">12:00</b-dropdown-item>
                </b-dropdown>
              </div>
            </b-modal>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><b-button id="hourbutton2" v-b-modal.modal-12>{{this.buttonMessage12}}</b-button></td>
              <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
              <b-modal id="modal-12" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-12">
                    <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour12('01:00')">01:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour12('02:00')">02:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour12('03:00')">03:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour12('04:00')">04:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour12('05:00')">05:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour12('06:00')">06:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour12('07:00')">07:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour12('08:00')">08:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour12('09:00')">09:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour12('10:00')">10:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour12('11:00')">11:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour12('12:00')">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="pm">p.m</td>
            </div>
          </tr>
          <tr>
            <td>Sun.</td>
            <td><b-button id="hourbutton" v-b-modal.modal-13>{{this.buttonMessage13}}</b-button></td>
            <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
            <b-modal id="modal-13" title="Update Hours">
              <div align="center">
                <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-13">
                  <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour13('01:00')">01:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour13('02:00')">02:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour13('03:00')">03:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour13('04:00')">04:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour13('05:00')">05:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour13('06:00')">06:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour13('07:00')">07:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour13('08:00')">08:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour13('09:00')">09:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour13('10:00')">10:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour13('11:00')">11:00</b-dropdown-item>
                  <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour13('12:00')">12:00</b-dropdown-item>
                </b-dropdown>
              </div>
            </b-modal>
            <td id="am">a.m</td>
            <div id="topm">
              <td>To</td>
              <td><b-button id="hourbutton2" v-b-modal.modal-14>{{this.buttonMessage14}}</b-button></td>
              <!--   upon clicking on the time, opens a modal which can be used to change the opening and closing hours of the store  -->
              <b-modal id="modal-14" title="Update Hours">
                <div align="center">
                  <b-dropdown id="dropdown-left" :text="dropDownMessage" variant="primary" class="m-14">
                    <b-dropdown-item href="#" @click="changeOpeningHour('01:00'), changeButtonHour14('01:00')">01:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('02:00'), changeButtonHour14('02:00')">02:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('03:00'), changeButtonHour14('03:00')">03:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('04:00'), changeButtonHour14('04:00')">04:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('05:00'), changeButtonHour14('05:00')">05:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('06:00'), changeButtonHour14('06:00')">06:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('07:00'), changeButtonHour14('07:00')">07:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('08:00'), changeButtonHour14('08:00')">08:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('09:00'), changeButtonHour14('09:00')">09:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('10:00'), changeButtonHour14('10:00')">10:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('11:00'), changeButtonHour14('11:00')">11:00</b-dropdown-item>
                    <b-dropdown-item href="#" @click="changeOpeningHour('12:00'), changeButtonHour14('12:00')">12:00</b-dropdown-item>
                  </b-dropdown>
                </div>
              </b-modal>
              <td id="pm">p.m</td>
            </div>
          </tr>
        </table>
      </div>
    </div>
  </div>


</template>


<script src="./businesshours.js">
import Header from "../components/Header.vue"
export default {
  components: {
    Header
  }
}
</script>

<style>
  #col5{
    color: red;
    margin-left: 20%;
    font-size: xxx-large;
    margin-top: 5%;
  }
  #hourbutton{
    transition-duration: 0.4s;
    font-size: xx-large;
    margin-left: 300px;
    margin-right: 5px;
    width: 10vw;
  }
  #hourbutton:hover{
    color:white;
    background-color: red;
  }
  #hourbutton2{
    margin-left: 20px;
    margin-right: 5px;
    font-size: xx-large;
    transition-duration: 0.4s;
    width: 10vw;
  }
  #hourbutton2:hover{
    color:white;
    background-color: red;
  }
  #am{
    font-size: xx-large;
    margin-right: 100px;
  }
  #pm{
    font-size: xx-large;
  }
  #topm{
    margin-left: 15px;
  }
</style>

