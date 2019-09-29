<template>
        <div class="feed-list">
          <div class="title">All Post</div>
          <div>
            <div class="grid">
              <p class="d-none">{{isAdmin}}</p>

              <button class="button m-1" v-on:click="sortList('post-name','asc')">Price <span class="mif-arrow-up"></span></button>
              <!-- all post created -->
              <ul id="groupCard" 
                  data-role="list"  
                  data-cls-list="unstyled-list row flex-justify-center mt-4"
                  data-cls-list-item="cell-sm-6 cell-md-4"
                  showPagination="true"
                  >
                <li class="cell-sm-6 cell-md-4"  v-for="post in posts" >
                
                  <!-- single post  -->
                  <div>
                  <SinglePostView :postData="post">
                  </SinglePostView>
                  </div>
                  <figure class="text-center">
                  <figcaption class="postName"> {{post.postName}}</figcaption>
                  </figure> 


                  <!-- test post -->




                <!-- isAdmin -->
                  <div class="cell colspan-2 d-flex flex-align-center" v-if="isAdmin" >
                    <!-- button edit -->
                      <router-link :to="{name: 'editPost', params: { userId : post.userId.userId, postData: post}}" class="p-5">
                        <button class="button warning">Edit</button>
                      </router-link>
                    <!-- button delete -->  
                      <router-link :to="{name: 'deletePost', params: { userId : 1, postId: post.postId}}" class="p-5">
                      <button class="button alert">Delete</button>
                    </router-link>
                  </div>


              </li>
              </ul>  

              <div class="row"  v-for="postElement in posts" v-else>
                <div class="cell colspan-12">
                  <SinglePostView :postData="postElement"/>
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
    posts : null,
    checkUserId : null
    }
  },
  components: {
    SinglePostView
  },   
  props: {
    isAdmin: Boolean
  },
  methods: {
    sortList: function (col, dir) {
    $('#paintings').data('list').sorting(col,dir,true);
    // this.$refs.groupCard.data('list').sorting(col,dir,true);
    }
  },
  mounted(){
        const axios = require('axios');
        // Make a request for a user with a given ID
        //axios.get(this.$restBaseUrl+'/posts/')
        axios.get('http://localhost:8080/posts/')
        .then(response => {
            // handle success
            console.log(response.data);
            this.posts = response.data;
            // console.log(this.$session.getAll().user.userId);
            
          });

        this.isAdmin = false;
        if(this.$session.getAll().user.userId == 1){
          this.isAdmin = true;
        }
        else{
          this.isAdmin = false;
        }
  }
 
};
</script>