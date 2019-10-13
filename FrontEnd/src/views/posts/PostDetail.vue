<template>
  <div>
    <div>
    <SinglePostView :postData="post"/>
    </div>
    <div  data-role="panel"
          data-title-caption="some description"
          data-collapsible="true">
          {{post}}</div>

    <AllComment v-if="postId" :postId="postId" />
    <AddComment :postId="postId"/>
  </div>
  <div>
    
  </div>
</template>

<script>
// @ is an alias to /src
import SinglePostView from '@/components/SinglePostView.vue';
import AllComment from '@/views/comments/AllComment.vue';
import AddComment from '@/views/comments/AddComment.vue';
export default {
  name: "postDetail",
  data() {
    return {      
      postId : null,
      post : null
    }
  },
  components: {
    SinglePostView,
    AllComment,
    AddComment
  },   
  props: {
  },
  methods: {
  },
  mounted(){
        const axios = require('axios');
        console.log(Number.parseInt(this.$route.params.id));
        this.postId = Number.parseInt(this.$route.params.id);
        // Make a request for a user with a given ID
        axios.get('http://localhost:8080/posts/'+this.postId)
        .then(response => {
            this.post = response.data;
          });
  }
};

</script>
