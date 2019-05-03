<template>
    <div id="login">
        <h1>Login</h1>
        <input type="text" name="username" v-model="input.username" placeholder="Username" />
        <input type="password" name="password" v-model="input.password" placeholder="Password" />
        <button type="button" v-on:click="login()">Login</button>
    </div>
</template>

<script>
import Menu from '@/views/Home'
export default {
    name: 'Login',
    data() {
        return {
            input: {
                username: '',
                password: ''
            }
        }
    },
    mounted () {
        if(this.$session.exists() && this.$session.has('user')) this.$router.push({name:'home'});
    },
    methods: {
        login: function() {
            var self = this;
            const axios = require('axios');
                 // Make a request for a user with a given ID
                 axios.post('http://localhost:8080/users/login',this.input)
                 .then(response => {
                if(!response.data == false) {
                this.$session.start();
                this.$session.set('user',response.data);

                // console.log(this.$session.get('user'));
                this.$emit("authenticated", true);
                this.$router.push({name:'home'});
            } else {
                console.log("Login failed");
            }
            console.log(response.data);
            this.login = response.data;
        });
             }  
         }
     };
     </script>

     <style scoped>
     #login {
        width: 500px;
        border: 1px solid #CCCCCC;
        background-color: #FFFFFF;
        margin: auto;
        margin-top: 200px;
        padding: 20px;
    }
    </style>