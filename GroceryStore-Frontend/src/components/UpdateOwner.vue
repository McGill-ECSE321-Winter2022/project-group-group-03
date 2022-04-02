<template>
  <div>
  <Header/>
  <h1 id="title">My Owner Profile</h1>
  <p class="form">Username: {{this.username}}</p>
  <p class="form">Email: {{this.email}}</p>
  <p class="form">Password: {{this.password}}</p>
  <b-button v-b-modal.modal-prevent-closing class="btn">Update Password</b-button>
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
  </div>
</template>

<script>
import Header from "./EmployeeNav"

function OwnerDTO(username,password,email,address){
  this.username = username
  this.password = password
  this.email = email
}
export default {
  name: "UpdateOwner",
  data() {
    return {
      username: '',
      password: '',
      email: '',
      address: '',
      workingStatus: '',
      newPassword: ''
    }
  },
  components: {
    Header
  },
  created: function () {
    this.username = 'Mark'
    this.password = 'password'
    this.email = 'email'
  },
  methods: {
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit() {
      this.password=this.newPassword
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    }
  }
}
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
