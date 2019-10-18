<template>
  <form >
    <div class="from-group">
      <label>ur comment</label>
      <input type="text" placeholder="text" v-model="commentData.content" />
    </div>
    <button class="button primary"  v-on:click="addComment">add comment</button>
  </form>
  
</template>

<script>
// @ is an alias to /src

export default {
  name: "AddComment",
  data () {
    return {
      commentData : {
        content: ''
      }
    }
  },
  components: {
  },   
  props: {
    postId :Number
  },
  methods: {
    addComment : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.post('http://localhost:8080/comments/' + this.postId + '/' + this.userId,this.commentData)
        .then(response => {
            // handle success
            console.log(response.data);
            this.comments = response.data;
            this.commentData.content ="";
          });
    }
  },
  mounted(){
    if(!this.$session.exists() || !this.$session.has('user')) this.$router.push({name: 'login'});

    var user = this.$session.get('user');
    if(user==null) this.userId = null;
    else{
      this.userId = user.userId;
    }

  }
 
};

</script>
