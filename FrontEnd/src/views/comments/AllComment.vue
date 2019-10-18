<template>
  <div>
    <ul style="list-style-type: none;">
      <li v-for="comment in comments">
        <SingleCommentView :commentData="comment"/>
      </li>
    </ul>
  </div>
  <div>
    
  </div>

</template>

<script>
// @ is an alias to /src
import SingleCommentView from '@/components/SingleCommentView.vue';
export default {
  name: "AllComments",
  data () {
    return {
    comments : null 
  }
  },
  components: {
    SingleCommentView
  },   
  props: {
    postId : Number
  },
  methods: {
    getAllComment:function(){
      const axios = require('axios');
        // Make a request for a user with a given ID
        axios.get('http://localhost:8080/comments/post/'+this.postId)
        .then(response => {
            // handle success
            // console.log(response.data);
            this.comments = response.data;
          });
    },
    intervalData:function(){
      setInterval(() => {
        this.getAllComment();
      },1000);
    }
  },
  mounted(){
    this.getAllComment();
    this.intervalData();
  }
 
};

</script>
