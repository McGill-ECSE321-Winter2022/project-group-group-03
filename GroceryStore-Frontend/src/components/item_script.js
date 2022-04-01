import Header from "../components/Header"
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
      response: [],
      out: []
    }
  },
  components: {
    Header
  },
  methods: {
    createItem: function (itemName, itemPrice, description, image) {
      // Create a new person and add it to the list of people
      console.log('/item?'.concat("itemName=",itemName,"&purchasable=true&price=",itemPrice,"&description=", description,"&stock=5"))
      AXIOS.post('/item?'.concat("itemName=",itemName,"&purchasable=true&price=",itemPrice,"&description=", description,"&stock=5"),{},{})
        .catch(function (error) {
        this.errorItem = error.data();
      })
      this.sleep(500);
      this.items.length = 0;
      AXIOS.get('/item',{}).then(response => {
        // console.log(response.data);
        out = response.data;
      })
      console.log(out);


      // let i = new ItemDto(itemName, false, itemPrice, 5, description, image, 0)
      // this.items.push(i)
      // // Reset the name field for new people
      // this.newItem = ''
      // this.newPrice = ''
      // this.newImage = ''
      // this.newDescription = ''
    },
    sleep: function (milliseconds) {
      const date = Date.now();
      let currentDate = null;
      do {
        currentDate = Date.now();
      }
      while (currentDate - date < milliseconds);
    },
    created: function () {
      AXIOS.get('/store',{})
        .then(response => {
        console.log(response.data)
      })
      // AXIOS.get('/item')
      //   .then(response => {
      //     // JSON responses are automatically parsed.
      //     console.log(response.data)
      //   })
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
