<template>
  <p>{{ taskData }}</p>
</template>

<script>
// @ is an alias to /src

export default {
  name: "SingleTaskView",
  data () {
    return {
    }
  },
  components: {
  },   
  props: {
       taskData: Object
  },
  mounted(){

  }
 
};

</script>
