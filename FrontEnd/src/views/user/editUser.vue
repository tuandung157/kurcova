<template>
  <div>{{userData}}</div>
  <div>
  <form >

    <input type="text" data-role="input" placeholder="Email" data-prepend="your Email: "            v-model="userData.userEmail" required >

    <input type="password" data-role="password" placeholder="password" data-prepend="password: "  v-model="userData.password" required >

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
  name: "editUser",
  data () {
    return {}
  },
  components: {
  },   
  props: {
    userData: Object
  },
  methods: {
    editPost : function(event){

        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.put('http://localhost:8080/users/edit/' + this.$session.getAll().user.userId,this.userData)
        .then(response => {
            // handle success
            console.log(response.data);

            this.user = response.data;
          });
        this.$router.push({name: 'home'});


    }
  },
  mounted(){
    console.log(this.$session.getAll().user.userId);
  }
 
};

</script>
