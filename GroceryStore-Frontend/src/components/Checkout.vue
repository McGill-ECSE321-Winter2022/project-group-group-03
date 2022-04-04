<template>
  <div style="display: flex; min-height: fit-content">

    <b-list-group style="padding: 5%; max-width: 70%; min-height: 90%; flex: 1;">
      <b-list-group-item
        style="min-width: 35%;border-radius: 10%; margin-bottom: 2%; display: flex; background: whitesmoke"
        v-for="Item in translatedPurchasedItems" :key="Item.aItem.name">
        <b-img :src="Item.aItem.image" rounded alt="Rounded image" style=" max-width: 30%; flex:1"></b-img>
        <b-col style="padding-left: 5%">
          <b-row><h3>{{Item.aItem.name}}</h3></b-row>
          <b-row><h6>{{Item.aItem.description}}</h6></b-row>
          <b-row><b-button @click="down(Item.aPurchasedItemID, Item.aItemQuantity)" style="max-height: 5%">-</b-button><h6 style="padding-left: 5%; padding-right: 5%; padding-top: 2%">{{Item.aItemQuantity}}</h6><b-button @click="up(Item.aPurchasedItemID, Item.aItemQuantity)">+</b-button></b-row>
          <b-row><h6 @click="deleteItem(Item.aPurchasedItemID)" style="color: red; padding-top:3%">remove</h6></b-row>
        </b-col>
        <h2><b>{{Item.aItem.price}} $</b></h2>
      </b-list-group-item>
    </b-list-group>

    <div style = "max-width: 20%; flex: 1; padding:5%">
      <div class="item-text">
        <b-dropdown id = dropdown-1 style="background-color: #e03444; border-color: #e03444;"  variant="danger" text="Order Options" class="m-md-2">
          <b-dropdown-text>Do you want it delivered?</b-dropdown-text>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item-button style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden;" v-b-modal.modal-1>
            In Town Delivery
            <b-modal id="modal-1" title="Have your order delivered in-town">
              <div id = text1>
                Warning! This action is irreversible, but shipping is FREE!
              </div>
              <b-form-textarea
                id="textarea-rows"
                placeholder="Please enter your shipping address"
                rows="1"
              ></b-form-textarea>
            </b-modal>
          </b-dropdown-item-button>

          <b-dropdown-item-button style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden;" v-b-modal.modal-2>
          Out of town delivery
            <b-modal id="modal-2" title="Have your order delivered out of town">
              <div id = text1>
                Warning! This action is irreversible!
              </div>
              <b-form-textarea
                id="textarea-rows"
                placeholder="Please enter your shipping address"
                rows="1"
              ></b-form-textarea>
            </b-modal>
          </b-dropdown-item-button>
        </b-dropdown>
      </div>
    </div>
  </div>
</template>



<script src="./checkout_script.js">
</script>

<style scoped>

#dropdown-1 {
  color: white;
  margin-top: 200px;
  background-color:#e03444;
}
#text1 {
  margin-bottom: 30px;
}
</style>
