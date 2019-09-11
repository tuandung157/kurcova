<template>
  <form >
    <input type="text" placeholder="Email" v-model="userData.userEmail" />
    <input type="text" placeholder="password" v-model="userData.password" />
    <input type="text" placeholder="telephone" v-model="userData.telephone" />
    <input type="text" placeholder="text" v-model="userData.text" />
    <input type="text" placeholder="userName" v-model="userData.username" />
    <input type="button" value="addUser- please dont click"  v-on:click="addUser"/>
  </form>
  
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
