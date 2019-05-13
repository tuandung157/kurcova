<template>
  <form >
    <input type="text" placeholder="postName" v-model="postData.postName" />
    <input type="text" placeholder="UserId" v-model="userId" />
    <input type="text" placeholder="content" v-model="postData.content" />
    <input type="text" placeholder="title" v-model="postData.title" />
    <input type="button" value="accept" v-on:click="editPost"/>
  </form>
  
</template>

<script>
// @ is an alias to /src

export default {
  name: "EditPost",
  data () {
    return {}
  },
  components: {
  },   
  props: {
    userId: Number,
    postData: Object
  },
  methods: {
    editPost : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.put('http://localhost:8080/posts/edit',this.postData)
        .then(response => {
            // handle success
            console.log(response.data);
            this.posts = response.data;
          });
        this.$router.push({name: 'home'});


    }
  },
  mounted(){
  }
 
};

</script>
