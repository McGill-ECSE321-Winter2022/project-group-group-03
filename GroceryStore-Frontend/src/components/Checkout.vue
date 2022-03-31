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
       <td>&nbsp 3 &nbsp</td>
     </tr>
   <tr>
       <td>Fruit</td>
       <td>1</td>
     </tr>
   </div>
     <tr>
       <td>Vegetables &nbsp </td>
       <td>9</td>
     </tr>

   </div>
</template>

<script>
export default {
  name: "Checkout"
}
</script>

<style scoped>
#col1 {
  margin-bottom: 200px;
  margin-left: 550px;
  color: red;
  //font-family: courier;
  font-size: 160%;
}
#col {
    margin-left: 700px;
    color: red;
  //font-family: courier;
    font-size: 160%;
  }
</style>
