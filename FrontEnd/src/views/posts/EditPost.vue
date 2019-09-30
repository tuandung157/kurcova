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
        <button class="button primary" value="accept" v-on:click="editPost">Edit post</button>

    </div>
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
            this.post = response.data;
          });
        this.$router.push({name: 'home'});


    }
  },
  mounted(){
  }
 
};

</script>
