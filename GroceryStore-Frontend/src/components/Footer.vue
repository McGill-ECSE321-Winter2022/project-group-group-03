<template>


  <div class="d-flex flex-column min-vh-100">
    <nav>
    </nav>
    <main class="flex-fill">
    </main>
    <footer id="footer">
      <b-container class="bv-example-row">
        <b-row>

          <b-col style="color:white">Monday
            <br> {{hours[0].startTime}} - {{hours[0].endTime}}
          </b-col>
          <b-col style="color:white">Tuesday
            <br> {{hours[1].startTime}} - {{hours[1].endTime}}
          </b-col>
          <b-col style="color:white">Wednesday
            <br> {{hours[2].startTime}} - {{hours[2].endTime}}
          </b-col>
          <b-col style="color:white">Thursday
            <br> {{hours[3].startTime}} - {{hours[3].endTime}} </b-col>
          <b-col style="color:white">Friday
            <br> {{hours[4].startTime}} - {{hours[4].endTime}}</b-col>
          <b-col style="color:white">Saturday
            <br> {{hours[5].startTime}} - {{hours[5].endTime}}</b-col>
          <b-col style="color:white">Sunday
            <br> {{hours[6].startTime}} - {{hours[6].endTime}}</b-col>

        </b-row>
      </b-container>
    </footer>
  </div>

</template>


<script src="./businesshours.js">

</script>

<style>
#footer{
  background-color: red;
}
</style>
