export default {
  data() {
    return {
      msg: "Welcome to Dr. Kanaan's Online Grocery Store",
      login_msg: "Please Choose Account Type to Login as"
    }
  },
  methods: {
    changeMessage: function (accountType) {
      if (this.login_msg===accountType){
        this.login_msg="Please Choose Account Type to Login as"
      }
      else{this.login_msg = accountType}
    }
  }
}

