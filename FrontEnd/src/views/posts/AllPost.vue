<template>
        <div class="feed-list">
          <div class="title">All Post</div>
          <div>
            <div class="grid">
              <p class="d-none">{{isAdmin}}</p>
              <div class="row"  v-for="post in posts" v-if="isAdmin">
                <div class="cell colspan-10">
                  <SinglePostView :postData="post"/>
                </div>
                <div class="cell colspan-2 d-flex flex-align-center">
                    <router-link :to="{name: 'editPost', params: { userId : 1, postData: post}}" class="p-5">Edit</router-link>
                    <router-link :to="{name: 'deletePost', params: { userId : 1, postId: post.postId}}" class="p-5">Delete</router-link>
                </div>
              </div>

              <div class="row"  v-for="post1 in posts" v-else>
                <div class="cell colspan-12">
                  <SinglePostView :postData="post1"/>
                </div>
              </div>
            </div>  
          </div>
        </div>
  
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
    isAdmin: Boolean

  },
  methods: {

  },
  mounted(){
        const axios = require('axios');
        // Make a request for a user with a given ID
        //axios.get(this.$restBaseUrl+'/posts/')
        axios.get('http://localhost:8080/posts/')
        .then(response => {
            // handle success
            // console.log(response.data);
            this.posts = response.data;
          });
  }
 
};

</script>
