<template>
        <div class="feed-list">
          <div class="title">All Post</div>
          <div>
            <div class="grid">
              <p class="d-none">{{isAdmin}}</p>
              <div>{{this.searchParent}}</div>
              <!-- all post created -->
              <input type="text" v-model= "search" data-history ="true" placeholder="Find the language you want" />

              <!-- search button -->
              <router-link :to="{name: 'postSearch',params:{string :search,searchParent :search}}">
                <button class="primary" v-on:click="getSearchChild">AddToSearchList</button>
              </router-link>

              <!-- check box -->
              <div>
                <!-- java -->
                <input type="checkbox" id="java" data-role="checkbox" data-style="2" v-model="checkbox" value="java">
                <label for="java">java</label>
                <br>

                <!-- html -->
                <input type="checkbox" id="html" data-role="checkbox" data-style="2" v-model="checkbox" value="html">
                <label for="html">html</label>
                <br>

                <!-- css -->
                <input type="checkbox" id="css" data-role="checkbox" data-style="2" v-model="checkbox" value="css">
                <label for="css">css</label>
                <br>

                <!-- js -->
                <input type="checkbox" id="js" data-role="checkbox" data-style="2" v-model="checkbox" value="js">
                <label for="js">js</label>
                <br>

                <span> check box : {{checkbox}}</span>
              </div>

              <ul id="groupCard" 
                  data-role="list"  
                  data-cls-list="unstyled-list row flex-justify-center mt-4"
                  data-cls-list-item="cell-sm-6 cell-md-4"
                  data-sort-class="postName"
                  data-sort-dir="desc"
                  showPagination="true"
                  data-pagination="true"
                  >
                <li class="cell-sm-6 cell-md-4"  v-for="post in posts" >
      
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
            </div>  
          </div>
        </div>
  
</template>

<script>
// @ is an alias to /src
import SinglePostView from '@/components/SinglePostView.vue';
export default {
  name: "postSearch",
  data() {
    return {      
    posts : [],
    checkUserId : null,
    search:'',
    checkbox: [],
    stringCheckbox:'',
    searchChild:''
    
    }
  },
  components: {
    SinglePostView
  },   
  props: {
    isAdmin: Boolean,
    searchParent: '',
    string:''
  },
  methods: {
    arrayToString:function(){
      this.stringCheckbox = " "+this.checkbox.join(" ");
      // console.log(this.stringCheckbox);
    },
    getSearchPost:function(){
      const axios = require('axios');
        // Make a request for a user with a given ID
        //axios.get(this.$restBaseUrl+'/posts/')
        this.search = this.$route.params.string; 
        console.log(this.search);
        console.log('http://localhost:8080/posts/search?search='+this.search);
        
        axios.get('http://localhost:8080/posts/search?search='+this.search)
        .then(response => {
            // handle success
            // console.log(response.data);
            this.posts = response.data;
            // console.log(this.$session.getAll().user.userId);
            
          });
    },
    getSearchChild:function(){
      const axios = require('axios');
        // Make a request for a user with a given ID
        //axios.get(this.$restBaseUrl+'/posts/')
        this.arrayToString();
        console.log("from checkbox"+this.stringCheckbox);
        this.searchChild =this.search.concat(this.stringCheckbox);
        console.log("search child" + this.searchChild)
        console.log('http://localhost:8080/posts/search?search='+this.searchChild);
        
        axios.get('http://localhost:8080/posts/search?search='+this.searchChild)
        .then(response => {
            // handle success
            // console.log(response.data);
            this.posts = response.data;
            // console.log(this.$session.getAll().user.userId);
            
          });
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
        // this.search = console.log(this.$route.params.string);
        this.getSearchPost();

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