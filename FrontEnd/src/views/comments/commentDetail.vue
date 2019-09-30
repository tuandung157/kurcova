<template>
  <div>

  </div>
</template>

<script>
// @ is an alias to /src
import SingleCommmentView from '@/components/SinglePostView.vue';
import AllComment from '@/views/comments/AllComment.vue'
export default {
  name: "postDetail",
  data() {
    return {      
      postId : null,
      post : null
    }
  },
  components: {
    SinglePostView,
    AllComment
  },   
  props: {
    id : Number
  },
  methods: {

  },
  mounted(){
        const axios = require('axios');
        this.postId = Number.parseInt(this.id);
        // Make a request for a user with a given ID
        axios.get('http://localhost:8080/comments'+this.postId)
        .then(response => {
            this.post = response.data;
          });
  }
};

</script>
