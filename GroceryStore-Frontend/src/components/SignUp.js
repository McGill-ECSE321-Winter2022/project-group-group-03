export default {
  data() {
    return {
      account_msg: "Please Choose Account Type to Create"
    }
  },
  methods: {
    changeMessage: function (accountType) {
      if (this.account_msg===accountType){
        this.account_msg="Please Choose Account Type to Create"
      }
      else{this.account_msg = accountType}
    }
  }
}
