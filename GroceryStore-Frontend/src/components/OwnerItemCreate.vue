<template>
  <div>
    <Header />
    <div id ="ItemSearchTextButton" class="col-7">
      <div id="searchRow" class="row-12">
        <div class="row g-3">
          <div class="col">
            <!-- Item name text field !-->
            <input type="text" v-model="searchItemName" class="form-control" placeholder="Search Item" aria-label="Search Item" >
          </div>
          <div class="col">
            <!-- Item search button !-->
            <button type="button" id=search class="btn btn-danger" @click="searchForItem(searchItemName)">Search</button>
            <!-- Item update button !-->
            <button type="button" id=update class="btn btn-danger" @click="updateItem(itemNameO, price, description, purchasable, stock, imageUrl)">Update</button>
            <!-- Item create button !-->
            <button type="button" id=create class="btn btn-danger" @click="createItemOwner(itemNameO, price, description, purchasable, stock)">Create</button>
            <!-- Item view all button !-->
            <button type="button" id=view class="btn btn-danger" @click="viewAll">View All</button>
          </div>
        </div>
      </div>
    </div>
    <div id="itemInfo" class="container">
      <div class="row">
        <div class="col-sm">
          <!-- Item name field !-->
          <input type="text" id="Name" class="form-control" placeholder="Name" v-model="itemNameO">
          <!-- Item description field !-->
          <input type="text" id="Description" class="form-control" placeholder="Description" v-model="description">
          <!-- Item price field !-->
          <input type="text" id="Price" class="form-control" placeholder="Price" v-model="price">
          <!-- Item stock field !-->
          <input type="text" id="Stock" class="form-control" placeholder="Stock" v-model="stock">
          <!-- Item purchasable checkbox !-->
          <input class="form-check-input" type="checkbox" value="" v-model="purchasable" id="Purchasable">
          <label class="form-check-label" for="Purchasable">
            Purchasable
          </label>
        </div>
        <div id="itemFormat" class="col-sm">
          <div class = "container">
            <!-- Item URL field !-->
            <input type="text" id="imageURLField" class="form-control" placeholder="URL" v-model="imageUrl">
            <button type="button" id=addItem class="btn btn-danger" @click="putImage(itemNameO, imageUrl)">Add Image</button>
          </div>
          <!-- Item image !-->
          <img :src=image2Load :alt=itemNameO id="itemPic" class="img-thumbnail">
        </div>
      </div>
    </div>
    <!-- All items !-->
    <div class="card-deck" style="padding: 5%" v-show="visibleViewAll">
      <div class="card" id="card" v-for="item in items" :key=item.name>

        <h5 class="w3-container w3-center" style="color:white">
          <h2>{{item.name.toUpperCase()}}</h2>
          <img :src=item.image :alt=item.name style="width:80%; height: 15vw; object-fit: cover;border-radius: 4%">
          <h5 >{{item.description}}</h5>
          <h5>{{item.price}} $</h5>

          <h5 class="w3-container w3-center">
          </h5>
        </h5>
      </div>
    </div>
    <!-- Hide all items field !-->
    <button type="button" id=hideAll class="btn btn-danger" aligncenter="holidayTable"  v-show="visibleViewAll" @click="hideAll">Hide</button>
    <div align="center">
      <b-alert id="alert" style="max-width: 50%" :show="setAlert()" dismissible variant="danger" @dismissed="setErrorEmpty()">
        {{errorItemOwner}}
      </b-alert>
    </div>

  </div>
</template>

<script src="./ownerItemCreate.js">
import Header from "../components/Header.vue"
export default {
  components: {
    Header
  }
}
</script>

<style>
#card{
  background: #e03444;
  min-width: 30%;
  max-width: 30%;
  border-radius: 10%;
  margin-bottom: 2%;
}
#imageURLField{
  width: 72%;
  margin-bottom: 2%
}
   #ItemSearchTextButton{
    margin-top: 100px;
    margin-left: 500px;
  }
   #addItem{
     margin-left: 25%;
     margin-bottom: 5%;

   }
   #search{
     margin-right: 20px;
   }
   #update{
     margin-right: 20px;
   }
   #create{
     margin-right: 20px;
   }
   #ItemName{
     margin-top: 80px;
     margin-left: 200px;
   }
   #Name{
     margin-bottom: 20px;
     margin-right: 100px;
     width: 50%;
   }
   #Description{
     margin-bottom: 20px;
   }
   #Price{
     margin-bottom: 20px;
   }
   #Stock{
     margin-bottom: 20px;
   }
    #itemPic{
      width: 60%;
      margin-right: 20%
    }
    #itemInfo{
      margin-top: 100px;
    }
    #alert{
      margin-top: 5%;
    }

</style>
