<template>
  <div>
    <Header />
    <div id ="HolidaySearchTextButton" class="col-7">
      <div id="searchRow" class="row-12">
        <div class="row g-3">
          <div class="col">
            <input type="text" class="form-control" placeholder="Search Holiday" aria-label="Search Holiday">
          </div>
          <div class="col">
            <button type="button" id=search class="btn btn-danger">Search</button>
            <button type="button" id=update class="btn btn-danger">Update</button>
            <button type="button" id=viewAll class="btn btn-danger">View All</button>
          </div>
        </div>
      </div>
    </div>
    <div id="holidayBlock">
    <div id="holidayInfo"  class="container">
      <div class="row">
        <div class="col-sm">
          <input type="text" id="Name" class="form-control" placeholder="Name" aria-label="Name">
          <div id="StartDateOptions" class="container">
            <div class="row">
              <div class="col-sm">
                <input type="text" id="StartDateText" class="form-control" placeholder="Start Date" aria-label="Start Date">
              </div>
              <div>
                <button type="button" id="startDateButton" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                  Select
                </button>

                </div>
            </div>
          </div>
          <div id="EndDateOptions" class="container">
            <div class="row">
              <div class="col-sm">
                <input type="text" id="EndDateText" class="form-control" placeholder="End Date" aria-label="End Date">
              </div>
              <div>
                <button type="button" id="endDateButton" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                  Select
                </button>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Select Date</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div>
              <b-dropdown id="dropdown-Year" text="Year" class="m-md-2">
                <b-dropdown-item>2022</b-dropdown-item>
                <b-dropdown-item>2023</b-dropdown-item>
                <b-dropdown-item>2024</b-dropdown-item>
                <b-dropdown-item>2025</b-dropdown-item>
                <b-dropdown-item>2026</b-dropdown-item>
                <b-dropdown-item>2027</b-dropdown-item>
                <b-dropdown-item>2028</b-dropdown-item>
                <b-dropdown-item>2029</b-dropdown-item>
                <b-dropdown-item>2030</b-dropdown-item>
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item active>Active action</b-dropdown-item>
                <b-dropdown-item disabled>Disabled action</b-dropdown-item>
              </b-dropdown>

              <b-dropdown id="dropdown-Month" text="Month" class="m-md-2">
                <b-dropdown-item>January</b-dropdown-item>
                <b-dropdown-item>February</b-dropdown-item>
                <b-dropdown-item>March</b-dropdown-item>
                <b-dropdown-item>April</b-dropdown-item>
                <b-dropdown-item>May</b-dropdown-item>
                <b-dropdown-item>June</b-dropdown-item>
                <b-dropdown-item>July</b-dropdown-item>
                <b-dropdown-item>August</b-dropdown-item>
                <b-dropdown-item>September</b-dropdown-item>
                <b-dropdown-item>October</b-dropdown-item>
                <b-dropdown-item>November</b-dropdown-item>
                <b-dropdown-item>December</b-dropdown-item>
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item active>Active action</b-dropdown-item>
                <b-dropdown-item disabled>Disabled action</b-dropdown-item>
              </b-dropdown>

              <b-dropdown id="dropdown-Day" text="Day" class="m-md-3">
                <b-dropdown-item>1</b-dropdown-item>
                <b-dropdown-item>2</b-dropdown-item>
                <b-dropdown-item>3</b-dropdown-item>
                <b-dropdown-item>4</b-dropdown-item>
                <b-dropdown-item>5</b-dropdown-item>
                <b-dropdown-item>6</b-dropdown-item>
                <b-dropdown-item>7</b-dropdown-item>
                <b-dropdown-item>8</b-dropdown-item>
                <b-dropdown-item>9</b-dropdown-item>
                <b-dropdown-item>10</b-dropdown-item>
                <b-dropdown-item>11</b-dropdown-item>
                <b-dropdown-item>12</b-dropdown-item>
                <b-dropdown-item>13</b-dropdown-item>
                <b-dropdown-item>14</b-dropdown-item>
                <b-dropdown-item>15</b-dropdown-item>
                <b-dropdown-item>16</b-dropdown-item>
                <b-dropdown-item>17</b-dropdown-item>
                <b-dropdown-item>18</b-dropdown-item>
                <b-dropdown-item>19</b-dropdown-item>
                <b-dropdown-item>20</b-dropdown-item>
                <b-dropdown-item>21</b-dropdown-item>
                <b-dropdown-item>22</b-dropdown-item>
                <b-dropdown-item>23</b-dropdown-item>
                <b-dropdown-item>24</b-dropdown-item>
                <b-dropdown-item>25</b-dropdown-item>
                <b-dropdown-item>26</b-dropdown-item>
                <b-dropdown-item>27</b-dropdown-item>
                <b-dropdown-item>28</b-dropdown-item>
                <b-dropdown-item>29</b-dropdown-item>
                <b-dropdown-item>30</b-dropdown-item>
                <b-dropdown-item>31</b-dropdown-item>
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item active>Active action</b-dropdown-item>
                <b-dropdown-item disabled>Disabled action</b-dropdown-item>
              </b-dropdown>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Select</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
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
#update{
  margin-right: 20px;
}
#Name{
  margin-bottom: 20px;
  width: 27%;
}
#StartDateText {
  margin-bottom: 20px;
  width: 60%;
}
#EndDateText {
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
</style>
