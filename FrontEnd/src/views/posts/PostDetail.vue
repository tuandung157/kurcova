<template>
  <div>
    <SinglePostView :postData="post"/>
    <AllComment v-if="postId" :postId="postId" />
  </div>
</template>

<script>
// @ is an alias to /src
import SinglePostView from '@/components/SinglePostView.vue';
import AllComment from '@/views/comments/AllComment.vue'
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
    AllComment
  },   
  props: {
   
  },
  methods: {

  },
  mounted(){
        const axios = require('axios');
        this.postId = Number.parseInt(this.$route.params.id);
        // Make a request for a user with a given ID
        axios.get('http://localhost:8080/posts/'+this.postId)
        .then(response => {
            this.post = response.data;
          });
  }
};

</script>
