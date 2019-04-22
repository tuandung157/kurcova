<template>
  <div class="grid home">
    <div class="row flex-justify-center">
      <div class="cell-2">
        <img alt="Vue logo" src="../assets/logo.png" />
      </div>
    </div>
    <div class="row">
      <div class="cell">
        <AllPost isAdmin />
      </div>
    </div>
    <a>AddComment</a>
    <div class="row">
      <div class="cell">
        <AddComment/>
      </div>
    </div>
    <a>AllProject</a>
    <div class="row">
      <div class="cell">
        <AllProject/>
      </div>
    </div>
    <a>AllTask</a>
    <div class="row">
      <div class="cell">
        <AllTask/>
      </div>
    </div>

  </div>
     <!-- <AllPost /> -->
  </div>
</template>

<script>
// @ is an alias to /src
import AllPost from "@/views/posts/AllPost.vue";
import AddPost from "@/views/posts/AddPost.vue";
import AllComment from "@/views/comments/AllComment.vue";
import AddComment from "@/views/comments/AddComment.vue";
import AllProject from "@/views/projects/AllProject.vue";
import AllTask from "@/views/tasks/AllTask.vue";
export default {
  name: "home",
  data () {
  	return {
  	}
  },
  components: {
  AllPost,
  AddPost,
  AllComment,
  AddComment,
  AllProject,
  AllTask
  }, mounted(){
  }
 
};

</script>
