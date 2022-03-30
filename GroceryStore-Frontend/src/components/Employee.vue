<template>
    <div>
      <h1>Update personal info</h1>
    <b-form inline @submit="onSubmit" @reset="onReset" >
      <b-form-group id="input-group-1" label="Change password:" label-for="input-1">
        <b-form-input id="input-1" v-model="form.password" type="password" placeholder="Enter password"></b-form-input>
        <b-button type="submit" variant="primary">Confirm</b-button>
        <b-button type="reset" variant="info">Reset</b-button>
      </b-form-group>
      </b-form>
      <b-form inline @submit="onSubmit" @reset="onReset" >
      <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
        <b-form-input id="input-2" v-model="form.address" placeholder="Enter name"></b-form-input>
         <b-button type="submit" variant="primary">Confirm</b-button>
        <b-button type="reset" variant="info">Reset</b-button>
      </b-form-group>
    </b-form>
    </div>
  </template>
<!--<template>-->
<!--  <body>-->
<!--  <main class="form">-->
<!--    <h1>Update personal info</h1>-->
<!--    <b-form @submit="onSubmit" >-->
<!--      <b-form-group-->
<!--        id="input-group-1"-->
<!--        label="Your new name:"-->
<!--        label-for="input-1"-->
<!--      >-->
<!--        <b-form-input-->
<!--          id="input-1"-->
<!--          v-model="formName"-->
<!--          :placeholder="name"-->
<!--          required-->
<!--        ></b-form-input>-->
<!--      </b-form-group>-->
<!--      <b-form-group-->
<!--        id="input-group-3"-->
<!--        label="Your new address:"-->
<!--        label-for="input-3"-->
<!--      >-->
<!--        <b-form-input-->
<!--          id="input-3"-->
<!--          type="text"-->
<!--          v-model="formAddress"-->
<!--          :placeholder="address"-->
<!--          required-->
<!--        ></b-form-input>-->
<!--      </b-form-group>-->
<!--      <b-button v-on:click="onSubmit" variant="primary" v-bind:disabled="!this.formName.trim() || !this.formAddress.trim()">Submit</b-button>-->
<!--    </b-form>-->
<!--    <p v-if="errorMessage" class="error-message">-->
<!--      ERROR: {{ this.errorMessage }}-->
<!--    </p>-->
<!--    <p v-if="confirmationMessage" class="confirmation-message">-->
<!--      {{ this.confirmationMessage }}-->
<!--    </p>-->
<!--  </main>-->
<!--  </body>-->
<!--</template>-->


<script>
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
#input-group-1{
  margin-top: 100px;
}
</style>

