import Header from "./EmployeeNav"
import Cart from "./Cart"
import Footer from "./Footer"

import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function PurchasedItemDTO(aItem, aItemQuantity, aPurchasedItemID) {
  this.aItem = aItem
  this.aItemQuantity = aItemQuantity
  this.aPurchasedItemID = aPurchasedItemID
}
export default {
  name: 'purchasedItem',

  data() {
    return{
      translatedPurchasedItems: [],
      orderType: '',
      deliveryAddress: '',
      addressState: null,
      deliveryType: null,
      paymentType: null,
      totalCost: 0,
      error: false,
      loggedIn_employee:'',
      loggedIn_customer:''
    }
  },

  components: {
    Header,
    Cart,
    Footer
  },

  async created() {
    if(sessionStorage.accountType==="Employee"){
      this.loggedIn_employee=true
      console.log(this.loggedIn_employee)
    }else if(sessionStorage.accountType==="Customer"){
      this.loggedIn_customer=true
    }
    await this.getOrder()
    this.orderType = sessionStorage.orderType
    let list1 = JSON.parse(sessionStorage.purchasedItemList)
    let list2 = []
    for (let index in list1) {
      list2.push(JSON.parse(list1[index]))
    }
    list2.sort(function (a, b) {
      if (a.aItem.name < b.aItem.name) {
        return -1
      } else if (a.aItem.name > b.aItem.name) {
        return 1
      }
      return 0
    })
    console.log(list2)
    this.translatedPurchasedItems = list2
    this.totalCost = sessionStorage.price
  },

  methods: {

    async cash() {
      console.log("/convertToPickup?username=".concat(sessionStorage.username, "&paymentMethod=Cash&accountType=", sessionStorage.accountType))
      await AXIOS.put("/convertToPickup?username=".concat(sessionStorage.username, "&paymentMethod=Cash&accountType=", sessionStorage.accountType))

      await this.getOrder()
      // Push the name to submitted names
      sessionStorage.deliveryAddress = this.deliveryAddress
    },
    async credit() {
      console.log("/convertToPickup?username=".concat(sessionStorage.username, "&paymentMethod=CreditCard&accountType=", sessionStorage.accountType))
      await AXIOS.put("/convertToPickup?username=".concat(sessionStorage.username, "&paymentMethod=CreditCard&accountType=", sessionStorage.accountType))

      await this.getOrder()
      // Push the name to submitted names
      sessionStorage.deliveryAddress = this.deliveryAddress
    },

    checkFormValidity() {
      const valid = this.$refs.form.checkValidity()
      this.addressState = valid
      return valid
    },
    resetModal() {
      this.deliveryAddress = ''
      this.addressState = null
    },
    handleOkIT(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmitIT()
    },
    async handleSubmitIT() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }
      console.log("/convertToDelivery?username=".concat(sessionStorage.username, "&shippingAddress=", this.deliveryAddress, "&accountType=", sessionStorage.accountType, "&isOutOfTown=false"))
      await AXIOS.put("/convertToDelivery?username=".concat(sessionStorage.username, "&shippingAddress=", this.deliveryAddress, "&accountType=", sessionStorage.accountType, "&isOutOfTown=false"))

      await this.getOrder()
      // Push the name to submitted names
      sessionStorage.deliveryAddress = this.deliveryAddress
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    },
    setError(){
      this.error = false
    },
    handleOkOOT(bvModalEvt2) {
      // Prevent modal from closing
      bvModalEvt2.preventDefault()
      // Trigger submit handler
      this.handleSubmitOOT()
    },
    async handleSubmitOOT() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }
      console.log("/convertToDelivery?username=".concat(sessionStorage.username,"&shippingAddress=",this.deliveryAddress, "&accountType=",sessionStorage.accountType, "&isOutOfTown=true"))
      await AXIOS.put("/convertToDelivery?username=".concat(sessionStorage.username,"&shippingAddress=",this.deliveryAddress, "&accountType=",sessionStorage.accountType, "&isOutOfTown=true"))
      // Push the name to submitted names
      sessionStorage.deliveryAddress = this.deliveryAddress
      await this.getOrder()
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing2')
      })
    },
    deleteItem: async function (purchasedItemID) {
      console.log("Deleting purchased item")
      await AXIOS.delete("./purchased_item/".concat(purchasedItemID))
      await this.getOrder()
      this.TranslatePurchasedItems()
    },

    TranslatePurchasedItems() {
      console.log("translating JSON list")
      let list1 = JSON.parse(sessionStorage.purchasedItemList)
      let list2 = []
      for (let index in list1) {
        list2.push(JSON.parse(list1[index]))
      }
      list2.sort(function(a,b){
        if (a.aItem.name < b.aItem.name) {return -1}
        else if (a.aItem.name > b.aItem.name) {return 1}
        return 0
      })
      this.translatedPurchasedItems = list2
    },

    async checkout() {
      this.error = false
      console.log(this.error)
      if (this.orderType === "Pickup") {
        console.log("/editPickupOrderStatus/".concat(sessionStorage.confirmationNumber, "/?newPickupStatus=Ordered"))
        await AXIOS.put("/editPickupOrderStatus/".concat(sessionStorage.confirmationNumber, "/?newPickupStatus=Ordered"))
          .catch(e => {
            console.log("too many orders")
            this.error = true
          })
      } else {
        console.log("/editDeliveryOrderShippingStatus/".concat(sessionStorage.confirmationNumber, "/?newShippingStatus=Ordered"))
        await AXIOS.put("/editDeliveryOrderShippingStatus/".concat(sessionStorage.confirmationNumber, "/?newShippingStatus=Ordered"))
          .catch(e => {
            console.log("too many orders")
            this.error = true
          })
      }
      console.log(this.error)
      await this.getOrder()
    },

    /**
     * gets the current cart and all the purchased items inside
     * or creates one if the customer or employee does not have one
     * @returns {Promise<void>}
     */
    getOrder: async function () {
      console.log("running getOrder")
      let bool = true
      await AXIOS.get('/'.concat(sessionStorage.accountType.toLowerCase(), "_order/", sessionStorage.username), {responseType: "json"})
        .then((response) => {
          this.response = response.data;
          console.log(this.response)
          let purchasedItems = this.response.purchasedItem
          let list = []
          for (const purchasedItem in purchasedItems) {
            let pi = new PurchasedItemDTO(purchasedItems[purchasedItem].item, purchasedItems[purchasedItem].itemQuantity, purchasedItems[purchasedItem].purchasedItemID)
            list.push(JSON.stringify(pi))
          }
          sessionStorage.purchasedItemList = (JSON.stringify(list))
          sessionStorage.price = this.response.totalCost
          this.totalCost = this.response.totalCost
          sessionStorage.confirmationNumber = this.response.confirmationNumber
          sessionStorage.orderType = this.response.orderType
          this.orderType = this.response.orderType
          this.TranslatePurchasedItems()
        })
        .catch(e => {
          bool = false
          console.log("ERROR: going to create order")
        })

      if (bool === false) {
        await AXIOS.post("pickupOrder?username=".concat(sessionStorage.username,"&paymentMethod=Cash&accountType=",sessionStorage.accountType), {})
          .then((response) => {
            this.response = response.data;
            sessionStorage.price = this.response.totalCost
            sessionStorage.confirmationNumber = this.response.confirmationNumber
            sessionStorage.purchasedItemList = ""
            sessionStorage.orderType = "Pickup"
            this.translatedPurchasedItems = []
            this.totalCost = 0
          })
      }
    },

    up: async function (purchasedItemID, counter) {
      console.log("increasing the count of a purchased item")
      let newCount = counter + 1
      let count2 = JSON.stringify(newCount)
      await AXIOS.put("/edit_purchasedItem/".concat(purchasedItemID, "?itemQuantity=", count2))
      await this.getOrder()
      this.TranslatePurchasedItems()
    },

    down: async function (purchasedItemID, counter) {
      console.log("Decreasing the count of a purchased item")
      let newCount = counter - 1
      let count2 = JSON.stringify(newCount)
      await AXIOS.put("/edit_purchasedItem/".concat(purchasedItemID, "?itemQuantity=", newCount))
      await this.getOrder()
      this.TranslatePurchasedItems()
    }
  }
}
