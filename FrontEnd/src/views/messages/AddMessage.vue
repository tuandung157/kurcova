<template>
  <form >
    <div class="from-group">
    <label> message</label>
    <input type="text" placeholder="yourMessage" v-model="messData.text" />
    
    <button type="button primary"  v-on:click="addMessage">send</button>
    </div>
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
        text: ''
      }
    }
  },
  components: {
    
  },   
  props: {
    userToId : Number
  },
  methods: {
    addMessage : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.post('http://localhost:8080/message/'+this.$session.get('user').userId + '/' + this.userToId, this.messData)
        .then(response => {
            // handle success
            console.log(response.data);
            this.messages = response.data;
            this.messData.text = "";
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
