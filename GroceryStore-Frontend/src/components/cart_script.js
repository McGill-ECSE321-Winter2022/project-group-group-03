
import Header from "./EmployeeNav"
import Nav from "./OwnerNav"
import Login from "./login_script"

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
          sessionStorage.items.length = 0;
          if (Login.login_accountType === "Customer") {
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
          //TODO: add to concat
          if (Login.login_accountType === "Employee") {
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

        getOrder: function () {
          console.log(sessionStorage)
          console.log('/'.concat(sessionStorage.accountType.toLowerCase(), "_order/", sessionStorage.username))
          AXIOS.get('/'.concat(sessionStorage.accountType.toLowerCase(), "_order/", sessionStorage.username), {responseType: "json"})
            .then((response) => {
              this.response = response.data;
              let list = this.response.purchasedItem
              for (const purchasedItem in list) {
                sessionStorage.purchasedItemList.push(new PurchasedItemDTO(purchasedItem.item, purchasedItem.aItemQuantity, purchasedItem.aPurchasedItemID))
              }
              sessionStorage.price = this.response.totalCost
              sessionStorage.confirmationNumber = this.response.confirmationNumber
              }
            )
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
