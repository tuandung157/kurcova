<template>
  <div></div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "DeleteProject",
  data () {
    return {}
  },
  components: {
  },   
  props: {
    projectId: Number,
    groupId: Number
  },
  methods: {

  },
  mounted(){
        const axios = require('axios');
        // Make a request for a user with a given ID
        axios.delete('http://localhost:8080/project/delete/'+ this.groupId + '/' + this.projectId)
        .then(response => {
            // handle success
            console.log(response.data);
          });    
  }
 
};

</script>
