<template>
    <div>
      <Header />
      <h1 id="title">Update personal info</h1>
    <b-form inline @submit="onSubmit" @reset="onReset" >
      <b-form-group id="input-group-1" label="Change password: " label-for="input-1">
        <b-form-input id="input-1" v-model="form.password" type="password" placeholder="Enter password"></b-form-input>
        <b-button class="button" type="submit" variant="danger">Confirm</b-button>
        <b-button class="button" type="reset" variant="danger">Reset</b-button>
      </b-form-group>
      </b-form>
      <b-form inline @submit="onSubmit" @reset="onReset" >
      <b-form-group id="input-group-2" label="Change address: " label-for="input-2">
        <b-form-input id="input-2" v-model="form.address" placeholder="Enter address"></b-form-input>
         <b-button class="button" type="submit" variant="danger">Confirm</b-button>
        <b-button class="button" type="reset" variant="danger">Reset</b-button>
      </b-form-group>
    </b-form>
    </div>
</template>
<script>
import Header from "../components/Header.vue"
export default {
  data() {
    return {
      form: {
        password: '',
        address: '',
      },
      show: true
    }
  },
  components: {
    Header
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.form.password = ''
      this.form.address = ''
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
</script>

<style>
#title{
  margin-top: 10px;
}
#input-group-1{
  margin-top: 100px;
  margin-left: 400px;
  position: center;
}
#input-group-2{
  margin-top: 100px;
  margin-left: 400px;
  position: center;
}
.button:hover{
  background-color: red;
  border-color: red;
}
</style>

