import Header from "./Header";

function EmployeeDTO(username,password,email,address,workingStatus){
  this.username = username
  this.password = password
  this.email = email
  this.address = address
  this.workingStatus = workingStatus
}

export default {
  name: "Profile",
  data() {
    return {
      username: '',
      password: '',
      email: '',
      address: '',
      workingStatus: ''
    }
  },
  components: {
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
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity()
      this.nameState = valid
      return valid
    },
    resetModal() {
      this.name = ''
      this.nameState = null
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }
      // Push the name to submitted names
      this.submittedNames.push(this.name)
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    }
  }
}
