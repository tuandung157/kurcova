<template>
  <div id="app">
    <Menu :key="menuKey" :authenticated="authenticated"/>
    <div class="grid">
        <div class="row">
            <div class="cell offset-2 colspan-8">
                <router-view @authenticated="setAuthenticated" class="pt-15" />
                
            </div>
        </div>
    </div>

  </div>
</template>

<script>
import Menu from '@/views/Menu.vue';
    export default {
        name: 'App',
        data() {
            return {
                authenticated: false,
                menuKey : 0
            }
        },
        components : {
          Menu
        }
        ,
        mounted() {
          this.authenticated = this.$session.exists() && this.$session.has('user');
            // if(!this.authenticated) {
            //     this.$router.replace({ name: "login" });
            // }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
            },
            logout() {
                this.authenticated = false;
            },
            forceRerender() {
              this.menuKey += 1;  
            }
        }
    };
</script>

<style>
</style>
