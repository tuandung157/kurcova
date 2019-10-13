<template>
  <div>
    <div>
    {{conversationData.userFrom.username}}</div>
    <div>
    {{conversationData.text}}</div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "SingleConversationView",
  data () {
    return {
    }
  },
  components: {
  },   
  props: {
    conversationData: Object
  },
  mounted(){

  }
 
};

</script>
