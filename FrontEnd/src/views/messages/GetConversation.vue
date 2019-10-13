<template>
<div>
  <div>
    <ul>
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
      userToId : null,
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
    update :function(){
      const axios = require('axios');
      axios.get(stringUrl)
      .then(response => {
          console.log(response.data);
          this.conversations = response.data;
        });      
    }
  },
  mounted(){
    const axios = require('axios');
    console.log(this.$route.params);
    this.userToId = Number.parseInt(this.$route.params.id);
    // Make a request for a user with a given ID
    // $route.params.id1
    axios.get('http://localhost:8080/message/'+this.$session.get('user').userId+'/'+this.userToId)
    .then(response => {
        console.log(response.data);
        this.conversations = response.data;
      });
  }
 
};

</script>
