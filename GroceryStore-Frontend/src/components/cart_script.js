
import Header from "./EmployeeNav"
import Nav from "./OwnerNav"
import Login from "./Login_Script"

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
        return {
          price: 0,
          purchasedItemList: [],
        }
      },
      components: {
        Header
      },

      methods: {
        getItems: function () {
          console.log("getting items");
          this.items.length = 0;
          if (Login.login_accountType === "Customer") {
            AXIOS.get('/item'.concat(), {responseType: "json"})
              .then((response) => {
                this.response = response.data;
                for (const item in this.response) {
                  let i = new PurchasedItemDTO(this.response[purchasedItem].aItem, this.response[purchasedItem].aItemQuantity, this.response[purchasedItem].aPurchasedItemID);
                  this.items.push({name: this.response[item].name, item: i});
                  console.log(i);
                }
              });
          }
          if (Login.login_accountType === "Employee") {
            AXIOS.get('/item'.concat(), {responseType: "json"})
              .then((response) => {
                this.response = response.data;
                for (const item in this.response) {
                  let i = new PurchasedItemDTO(this.response[purchasedItem].aItem, this.response[purchasedItem].aItemQuantity, this.response[purchasedItem].aPurchasedItemID);
                  this.items.push({name: this.response[item].name, item: i});
                  console.log(i);
                }
              });
          }
        },

        //TODO: else throws error
        deleteItem: function (purchasedItemID){
          let index = this.purchasedItemList.purchasedItemID.indexOf(purchasedItemID)
          if (index > -1) {
            this.purchasedItemList.splice(index, 1); // 2nd parameter means remove one item only
          }
        },

        getOrder: function () {
          AXIOS.get('/'.concat(Login.login_accountType, "_order/", Login.login_username), {responseType: "json"})
            .then((response) => {
              this.response = response.data;
              let list = this.response.purchasedItem
              for (const purchasedItem in list) {
                this.purchasedItemList.push(new PurchasedItemDTO(purchasedItem.item, purchasedItem.aItemQuantity, purchasedItem.aPurchasedItemID))
              }
              this.price = this.response.totalCost
              }
            )
        },

        up: function (purchasedItemID){
          let objIndex = this.items.findIndex((purchasedItem => purchasedItem.aPurchasedItemID === purchasedItemID));
            this.items[objIndex].counter += 1
        },

        down: function (purchasedItemID){
          let objIndex = this.purchasedItemList.findIndex(purchasedItem => purchasedItem.aPurchasedItemID === purchasedItemID);
            this.items[objIndex].counter -= 1
          }

      }
}
