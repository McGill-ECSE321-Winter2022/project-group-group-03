<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col>

        <b-card
          title="Meat"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem;"
          class="mb-2"
        >
          <b-card-text>
            meaty
          </b-card-text>

        </b-card>

      </b-col>
<!--      <b-col>-->
<!--        <b-card-->
<!--          title="Fruit"-->
<!--          img-src="https://picsum.photos/600/300/?image=25"-->
<!--          img-alt="Image"-->
<!--          img-top-->
<!--          tag="article"-->
<!--          style="max-width: 20rem;"-->
<!--          class="mb-2"-->
<!--        >-->
<!--          <b-card-text>-->
<!--            fruity-->
<!--          </b-card-text>-->

<!--        </b-card>-->
<!--      </b-col>-->
      <b-col>
        <div class="item-text">
          <b-dropdown id = dropdown-1 style="background-color: #e03444; border-color: #e03444;"  variant="danger" text="Delivery Options" class="m-md-2"> <b-dropdown-item>
            <button id = newbutton1 style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden; " v-b-modal.modal-1>In Town Delivery    </button>
              <b-modal id="modal-1" title="BootstrapVue">
                <p class="my-4">
                  <div id = text>
                Warning! This action is irreversible!
              </div>
                  <b-form-textarea
                    id="textarea-rows"
                    placeholder="Please enter your shipping address"
                    rows="1"
                  ></b-form-textarea>
              </b-modal>
              </b-dropdown-item>
            <b-dropdown-item>
              <div>
                <button id = newbutton2 style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden; " v-b-modal.modal-1>In Town Delivery    </button>
                <b-modal id="modal-2" title="BootstrapVue">
                  <div id = text1>
                    Warning! This action is irreversible!
                  </div>
                  <b-form-textarea
                    id="textarea-rows"
                    placeholder="Please enter your shipping address"
                    rows="1"
                  ></b-form-textarea>

                </b-modal>
              </div>
            </b-dropdown-item>
            <b-dropdown-divider></b-dropdown-divider>
              <b-dropdown-item>
                <button id = newbutton3 style="background-color:Transparent; background-repeat:no-repeat;  border: none; cursor:pointer; overflow: hidden; " v-b-modal.modal-1>In Town Delivery    </button>
                <b-modal id="modal-3" title="BootstrapVue">
                  <p class="my-6">Hello from modal!</p>
                </b-modal>


              </b-dropdown-item>
          </b-dropdown>
        </div>

      </b-col>
    </b-row>

    <b-row>
      <b-col>

        <b-card
          title="Meat"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem;"
          class="mb-2"
        >
          <b-card-text>
            meaty
          </b-card-text>

        </b-card>

      </b-col>
<!--      <b-col>-->
<!--        <b-card-->
<!--          title="Fruit"-->
<!--          img-src="https://picsum.photos/600/300/?image=25"-->
<!--          img-alt="Image"-->
<!--          img-top-->
<!--          tag="article"-->
<!--          style="max-width: 20rem;"-->
<!--          class="mb-2"-->
<!--        >-->
<!--          <b-card-text>-->
<!--            fruity-->
<!--          </b-card-text>-->

<!--        </b-card>-->
<!--      </b-col>-->
      <b-col>
      </b-col>
    </b-row>
    <b-row>
      <b-col>

        <b-card
          title="Meat"
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem;"
          class="mb-2"
        >
          <b-card-text>
            meaty
          </b-card-text>

        </b-card>

      </b-col>
<!--      <b-col>-->
<!--        <b-card-->
<!--          title="Fruit"-->
<!--          img-src="https://picsum.photos/600/300/?image=25"-->
<!--          img-alt="Image"-->
<!--          img-top-->
<!--          tag="article"-->
<!--          style="max-width: 20rem;"-->
<!--          class="mb-2"-->
<!--        >-->
<!--          <b-card-text>-->
<!--            fruity-->
<!--          </b-card-text>-->

<!--        </b-card>-->
<!--      </b-col>-->
      <b-col>
        <div id="total" >
          Sub Total: $40
        </div>

        <b-button id="newbutton" style="color: white; background-color: #e03444; border-color: #e03444;">Buy Now</b-button>

      </b-col>
    </b-row>

  </b-container>

</template>



<script>
export default {
  name: "Checkout"
}
</script>

<style scoped>

#dropdown-1 {
  color: white;
  margin-top: 200px;
  background-color:#e03444;
}
#card{
  background: #e03444;
  min-width: 30%;
  max-width: 30%;
  border-radius: 10%;
  margin-bottom: 2%;
}

</style>

<style scoped>
#total {
  margin-bottom: 10px;
  font-size: 30px;
}
#text {
  margin-bottom: 30px;
}
#text1 {
  margin-bottom: 30px;
}
#newbutton {
  color: black;
}
#newbutton1 {
  color: #e03444;
}
#newbutton2 {
  color: #e03444;
}
#newbutton3 {
  color: #e03444;
}
</style>
