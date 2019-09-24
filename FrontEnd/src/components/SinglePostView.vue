<template>
  <div class="card fg-black " v-if="postData">
    <div class="card-header fg-white bg-cyan" >
      <div class="name">name user</div>
      <div class="date"> Posted on {{ postData.createdAt }}</div>  
      </div>
    <div class="card-content p-2 m-5">
        {{ postData.title }}
    </div>
    <div class="card-content m-5">
      {{ postData.content }}

    </div>
    <!-- so like vs so comment -->
    <!-- <div class="card-content fg-gray p-2">
        <span><small>Likes: </small>112</span>
        <span><small>Comments: </small>43</span>
    </div> -->
    <!-- button for like  -->
    <!-- <div class="card-footer">
        <button class="flat-button mif-thumbs-up mif-2x"></button>
        <button class="flat-button mif-tag mif-2x"></button>
        <button class="flat-button mif-share mif-2x"></button>
    </div> -->
    <div class="card-footer">
      <router-link  :to="{name: 'postDetail', params: { id: postData.postId }}">
          <button class="button secondary "> see more</button>
      </router-link>
    </div>
</div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "SinglePostView",
  data () {
    return {
    }
  },
  components: {
  },   
  props: {
       postData: Object
  },
  mounted(){
  }
 
};

</script>
