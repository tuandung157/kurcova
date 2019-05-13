<template>
  <div>
  <p>{{ commentData }}</p>
  </div>

</template>

<script>
// @ is an alias to /src

export default {
  name: "SingleCommentView",
  data () {
    return {
    }
  },
  components: {
  },   
  props: {
       commentData: Object
  },
  mounted(){ 

  }
 
};

</script>
