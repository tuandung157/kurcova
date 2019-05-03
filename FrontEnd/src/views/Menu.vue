<template>
  <div data-role="appbar" data-expand-point="md" class="bg-crimson fg-white ">
    <a href="#" class="brand no-hover">
        <span style="width: 55px;" class="border bd-dark border-radius">
            DevFinder
        </span>
    </a>
    <ul class="app-bar-menu">
      <li><router-link to="/home">Home</router-link></li>
      <li><router-link to="/about">About</router-link></li>
    </ul>
    
    <ul class="app-bar-menu ml-auto">
      <li v-if="authenticated">
        <router-link :to="{name: 'addPost'}">Add Post</router-link>
      </li>
      <li v-if="authenticated">
        {{ this.$session.get('user').username }}  
      </li>
      <li v-else="authenticated">
        <router-link :to="{name: 'addUser'}">Register</router-link>
      </li>
      <li>
        <a v-if="authenticated" v-on:click.stop.prevent="logout()" href>Logout</a>
        <a v-else v-on:click.stop.prevent="login()" href>Login</a>

        <!-- <Login v-else /> -->
      </li>
    </ul>
  </div>
</template>

<script>
  import Login from '@/views/login.vue';
  require( 'metro4' );
// @ is an alias to /src
export default {
  name: "Menu",
  data () {
  	return {
  	}
  },
  props: {
    authenticated : Boolean
  },
  components: {
    Login
  }, mounted(){
  }, methods : {
    logout: function() {
      this.$session.destroy();
      this.$router.go();
    },
    login: function() {
      this.$router.push({name: 'login'});
    }
  }
 
};
</script>
