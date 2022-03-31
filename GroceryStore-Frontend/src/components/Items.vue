<template>
  <div>
    <Header />
    <h2>Items</h2>
    <table>
      <tr v-for="item in items" :key=item.name>
        <td>{{ item.name }}</td>
        <td>{{item.price}}</td>
        <td>{{item.description}}</td>
        <td>{{item.image}}</td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newItem" placeholder="Item Name">
        </td>
        <td>
          <input type="text" v-model="newPrice" placeholder="Item Price">
        </td>
        <td>
          <input type="text" v-model="newDescription" placeholder="Item Description">
        </td>
        <td>
          <input type="text" v-model="newImage" placeholder="Item image url">
        </td>

        <td>
          <button v-bind:disabled="!newItem" @click="createItem(newItem, newPrice, newDescription, newImage)">Create Item</button>
        </td>
      </tr>
    </table>

    <div class="w3-card-4 w3-dark-grey" style="width:30%; height:40%; background: darkgray" v-for="item in items" :key=item.name>

      <div class="w3-container w3-center">
        <h3>{{item.name}}</h3>
        <img :src=item.image alt={{item.name}} style="width:80%">
        <h5>{{item.price}}</h5>

        <button class="w3-button" style="background: green">buy</button>
        <button class="w3-button" style="background:red">don't buy</button>
      </div>

    </div>
    <button @click="deleteItem()">Delete Item</button>
    <span v-if="errorItem" style="color:red">Error: {{errorItem}} </span>
  </div>
</template>

<script src="./thing.js">
import Header from "./Header.vue"
// import items from "./items";
export default {
  components: {
    Header
  }
}
</script>

<style scoped>
#item {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
  background: #f2ece8;
}
</style>
