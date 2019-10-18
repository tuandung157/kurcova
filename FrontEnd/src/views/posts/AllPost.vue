<template>
        <div class="feed-list">
          <div class="title">All Post</div>
          <div>
            <div class="grid">
              <!-- <div>{{this.search}}</div> -->
              <p class="d-none">{{isAdmin}}</p>
              <input type="text" v-model= "search" data-role="input" data-history ="true" placeholder="Find the language you want" />

              <!-- button search -->
              <router-link :to="{name: 'postSearch',params:{string :search,searchParent :search}}">
                <button class="primary">search</button>
              </router-link>

              <!-- all post created -->
              <ul id="groupCard" 
                  data-role="list"  
                  data-cls-list="unstyled-list row flex-justify-center mt-4"
                  data-cls-list-item="cell-sm-6 cell-md-4"
                  data-sort-class="postName"
                  data-sort-dir="desc"
                  showPagination="true"
                  data-pagination="true"
                  >
                <li class="cell-sm-6 cell-md-4"  v-for="post in filteredPosts" >
  
                  <!-- single post -->
                  <SinglePostView :postData="post">
                  </SinglePostView>

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
              <!-- <div class="row"  v-for="postElement in posts" v-else>
                <div class="cell colspan-12">
                  <SinglePostView :postData="postElement"/>
                </div>
              </div> -->

              <!-- pagination page -->


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
    posts : [],
    checkUserId : null,
    search:''
    }
  },
  components: {
    SinglePostView
  },   
  props: {
    isAdmin: Boolean
  },
  methods: {
    getAllPost: function(){
      const axios = require('axios');
      axios.get('http://localhost:8080/posts/')
        .then(response => {
            // handle success
            // console.log(response.data);
            this.posts = response.data;
            // console.log(this.$session.getAll().user.userId);
            
          });
    },
    intervalData:function(){
      setInterval(() => {
        this.getAllPost();
      },1000);
    }
  },
  computed: {
    filteredPosts:function(){
      return this.posts.filter((post) =>{
        return post.title.match(this.search)

      })
    }
  },
  mounted(){
        // const axios = require('axios');
        // Make a request for a user with a given ID
        //axios.get(this.$restBaseUrl+'/posts/')
        /*
        axios.get('http://localhost:8080/posts/')
        .then(response => {
            // handle success
            // console.log(response.data);
            this.posts = response.data;
            // console.log(this.$session.getAll().user.userId);
            
          });*/
        this.getAllPost();
        this.intervalData();
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