<template>
  <div class="card" v-if="postData">
    <div class="card-header">
      {{ postData.title }}
    </div>
    <div class="card-content m-5">
      <p class="fg-gray mb-4">Posted on {{ postData.createdAt }}</p>
      {{ postData.content }}
    </div>
<!--     <div class="card-footer">
      Footer
    </div> -->
    
</div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "SinglePostView",
  data () {
    return {
    }
  },
  components: {
  },   
  props: {
       postData: Object
  },
  mounted(){
  }
 
};

</script>
