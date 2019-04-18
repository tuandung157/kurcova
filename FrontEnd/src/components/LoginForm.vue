<template>
  <div>
    <input v-model="loginId" placeholder="Id">
    <input v-model="password" placeholder="Password">
    <button v-on:click="checkLogin">Login</button>
  </div>
</template>

<script>
export default {

  name: "loginForm",
  data () {
    return {
      loginId : null,
      password : null
    }
  },
  components: {
  }, mounted(){

  }, methods: {
    checkLogin : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.post('http://localhost:8080/users',{
            loginId : this.loginId,
            password : this.password
          })
        .then(response => {
            // handle success
            console.log(response.data);
            this.posts = response.data;
          });



  }
}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
