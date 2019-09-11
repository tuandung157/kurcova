<template>
  <form >
    <input type="text" placeholder="text" v-model="commentData.text" />
    <input type="button"  v-on:click="addMess"/>
  </form>
  
</template>

<script>
// @ is an alias to /src

export default {
  name: "EditComment",
  data () {
    return {
      }
    }
  },
  components: {
  },   
  props: {
  },
  methods: {
    addMess : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.put('http://localhost:8080/message/',this.commentData)
        .then(response => {
            // handle success
            console.log(response.data);
            this.posts = response.data;
          });
    }
  },
  mounted(){
  }
 
};

</script>
