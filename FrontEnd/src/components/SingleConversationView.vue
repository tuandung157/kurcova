<template>
  <div>
  <div class="icon-box border bd-default">
    <div class="content p-4">
      <div class ="text-upper text-bold text-lead">
      {{conversationData.userFrom.username}}
      </div>
      <div class="">
      {{conversationData.text}}
    </div>
    </div>
  </div>
  <br>
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
