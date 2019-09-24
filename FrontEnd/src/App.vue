<template>
  <div id="app">
    <Menu :key="menuKey" :authenticated="authenticated"/>
    
    <div class="grid">
        <div class="row"> <a>....</a></div>
        <div class="row"> <a>....</a></div>
        <!-- notification notify -->
        <div class="row">      
            <div class="cell-2 offset-10">
                    <notifications  group="foo" 
                                position="bottom right"/>
                <button class="action-button" v-on:click="showNotify()">Notify</button>
            </div> 
        </div>

        <div class="row">         
            <div class="cell offset-3 colspan-7">
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
                menuKey : 0,
            }
        },
        props: {
            socketMessage : String,
            statusNewPost : Number,
        },
        components : {
          Menu
        },
        mounted() {
            this.authenticated = this.$session.exists() && this.$session.has('user');
            this.socketMessage = 'we dont have any new post';
            this.socketMessage ='';
            // this.statusNewPost = 0;

        },
        sockets: {
            notification(msg) {
                console.log(msg);
                this.socketMessage = msg ;
                this.statusNewPost = 1;
            }
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
            },
            showNotify(socketMessage) {
                this.$notify({
                group: 'foo',
                type:'warn',
                duration: '1000',
                title: 'you have a new post is...',
                text: this.socketMessage
            })
                this.statusNewPost = 0;
            }
        }
    };

</script>

<style>
</style>
