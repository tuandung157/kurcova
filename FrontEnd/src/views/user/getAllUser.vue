<template>
  <div>
    <div>ALL USER</div>
  	<div>
      <ul>
        <li v-for="user in users">
  		    <SingleUserView :userData="user" ></SingleUserView>
        </li>
      </ul>

  	</div>
  </div>
</template>


<script>
import SingleUserView from '@/components/SingleUserView.vue';
// @ is an alias to /src
export default {
  name: "allUser",
  data() {
    return {      
      users : [],
      checkUserId : null
    }
  },
  components: {
  	SingleUserView
  },   
  props: {
  },
  methods: {
  },
  mounted(){
        const axios = require('axios');
        this.userId = Number.parseInt(this.$route.params.id);
        // Make a request for a user with a given ID
        axios.get('http://localhost:8080/users/')
        .then(response => {
        	console.log(response.data);
            this.users = response.data;
          });
  }
};
</script>