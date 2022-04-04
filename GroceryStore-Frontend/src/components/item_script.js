import Header from "./EmployeeNav"
import axios from 'axios'
import Cart_script from "./cart_script"
import {type} from "mocha/lib/utils";
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ItemDto (name, purchasable, price, stock, description, image, counter) {
  this.name = name
  this.purchasable = purchasable
  this.price = price
  this.stock = stock
  this.description = description
  this.image = image
  this.counter = counter
}
export default {
  name: 'item',

  data () {
    return {
      items: [],
      newItem: '',
      newPrice: '',
      newDescription:'',
      newImage: '',
      errorItem: '',
      response: '',
      search: '',
      out: []
    }
  },
  components: {
    Header,
    Cart_script
  },
  created() {
    this.getItems();
  },
  computed: {
    filteredItems() {
      return this.items.filter(p => {
        // return true if the product should be visible

        // in this example we just check if the search string
        // is a substring of the product name (case insensitive)
        return p.name.toLowerCase().indexOf(this.search.toLowerCase()) != -1;
      });
    }
    },
  methods: {
    /**
     * creates a purchased item and adds it to cart
     * @param itemName the name of the item that is getting added to cart
     * @returns {Promise<void>} in session storage, creates a double JSON encoded list with all the
     * purchased items in cart, along with the new item in it
     */
    addToCart: async function (itemName) {
      await Cart_script.methods.getOrder()
      console.log("Creating purchased item")
      let objIndex = this.items.findIndex((item => item.name == itemName));
      await AXIOS.post('/purchased_item?item='.concat(this.items[objIndex].item.name, "&aItemQuantity=", this.items[objIndex].item.counter, "&confirmationNumber=", sessionStorage.confirmationNumber,"&orderType=", sessionStorage.orderType))
      await Cart_script.methods.getOrder()
      console.log(sessionStorage)
    },
    getStore: function (){
      AXIOS.get('/store', {responseType: "json"})
        .then((response) =>{
          console.log(response)
          })
    },
    getItems: function(){
      console.log("getting items");
      this.items.length = 0;
      AXIOS.get('/item', {responseType: "json"})
        .then((response) =>{
          this.response = response.data;
          for (const item in this.response) {
            let i = new ItemDto(this.response[item].name, this.response[item].purchasable, this.response[item].price,
              this.response[item].stock, this.response[item].description, this.response[item].image, 0);
            this.items.push( { name: this.response[item].name, item: i} );
            console.log(i);
          }
        });
    },
    createItem: function (itemName, itemPrice, description) {
      // Create a new person and add it to the list of people
      console.log('/item?'.concat("itemName=", itemName, "&purchasable=true&price=", itemPrice, "&description=", description, "&stock=5"))
      AXIOS.post('/item?'.concat("itemName=", itemName, "&purchasable=true&price=", itemPrice, "&description=", description, "&stock=5"), {}, {})
        .catch(function (error) {
          this.errorItem = error.data();
        })
      this.sleep(500);
      this.getItems();

      // Reset the name field for new people
      this.newItem = '';
      this.newPrice = '';
      this.newImage = '';
      this.newDescription = '';
    },
    sleep: function (milliseconds) {
      const date = Date.now();
      let currentDate = null;
      do {
        currentDate = Date.now();
      }
      while (currentDate - date < milliseconds);
    },

    createStore: function (){
      AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
        .then(response => {
          console.log(response.data)
        })

    },
    deleteItem: function (){
      this.items.pop()
    },
    up: function (itemName){
      let objIndex = this.items.findIndex((item => item.name == itemName));
      if (this.items[objIndex].item.counter < this.items[objIndex].item.stock){
        this.items[objIndex].item.counter += 1
      }
    },
    down: function (itemName){
      let objIndex = this.items.findIndex((item => item.name == itemName));
      if (this.items[objIndex].item.counter > 0){
        this.items[objIndex].item.counter -= 1
      }
    }
  }
}
