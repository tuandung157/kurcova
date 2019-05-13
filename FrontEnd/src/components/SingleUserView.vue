<template>
  <div class="card" v-if="userData">
    <div class="card-header">
      <p>user Name</p>
      <!-- {{userData}} -->
      {{ userData.username }}

    </div>
    <div class="card-content m-5">
      <p>password</p>
      {{ userData.password }}

    </div>
    <div class="card-content m-5">
      <p>userId:</p>
      {{ userData.userId }}

    </div>
    <div class="card-content m-5">
      <p>email:</p>
      {{ userData.email }}

    </div>    
    <div class="card-content m-5">
      <p class="">telephone:</p>
      {{ userData.telephone }}

    </div>      
    <div class="card-content m-5">
      <p>some description by yourself</p>
      {{ userData.text }}

    </div>     
    <div class="card-footer">

      <p class="fg-gray mb-4">Joined in {{ userData.createdAt }}</p>
    </div>
<!--     <div class="card-footer">
      Footer
    </div> -->
    
</div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "SingleUserView",
  data () {
    return {
    }
  },
  components: {
  },   
  props: {
       userData: Object
  },
  mounted(){
  }
 
};

</script>
