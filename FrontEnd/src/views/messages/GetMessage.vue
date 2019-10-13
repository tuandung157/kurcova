<template>
<div>
  <div>
    <p>{{convertion}}</p>
  </div>
</div>
</template>

<script>
// @ is an alias to /src
export default {
  name: "Messages",
  data() {
    return {
      convertion: []
    }
  },
  components: {
  },   
  props: {
    userToId : Number
  },
  methods: {

  },
  mounted(){
        const axios = require('axios');
        // Make a request for a user with a given ID
        // $route.params.id1
        axios.get('http://localhost:8080/message/'+this.$session.get('user').userId+'/'+'5')
        // axios.get('http://localhost:8080/message/1/5')
        .then(response => {
            // handle success
            // console.log(this.$session.get('user').userId);
            console.log(response.data);
            this.convertion = response.data;
          });
  }
 
};

</script>
