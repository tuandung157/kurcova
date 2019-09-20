<template>
  <form >
    <div class="form-group">
        <label>Post name</label>
        <input type="text" placeholder="post Name" v-model="postData.postName" />
    </div>
    <div class="form-group">
        <label>Post title</label>
        <input type="text" placeholder="title" v-model="postData.title" />
    </div>
    <div class="form-group">
        <label>Content</label>
        <textarea data-role="textarea" placeholder="content" v-model="postData.content"></textarea>
    </div>
    <div class="d-flex flex-justify-end pt-5">
        <button class="button primary" v-on:click="addPost">Add post</button>

    </div>
<!--     <div class="d-flex flex-justify-end pt-6">
        <router-link to="home">Home</router-link>
    </div> -->
    <!-- <input type="text" placeholder="UserId" v-model="userId" /> -->
  </form>
</template>

<script>
// @ is an alias to /src

export default {
  name: "AddPost",
  data () {
    return {
      userId : null,
      postData : {
        postName: '',
        content: '',
        title: ''
      }
    }
  },
  components: {
  },   
  mounted() {
    if(!this.$session.exists() || !this.$session.has('user')) this.$router.push({name: 'login'});

    var user = this.$session.get('user');
    if(user==null) this.userId = null;
    else{
      this.userId = user.userId;
    }
  },
  props: {
  },
  methods: {
    addPost : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        if(this.userId == null){
          console.log("not logged in");
        }else{
        axios.post('http://localhost:8080/posts/' + this.userId, this.postData)
          .then(response => {
            // handle success
            console.log(response.data);
            this.posts = response.data;
          });
        }
    this.$router.push({name: 'home'});
    // this.$router.go();
    }
  }
 
};

</script>
