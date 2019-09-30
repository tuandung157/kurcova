<template>
  <form >
    <div>
      <div class="form-group">
          <label>change password</label>
          <input type="password" placeholder="password" v-model="userData.password" />
      </div>
      <div class="form-group">
          <label>user telephone</label>
          <input type="text" placeholder="title" v-model="userData.telephone" required/>
      </div>
      <div class="form-group">
          <label>your Bio</label>
          <input type="text" data-role="input" placeholder="text" data-prepend="User name: "          v-model="userData.text"  required />    </div>
      <div class="d-flex flex-justify-end pt-5">
          <button class="button primary" value="accept" v-on:click="editUser">Edit user</button>

    </div>
  </div>
  </form>

</template>

<script>
// @ is an alias to /src

export default {
  name: "EditUser",
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
