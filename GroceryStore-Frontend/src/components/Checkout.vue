<template>
  <div>
    <Header v-if="this.loggedIn_employee"/>
    <Cart v-if="this.loggedIn_customer"/>
    <b-alert variant="danger" dismissible :show="error" @dismissed="setError">
      The store is currently too busy to process your order. Please try again later!
    </b-alert>
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

      <div style="max-width: 20%; flex: 1; padding:5%">
        <h4 style="float:left; padding-left: 5%"><b>
          Price: {{totalCost}} $
        </b>
        </h4>

        <b-dropdown id = dropdown-1 style="float: left; background-color: #e03444; border-color: #e03444;"  variant="danger" text="Order Options" class="m-md-2">
          <b-dropdown-text v-if="orderType=== 'Delivery'">Do you want to pick up?</b-dropdown-text>
          <b-dropdown-text v-else>Do you want it delivered?</b-dropdown-text>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item-button @click="cash()" v-if="orderType=== 'Delivery'" style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden;">
            <!--          TODO: add cash event-->
            Cash
          </b-dropdown-item-button>
          <b-dropdown-item-button v-else style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden;" v-b-modal.modal-prevent-closing>
            In Town Delivery
          </b-dropdown-item-button>
          <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="Have your order delivered in-town"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOkIT"
          >
            <form ref="form" @submit.stop.prevent="handleSubmitIT">
              <b-form-group
                label="Address:"
                label-for="address-input"
                invalid-feedback="Address is required"
                :state="addressState"
              >
                <b-form-input
                  id="address-input"
                  v-model="deliveryAddress"
                  :state="addressState"
                  required
                ></b-form-input>
              </b-form-group>
            </form>
          </b-modal>

          <b-dropdown-item-button @click="credit" v-if="orderType=== 'Delivery'" style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden;">
            Credit
          </b-dropdown-item-button>
          <b-dropdown-item-button v-else style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden;" v-b-modal.modal-prevent-closing2>
            Out of Town Delivery
          </b-dropdown-item-button>

          <b-modal
            id="modal-prevent-closing2"
            ref="modal"
            title="Have your order delivered out of town"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOkOOT"
          >
            <form ref="form" @submit.stop.prevent="handleSubmitOOT">
              <b-form-group
                label="Address:"
                label-for="address-input2"
                invalid-feedback="Address is required"
                :state="addressState"
              >
                <b-form-input
                  id="address-input2"
                  v-model="deliveryAddress"
                  :state="addressState"
                  required
                ></b-form-input>
              </b-form-group>
            </form>
          </b-modal>
        </b-dropdown>
        <div>
          <b-button @click="checkout" style="float: left; background-color: #e03444; border-color: #e03444;"  variant="danger"  class="m-md-2">
            Checkout
          </b-button>
        </div>
      </div>
    </div>
    <Footer/>
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

</style>
