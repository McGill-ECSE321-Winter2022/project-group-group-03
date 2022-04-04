import Header from "./OwnerNav";
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
      errorItemOwner: '',
      image2Load:'',
      visibleViewAll: false
    }
  },
  components:{
    Header
  },

  created: function(){
    //this.createStore()
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
            let i = new ItemDTO(this.response[item].name, this.response[item].purchasable, this.response[item].price,  this.response[item].description, this.response[item].stock, 0, this.response[item].image);
            this.items.push(i);
            console.log(i);
          }
        })
        .catch(e => {
          this.errorItemOwner = e.response.data;
        })
    },
    viewAll: function(){
      this.getItems()
      this.visibleViewAll = true
    },

    hideAll: function(){
      this.visibleViewAll = false
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
          this.imageUrl = this.response.image
          this.image2Load = this.response.image
        })
        .catch(e => {
          this.errorItemOwner = e.response.data;
        })
    },

    createItemOwner: function(name, price, description, purchasable, stock){
      console.log('/item?itemName='.concat(name, '&purchasable=', purchasable ,'&price=', price, '&description=', description,'&stock=', stock))
      AXIOS.post('/item?itemName='.concat(name, '&purchasable=', purchasable ,'&price=', price, '&description=', description,'&stock=', stock))
        .catch(e => {
          this.errorItemOwner = e.response.data;
        })
      this.sleep(500);
      this.items.length = 0;
      this.getItems();

      // Reset the name field for new people
      this.itemNameO = ''
      this.description = ''
      this.price = ''
      this.purchasable = false
      this.stock = ''
      this.imageUrl = ''
      this.image2Load = ''
    },

    updateItem: function(name, price, description, purchasable, stock, URL){
      AXIOS.put('/editItem/'.concat(name,'?newImage=',URL,'&newPrice=',price,'&newStock=',stock,'&newDescription=',description,'&newPurchasable=',purchasable))
        .catch(function (error) {
          this.errorItemOwner = error.data();
        })
        .catch(e => {
          this.errorItemOwner = e.response.data;
        })
      this.itemNameO = ''
      this.description = ''
      this.price = ''
      this.purchasable = false
      this.stock = ''
      this.imageUrl = ''
      this.image2Load = ''
    },

    // deleteItem: function(name){
    //   AXIOS.delete
    // }
    putImage: function(name, image){
      AXIOS.put('/editItemImage/'.concat(name, '?image=', image))
        .then((response) => {
          console.log(response)
          this.imageUrl = ''
          this.itemNameO = ''
          this.description = ''
          this.stock = ''
          this.price = ''
          this.purchasable = ''
          this.image2Load = ''
        })
        .catch(e => {
          this.errorItemOwner = e.response.data;
        })
      this.sleep(500)
      this.image = image
      this.getItems()
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
        .catch(e => {
          this.errorItemOwner = e.response.data;
        })
    },
    setAlert: function () {
      return this.errorItemOwner !== ""
    },

    setErrorEmpty(){
      this.errorItemOwner = ""
    }
    }
}
