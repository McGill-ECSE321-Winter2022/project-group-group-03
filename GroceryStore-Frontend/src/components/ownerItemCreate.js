import Header from "./Cart"
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
  pageName: 'ownerItem',
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
  /**
   * Refreshes items list and places them into a list upon refreshing
   */
  created: function(){
    //this.createStore()
    this.getItems()
  },

  methods:{
    /**
     * Retrieves all items and places them into a list
     */
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
    /**
     * Option to view all items
     */
    viewAll: function(){
      this.getItems()
      this.visibleViewAll = true
    },
    /**
     * Option to hide all items
     */
    hideAll: function(){
      this.visibleViewAll = false
    },
    /**
     * Retrieves specific item with a given name.
     * @param {String} itemName - name of the item we are trying to search
     * @throws {NotFoundError} When the item is not found.
     */
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
    /**
     * Creates specific item with given attributes.
     * @param {String} itemName
     * @param {int} price
     * @param {String} description
     * @param {Boolean} purchasable
     * @param {int} stock
     * @throws {IllegalArgumentException} When the item attributes aren't valid.
     */
    createItemOwner: function(name, price, description, purchasable, stock){

      if(name.trim().length != 0 && price.trim().length != 0 && description.trim().length != 0 && stock.trim().length != 0){
        console.log("HERE")
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
      }
      else{
        if(name.trim().length == 0) this.errorItemOwner = "Name can't be empty."
        else if(description.trim().length == 0) this.errorItemOwner = "Description can't be empty."
        else if(price.trim().length == 0) this.errorItemOwner = "Price field empty"
        else if(stock.trim().length == 0) this.errorItemOwner = "Stock field empty"
        this.itemNameO = ''
        this.description = ''
        this.price = ''
        this.purchasable = false
        this.stock = ''
        this.imageUrl = ''
        this.image2Load = ''
      }

    },
    /**
     * Updates specific item with given attributes.
     * @param {String} name
     * @param {int} price
     * @param {String} description
     * @param {Boolean} purchasable
     * @param {int} stock
     * @param {String} URL
     * @throws {IllegalArgumentException} When the item attributes aren't valid.
     */
    updateItem: function(name, price, description, purchasable, stock, URL){
      console.log(name.trim().length)
      console.log(description.trim().length)
      console.log(price)
      console.log(stock)
      if(name.trim().length != 0 && price.toString() !== "" && description.trim().length != 0 && stock.toString() !== "") {
        AXIOS.put('/editItem/'.concat(name, '?newImage=', URL, '&newPrice=', price, '&newStock=', stock, '&newDescription=', description, '&newPurchasable=', purchasable))
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
      }
      else{
        if(name.trim().length == 0) this.errorItemOwner = "Name can't be empty."
        else if(description.trim().length == 0) this.errorItemOwner = "Description can't be empty."
        else if(price.toString() == "") this.errorItemOwner = "Price field empty"
        else if(stock.toString() == "") this.errorItemOwner = "Stock field empty"
        this.itemNameO = ''
        this.description = ''
        this.price = ''
        this.purchasable = false
        this.stock = ''
        this.imageUrl = ''
        this.image2Load = ''
      }
    },

    /**
     * Adds specific URL to an item.
     * @param {String} URL
     * @throws {IllegalArgumentException} When the item attribute isn't valid.
     */
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
    /**
     * Pauses the program for a given amount of milliseconds
     * @param {int} milliseconds - milliseconds for which the program will be paused
     */
    sleep: function (milliseconds) {
      const date = Date.now();
      let currentDate = null;
      do {
        currentDate = Date.now();
      }
      while (currentDate - date < milliseconds);
    },

    // createStore: function (){
    //   AXIOS.post("./store?aAddress=Sherbrooke&aCurrentActiveDelivery=2&aCurrentActivePickup=3",{},{})
    //     .then(response => {
    //       console.log(response.data)
    //     })
    //     .catch(e => {
    //       this.errorItemOwner = e.response.data;
    //     })
    // },
    /**
     * Returns status of error message
     */
    setAlert: function () {
      return this.errorItemOwner !== ""
    },
    /**
     * Resets error status by setting the error message to an empty string
     */
    setErrorEmpty(){
      this.errorItemOwner = ""
    }
    }
}
