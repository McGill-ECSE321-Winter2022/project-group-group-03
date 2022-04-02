<template>
  <div>
    <Header />
      <div id ="ItemSearchTextButton" class="col-7">
        <div id="searchRow" class="row-12">
          <div class="row g-3">
            <div class="col">
              <input type="text" class="form-control" placeholder="Search Item" aria-label="Search Item">
            </div>
            <div class="col">
              <button type="button" id=search class="btn btn-danger">Search</button>
              <button type="button" id=update class="btn btn-danger">Update</button>
              <button type="button" id=create class="btn btn-danger">Create</button>
              <button type="button" id=delete class="btn btn-danger">Delete</button>
            </div>
          </div>
          </div>
        </div>
    <div id="itemInfo" class="container">
      <div class="row">
        <div class="col-sm">
          <input type="text" id="Name" class="form-control" placeholder="Name" aria-label="Name">
          <input type="text" id="Description" class="form-control" placeholder="Description" aria-label="Description">
          <input type="text" id="Price" class="form-control" placeholder="Price" aria-label="Price">
          <input type="text" id="Stock" class="form-control" placeholder="Stock" aria-label="Stock">
          <input class="form-check-input" type="checkbox" value="" id="Purchasable">
          <label class="form-check-label" for="Purchasable">
            Purchasable
          </label>
        </div>
        <div id="itemFormat" class="col-sm">
          <img id="itemPic" src="https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX5748628.jpg" class="img-thumbnail" alt="https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX5748628.jpg">
        </div>
      </div>
    </div>
      </div>
</template>

<script>
import Header from "../components/Header.vue"
export default {
  components: {
    Header
  }

}
</script>

<style>
   #ItemSearchTextButton{
    margin-top: 100px;
    margin-left: 500px;
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
    }
    #itemInfo{
      margin-top: 100px;
    }
</style>
