<template xmlns:text-transform="http://www.w3.org/1999/xhtml" >
  <div style="overflow-x:hidden">
    <!-- imports the navbar at the top of the page -->
    <Cart/>
    <br>
    <br>
    <div class="search-wrapper panel-heading col-sm-12">
      <!-- interactive search bar that lets users only see items they have searched for -->
      <input style="width: 40%" type="text" v-model="search" placeholder="Search" /> <br> <br>
    </div>
    <!-- card-deck with each item being a different card -->
    <div class="card-deck" style="padding: 5%">
      <div class="card" id="card" v-for="item in filteredItems" :key=item.name>

        <h5 class="w3-container w3-center" style="color:white">
          <h2>{{item.item.name.toUpperCase()}}</h2>
          <img :src="item.item.image" :alt="item.name" style="width:80%; height: 15vw; object-fit: cover;border-radius: 4%">
          <h5 >{{item.item.description}}</h5>
          <h5>{{item.item.price}} $</h5>

          <h5 class="w3-container w3-center">
            <!-- incrementor and decrementor to set the quantity of the item  -->
            <button class="w3-button" @click="down(item.item.name)" style="background: white; margin-right: 5%">-</button>
            {{item.item.counter}}
            <button class="w3-button" @click="up(item.item.name)" style="background:white; margin-left: 5%">+</button>
          </h5>
          <!-- add to cart button that is disabled if the item is not purchasable online-->
          <button class="button" v-bind:disabled="!item.item.purchasable" @click="addToCart(item.item.name)" style="background:white">Add to Cart</button>
        </h5>
      </div>
    </div>

    <span v-if="errorItem" style="color:red">Error: {{errorItem}} </span>
    <Footer/>
  </div>
</template>

<script src="./item_script.js">
</script>

<style scoped>
#card{
  background: #e03444;
  min-width: 30%;
  max-width: 30%;
  border-radius: 10%;
  margin-bottom: 2%;
}

</style>
