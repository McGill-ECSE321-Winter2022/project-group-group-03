<template>
  <div id ="app">
    <b-navbar toggleable="lg" type="dark" variant="danger">
      <b-button id ="sideBarButton" size="sm" class="my-2 my-sm-0" variant="danger" v-b-toggle.sidebar-1><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
      </svg></b-button>
      <b-navbar-brand><img data-attachment-id="2182" data-permalink="https://ahistoryofitaly.com/donate/shop/" data-orig-file="https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?fit=512%2C512&amp;ssl=1" data-orig-size="512,512" data-comments-opened="1" data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;,&quot;orientation&quot;:&quot;0&quot;}" data-image-title="shop" data-image-description="" data-image-caption="" data-medium-file="https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?fit=512%2C512&amp;ssl=1" data-large-file="https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?fit=512%2C512&amp;ssl=1" loading="eager" src="https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?resize=180%2C180&amp;ssl=1" alt="" class="wp-image-2182 jetpack-lazy-image jetpack-lazy-image--handled" width="50" height="50" data-recalc-dims="1" srcset="https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?w=512&amp;ssl=1 512w, https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?resize=150%2C150&amp;ssl=1 150w, https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?resize=400%2C400&amp;ssl=1 400w, https://i0.wp.com/ahistoryofitaly.com/wp-content/uploads/2021/09/shop.png?resize=200%2C200&amp;ssl=1 200w" data-lazy-loaded="1" sizes="(max-width: 50px) 100vw, 50px"></b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="text-center">
          <b-nav-item class = "itemNav">Home</b-nav-item>
          <b-nav-item class = "itemNav">Profile</b-nav-item>
          <b-nav-item class = "itemNav">Checkout</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <b-nav-item class = "itemNav"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
              <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg></b-nav-item>
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <div>
      <b-sidebar id="sidebar-1" title="Sidebar" shadow>
        <div class="px-3 py-2">
          <p>
            Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
            in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
          </p>
          <b-img src="https://picsum.photos/500/500/?image=54" fluid thumbnail></b-img>
        </div>
      </b-sidebar>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#sideBarButton{
  border: solid transparent;
  color: white;
  margin-right: 10px;
  box-shadow: none!important;
  elevation: 0;
  transition-duration: 0.4s;
  cursor: pointer;
}
#sideBarButton:hover{
  background-color: red;
  color: black;
}
.itemNav{
  margin: 0px 150px;
  font-size: 25px;
  color: white;
}
</style>
