import Header from "./Header";

function EmployeeDTO(username,password,email,address,workingStatus){
  this.username = username
  this.password = password
  this.email = email
  this.address = address
  this.workingStatus = workingStatus
}

export default {
  name: "EmployeeProfile",
  data() {
    return {
      username: '',
      password: '',
      email: '',
      address: '',
      workingStatus: '',
      newPassword: '',
      newAddress: '',
      oldPassword: this.password,
      oldAddress: this.address

    }
  },

  components:{
    Header
  },
  created: function () {
    this.username = 'Mark'
    this.password = 'password'
    this.email = 'email'
    this.address = '998 rue Address'
    this.workingStatus = 'Hired'
  },
  methods: {
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleOk2(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmitAddress()
    },
    handleSubmit() {
      this.password=this.newPassword
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    },
    handleSubmitAddress(){
      this.address=this.newAddress
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    }
  }
}
