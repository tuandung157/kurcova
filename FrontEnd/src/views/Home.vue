<template>
  <div class="grid home">
    <div class="row flex-justify-center">
      <div class="cell-2">
        <a href='https://se.ifmo.ru'>
          <img alt="Vue logo" src="../assets/logo.png" /></a> 
      </div>
    </div>
    <div class="row">
      <div class="cell">
        <AllPost isAdmin />  
      <!-- test pagination         -->
      </div>
      <div>
        
      </div>
    </div>
    <a>end Home Page</a>
  </div>

</template>

<script>
// @ is an alias to /src
import AllPost from "@/views/posts/AllPost.vue";
import AllComment from "@/views/comments/AllComment.vue";
import AddComment from "@/views/comments/AddComment.vue";
import AllProject from "@/views/projects/AllProject.vue";
import AllTask from "@/views/tasks/AllTask.vue";
import PostDetail from "@/views/posts/PostDetail.vue";
export default {
  name: "home",
  data () {
  	return {
  	}
  },
  components: {
  AllPost,
  }, mounted(){
    
  }
 
};
</script>
