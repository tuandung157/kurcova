<template>
	<div>
    <div>
		<SingleUserView :userData="user"></SingleUserView>
    </div>
    <div  data-role="panel"
          data-title-caption="some description"
          data-collapsible="true"
          >{{user}}</div>
    <div>
      <router-link :to="{name: 'editUser', param:{userId :this.userId,userData: user}}">
        <button class="button warning">Edit</button>
      </router-link>
    </div>      
	</div>
</template>

<script>
import SingleUserView from '@/components/SingleUserView.vue';
// @ is an alias to /src
export default {
  name: "getUser",
  data() {
    return {      
      userId: null,
      user: null
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
        axios.get('http://localhost:8080/users/'+this.userId)
        .then(response => {
        	// console.log(response.data);
            this.user = response.data;
          });
  }
};
</script>