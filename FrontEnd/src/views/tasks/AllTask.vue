<template>
  <ul >
    <li v-for="task in tasks">
      <SingleTaskView :taskData="task" />
      
    </li>
  </ul>  
  
</template>

<script>
// @ is an alias to /src
import SingleTaskView from '@/components/SingleTaskView.vue';
export default {
  name: "Tasks",
  data() {
    return {      
    tasks : null
    }
  },
  components: {
    SingleTaskView
  },   
  props: {
    SingleTask: Object

  },
  methods: {

  },
  mounted(){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.get('http://localhost:8080/tasks/')
        .then(response => {
            this.tasks = response.data;
          });
  }
 
};

</script>
