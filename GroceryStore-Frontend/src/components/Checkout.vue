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
       <button type="button">-</button>
       <td>&nbsp 3 &nbsp</td>
       <button type="button">+</button>
     </tr>
     <tr>
       <td>Fruit</td>
       <button type="button">-</button>
       <td>1</td>
       <button type="button">+</button>
     </tr>
     <tr>
       <td>Vegetables &nbsp </td>
       <button type="button">-</button>
       <td>9</td>
       <button type="button">+</button>
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

