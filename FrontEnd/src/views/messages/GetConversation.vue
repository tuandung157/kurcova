<template>
  <ul >
    <li v-for="post in posts">
      <SinglePostView :postData="post" />
      <router-link :to="{name: 'editPost', params: { userId : 1, postData: post}}">Edit</router-link>
      <router-link :to="{name: 'deletePost', params: { userId : 1, postId: post.postId}}">Delete</router-link>
      
    </li>
  </ul>  
  
</template>

<script>
// @ is an alias to /src
import SinglePostView from '@/components/SinglePostView.vue';
export default {
  name: "Posts",
  data() {
    return {      
    posts : null
    }
  },
  components: {
    SinglePostView
  },   
  props: {
    SinglePost: Object

  },
  methods: {

  },
  mounted(){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.get('http://localhost:8080/messages/'+$route.params.id1+'/'+$route.params.id2)
        .then(response => {
            // handle success
            console.log(response.data);
            this.posts = response.data;
          });
  }
 
};

</script>
