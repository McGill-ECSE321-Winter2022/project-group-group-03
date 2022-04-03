import Header from "./Header";
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ItemDTO(name, purchasable, price, description, stock, totalPurchased, image){
  this.name = name
  this.purchasable = purchasable
  this.price = price
  this.description = description
  this.stock = stock
  this.totalPurchased = totalPurchased
  this.image = image
}

export default{
  itemNameO: 'Name',
  data () {
    return {
      itemNameO: '',
      purchasable: '',
      price: '',
      items: [],
      description: '',
      stock: '',
      totalPurchased: '',
      searchItemName: '',
      imageUrl: '',
      errorItemOwner: ''
    }
  },
  components:{
    Header
  },

  created: function(){
    this.getItems()
  },

  methods:{

    getItems: function(){
      console.log("getting items");
      this.items.length = 0;
      AXIOS.get('/item', {responseType: "json"})
        .then((response) =>{
          this.response = response.data;
          for (const item in this.response) {
            let i = new ItemDTO(this.response[item].name, this.response[item].purchasable, this.response[item].price, this.response[item].stock, this.response[item].description, this.response[item].image, 0);
            this.items.push(i);
            console.log(i);
          }
        });
    },
    searchForItem: function(itemName){
      console.log('getting specific item')
      AXIOS.get('/item/'.concat(itemName),{responseType: "json"})
        .then((response) => {
          this.response = response.data
          this.itemNameO = this.response.name
          this.description = this.response.description
          this.price = this.response.price
          this.stock = this.response.stock
          this.purchasable = this.response.purchasable
          this.image = this.response.image
        })
    },

    createItemOwner: function(name, price, description, purchasable, stock){
      console.log('/item?itemName='.concat(name, '&purchasable=', purchasable ,'&price=', price, '&description=', description,'&stock=', stock))
      AXIOS.post('/item?itemName='.concat(name, '&purchasable=', purchasable ,'&price=', price, '&description=', description,'&stock=', stock))
        .catch(function (error) {
          this.errorItemOwner = error.data();
        })
      this.sleep(500);
      this.items.length = 0;
      this.getItems();

      // Reset the name field for new people
      this.description = ''
      this.price = ''
      this.purchasable = false
      this.stock = ''
      this.image = ''
    },

    putImage: function(name, image){
      AXIOS.put('/editItemImage/'.concat(name, '?image=', image))
        .then((response) => {
          console.log(response)
        })
      this.sleep(500)
      this.image = image
      this.getItems()
      this.sleep(500)
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
    },
}
