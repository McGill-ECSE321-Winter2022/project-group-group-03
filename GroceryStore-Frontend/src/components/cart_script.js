
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
      },
      components: {
        Header
      },

      methods: {
        getItems: function () {
          console.log("getting items");
          let array = [];
          array.push({name: "hi", item: "bye"})
          array.push({name: "h2", item: "bye2"})
          sessionStorage.setItem("items", JSON.stringify(array))
          console.log(sessionStorage)
          let str = sessionStorage.getItem("items")
          str.push({name: "h3", item: "bye3"})
          console.log(str)
          sessionStorage.setItem("items", JSON.stringify(str))
          console.log(sessionStorage)
          if (sessionStorage.accountType === "Customer") {
            AXIOS.get('/item'.concat(), {responseType: "json"})
              .then((response) => {
                this.response = response.data;
                for (const item in this.response) {
                  let i = new PurchasedItemDTO(this.response[item].aItem, this.response[item].aItemQuantity, this.response[item].aPurchasedItemID);
                  sessionStorage.items.concat({name: this.response[item].name, item: i});
                  console.log(i);
                }

              });
          }
          //TODO: add to concat
          if (sessionStorage.accountType === "Employee") {
            AXIOS.get('/item'.concat(), {responseType: "json"})
              .then((response) => {
                this.response = response.data;
                for (const item in this.response) {
                  let i = new PurchasedItemDTO(this.response[purchasedItem].aItem, this.response[purchasedItem].aItemQuantity, this.response[purchasedItem].aPurchasedItemID);
                  sessionStorage.items.push({name: this.response[item].name, item: i});
                  console.log(i);
                }
              });
          }

        },
        deleteItem: function (purchasedItemID){
          let index = sessionStorage.purchasedItemList.purchasedItemID.indexOf(purchasedItemID)
          if (index > -1) {
            sessionStorage.purchasedItemList.splice(index, 1); // 2nd parameter means remove one item only
          }
        },

        getOrder: async function () {
          console.log('/'.concat(sessionStorage.accountType.toLowerCase(), "_order/", sessionStorage.username))
          let bool = true
          await AXIOS.get('/'.concat(sessionStorage.accountType.toLowerCase(), "_order/", sessionStorage.username), {responseType: "json"})
            .then((response) => {
              this.response = response.data;
              console.log(this.response)
              let list = this.response.purchasedItem
              for (const purchasedItem in list) {
                sessionStorage.purchasedItemList.push(new PurchasedItemDTO(purchasedItem.item, purchasedItem.aItemQuantity, purchasedItem.aPurchasedItemID))
              }
              sessionStorage.price = this.response.totalCost
              sessionStorage.confirmationNumber = this.response.confirmationNumber
              sessionStorage.orderType = this.response.orderType
              console.log(sessionStorage)
              console.log('guac')
            })
            .catch(e => {
              bool = false
              console.log("ERROR")
            })

          if (bool === false) {
            await AXIOS.post("pickupOrder?username=".concat(sessionStorage.username,"&paymentMethod=Cash&accountType=",sessionStorage.accountType), {})
              .then((response) => {
                this.response = response.data;
                console.log(this.response)
                sessionStorage.price = this.response.totalCost
                sessionStorage.confirmationNumber = this.response.confirmationNumber
                sessionStorage.purchasedItemsList = [69]
                sessionStorage.orderType = "Pickup"
                console.log("hyiallo")
                console.log(sessionStorage)
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
