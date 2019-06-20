<template>
  <div>this post deleted</div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "DeletePost",
  data () {
    return {}
  },
  components: {
  },   
  props: {
    userId: Number,
    postId: Number
  },
  methods: {
  },
  mounted(){
        //check userId
        // console.log(this.$session.getAll().user.userId);

        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.delete('http://localhost:8080/posts/delete/' + this.userId + '/' + this.postId)
        .then(response => {
            // handle success
            // console.log(response.data);
            this.$router.push({name : 'home'})
          });    
  }
 
};

</script>
