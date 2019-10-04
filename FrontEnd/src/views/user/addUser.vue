<template>
  <div>
  <form >
    <input type="text" data-role="input" placeholder="Email" data-prepend="your Email: "            v-model="userData.userEmail" required >

    <input type="password" data-role="password" placeholder="password" data-prepend="password: "  v-model="userData.password"  minlength="8" required >

    <input type="text" data-role="input" placeholder="(+7) xxx xxx" data-prepend="your telephone: " v-model="userData.telephone" required >

    <input type="text" data-role="input" placeholder="text" data-prepend="Bio: "                    v-model="userData.text"      required >

    <input type="text" data-role="input" placeholder="userName" data-prepend="User name: "          v-model="userData.username"  required >

    <input type="button" value="addUser- please dont click"  v-on:click="addUser" />
  </form>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "AddUser",
  data () {
    return {
      userData : {
        email: '',
        password: '',
        telephone:'',
        text:'',
        username:''
      }
    }
  },
  components: {
  },   
  props: {
  },
  methods: {
    addUser : function(event){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.post('http://localhost:8080/users',this.userData)
        .then(response => {
            // handle success
            console.log(response.data);
            this.users = response.data;
          });



    }
  },
  mounted(){
  }
 
};

</script>
