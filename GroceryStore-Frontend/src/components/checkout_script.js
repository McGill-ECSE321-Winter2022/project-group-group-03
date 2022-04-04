import Header from "./EmployeeNav"
import Nav from "./OwnerNav"

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
      translatedPurchasedItems: []
    }
  },

  components: {
    Header
  },

  created() {
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
    console.log(list2)
    this.translatedPurchasedItems = list2
  },

  methods: {

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

    /**
     * gets the current cart and all the purchased items inside
     * or creates one if the customer or employee does not have one
     * @returns {Promise<void>}
     */
    getOrder: async function () {
      console.log("running getOrder")
      //console.log('/'.concat(sessionStorage.accountType.toLowerCase(), "_order/", sessionStorage.username))
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
          sessionStorage.confirmationNumber = this.response.confirmationNumber
          sessionStorage.orderType = this.response.orderType
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
