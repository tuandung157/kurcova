<template>
  <form >
    <input type="text" placeholder="text" v-model="commentData.text" />
    <input type="text" placeholder="UserId" v-model="userId" />
    <input type="text" placeholder="PostId" v-model="postId" />
    <input type="button"  v-on:click="addComment"/>
  </form>
  
</template>

<script>
// @ is an alias to /src

export default {
  name: "AddComment",
  data () {
    return {
      postId : null,
      userId : null,
      commentData : {
        text: ''
      }
    }
  },
  components: {
  },   
  props: {
  },
  methods: {
    addComment : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.post('http://localhost:8080/comment/'+this.postId+'/'+this.userId,this.commentData)
        .then(response => {
            // handle success
            console.log(response.data);
            this.comments = response.data;
          });
    }
  },
  mounted(){
  }
 
};

</script>
