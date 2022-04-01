import Header from "./Header"
import axios from 'axios'
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
      out: []
    }
  },
  components: {
    Header
  },
  created() {
    console.log("if this is mounted ill see this");
    AXIOS.get('/store',{})
      .then(response => {
        console.log(response.data)
      })
    this.getItems();
    this.sleep(5000);
  },
  methods: {
    getItems: function(){
      console.log("getting items");
      this.items.length = 0;
      AXIOS.get('/item', {responseType: "json"})
        .then((response) =>{
          this.response = response.data;
          for (const item in this.response) {
            let i = new ItemDto(this.response[item].name, this.response[item].purchasable, this.response[item].price,
              this.response[item].stock, this.response[item].description, this.response[item].image, 0);
            this.items.push(i);
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
      this.items.length = 0;
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
      if (this.items[objIndex].counter < this.items[objIndex].stock){
        this.items[objIndex].counter += 1
      }
    },
    down: function (itemName){
      let objIndex = this.items.findIndex((item => item.name == itemName));
      if (this.items[objIndex].counter > 0){
        this.items[objIndex].counter -= 1
      }
    }
  }
}
