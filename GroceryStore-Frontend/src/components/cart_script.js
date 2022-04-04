
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
      created: {
        TranslatedPurchasedItems() {
          let list1 = JSON.parse(sessionStorage.purchasedItemList)
          let list2 = []
          for (let index in list1) {
            list2.push(JSON.parse(list1[index]))
          }
          console.log(list2)
          this.translatedPurchasedItems = list2
        }
      },

      methods: {

        deleteItem: function (purchasedItemID){
          let index = sessionStorage.purchasedItemList.purchasedItemID.indexOf(purchasedItemID)
          if (index > -1) {
            sessionStorage.purchasedItemList.splice(index, 1); // 2nd parameter means remove one item only
          }
        },
        TranslatePurchasedItems() {
          let list1 = JSON.parse(sessionStorage.purchasedItemList)
          let list2 = []
          for (let index in list1) {
            list2.push(JSON.parse(list1[index]))
          }
          console.log(list2)
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

        up: function (purchasedItemID){
          let objIndex = sessionStorage.items.findIndex((purchasedItem => purchasedItem.aPurchasedItemID === purchasedItemID));
          sessionStorage.items[objIndex].counter += 1
        },

        down: function (purchasedItemID){
          let objIndex = sessionStorage.purchasedItemList.findIndex(purchasedItem => purchasedItem.aPurchasedItemID === purchasedItemID);
          if (sessionStorage.items[objIndex].item.counter > 0){
            sessionStorage.items[objIndex].counter -= 1
          }
        }
      }
}
