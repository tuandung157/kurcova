<template>
  <form >
    <input type="text" placeholder="urMess" v-model="" />
    <input type="button"  v-on:click="addMessage"/>
  </form>
  
</template>

<script>
// @ is an alias to /src

export default {
  name: "addMessage",
  data () {
    return {
      userId : null,
      messData : {
        text: '',
      }
    }
  },
  components: {
  },   
  props: {
  },
  methods: {
    addMessage : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.post('http://localhost:8080/message', this.messData)
        .then(response => {
            // handle success
            console.log(response.data);
            this.messages = response.data;
          });



    }
  },
  mounted(){
  }
 
};

</script>
