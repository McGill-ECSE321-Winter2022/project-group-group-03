function ItemDto (name, purchasable, price, description, image) {
  this.name = name
  this.purchasable = purchasable
  this.price = price
  this.description = description
  this.image = image
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
      response: []
    }
  },
  created: function () {
    // Test data
    const p1 = new ItemDto('Apple', 'true', '15', 'yummy', 'https://i5.walmartimages.ca/images/Enlarge/094/514/6000200094514.jpg')
    const p2 = new ItemDto('Milk', 'false', '16', 'your moms','https://solidstarts.com/wp-content/uploads/Milk-for-Babies-scaled.jpg')
    // Sample initial content
    this.persons = [p1, p2]
  },
  methods: {
    createItem: function (itemName, itemPrice, description, image) {
      // Create a new person and add it to the list of people
      let i = new ItemDto(itemName, "true", itemPrice, description, image)
      this.items.push(i)
      // Reset the name field for new people
      this.newItem = ''
      this.newPrice = ''
      this.newImage = ''
      this.newDescription = ''
    },
    deleteItem: function (){
      this.items.pop()
    }
  }
}
