<template>
<div>
  <Header/>
  <h1 id="title">My Profile</h1>
  <p class="form">Username: {{this.username}}</p>
  <p class="form">Email: {{this.email}}</p>
  <p class="form">Address: {{this.address}} </p>
  <p class="form">Password: {{this.password}}</p>
  <p class="form">Working Status:{{this.workingStatus}}</p>
  <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button>
  <b-button v-b-modal.modal-prevent-closing2 class="btn">Update Address</b-button>
  <b-modal
    id="modal-prevent-closing"
    ref="modal"
    title="Update your password"
    @ok="handleOk"
  >
    <form ref="form" @submit.stop.prevent="handleSubmit">
      <b-form-group
        label="Please enter a new password"
        label-for="password-input"
        invalid-feedback="Password is required"
      >
        <input id="password_input" type="password" v-model="newPassword" placeholder="New Password">
      </b-form-group>
    </form>
  </b-modal>

  <b-modal
    id="modal-prevent-closing2"
    ref="modal"
    title="Update your address"
    @ok="handleOk2"
  >
    <form ref="form" @submit.stop.prevent="handleSubmitAddress">
      <b-form-group
        label="Please enter a new address"
        label-for="address-input"
        invalid-feedback="Address is required"
      >
        <input id="address_input" type="text" v-model="newAddress" placeholder="New Address">
      </b-form-group>
    </form>
  </b-modal>
</div>
</template>

<script src="./employeeprofile.js">
</script>

<style scoped>
#title{
  margin-top: 50px;
  color: #e03444;
  font-weight: bold;

}
#inline{display:inline;}

.form{
  font-size: xx-large;
}
.btn{
  margin-top: 60px;
}
</style>
