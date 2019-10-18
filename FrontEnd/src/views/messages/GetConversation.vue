<template>
<div>
  <div>
    <ul style="list-style-type: none;">
      <li v-for="conversation in conversations">
        <!-- tung mess -->
        <SingleConversationView :conversationData="conversation"></SingleConversationView>
      </li>
    </ul>
    <AddMessage :userToId="userToId">
  </div>
  <div>
    <!-- add message -->
  </div>
</div>
</template>

<script>
// @ is an alias to /src
import SingleConversationView from '@/components/SingleConversationView.vue';
import AddMessage from '@/views/messages/AddMessage.vue';
export default {
  name: "Conversation",
  data() {
    return {
      conversations: [],
      userToId : '',
      stringUrl : ''
    }
  },
  components: {
    SingleConversationView,
    AddMessage
  },   
  props: {
    userToId : Number
  },
  methods: {
    getConversation:function(){
      const axios = require('axios');
      // console.log(this.$route.params);
      this.userToId = Number.parseInt(this.$route.params.id);
      // Make a request for a user with a given ID
      // $route.params.id1
      axios.get('http://localhost:8080/message/'+this.$session.get('user').userId+'/'+this.userToId)
      .then(response => {
          // console.log(response.data);
          this.conversations = response.data;
        });
    },
    intervalData:function(val){
      if(this.$route.name == "conversation" ){
      var t = setInterval(() => {
        this.getConversation();
      },1000);}
      else{
        clearInterval(t);
      }
    }
  },
  watch: {
    
  },
  mounted(){
    
    this.getConversation();
    this.intervalData();
  }
 
};

</script>
