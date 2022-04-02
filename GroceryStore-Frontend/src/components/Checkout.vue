<template>
 <div class="row">
   <div class="col-12" id = "col">
     <tr>
       <td><b>Cart</b></td>
     </tr>
   </div>

   <div class="col-12" id = "col1">
     <tr>
       <td>Meat</td>
       <td>&nbsp 1 &nbsp</td>
     </tr>
     <tr>
       <td>Fruit</td>
       <td>&nbsp 3 &nbsp</td>
     </tr>
     <tr>
       <td>Fruit</td>
       <td>&nbsp 2 &nbsp</td>
     </tr>
     <tr>
     </tr>
   </div>

   <div class="col-12" id = "col2">
     <tr>
       <button id="newbutton" style=background-color:#e03444 type="button">
         Buy Now
       </button>
     </tr>
   </div>

   </div>
</template>

<script>
export default {
  name: "Checkout"
}
</script>

<style scoped>
#col1 {
  margin-top: 30px;
  margin-left: 680px;
  color: red;
  //font-family: courier;
  font-size: 160%;
}
#col2 {
  margin-bottom: 300px;
  margin-top: 20px;
  margin-left: 665px;
  font-size: 160%;
}
#newbutton {
  color: white;
}

#col {
  margin-top: 20px;
    margin-left: 700px;
    color: red;
  //font-family: courier;
    font-size: 160%;
  }
</style>
