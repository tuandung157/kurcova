import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Login from  "./views/login.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/home",
      name: "home",
      component: Home
    },
    {
      path: "/login",
      name: "login",
      component: () =>
        import("./views/login.vue")
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    },
    {
      path: "/posts/edit",
      name: "editPost",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/posts/EditPost.vue"),
      props: true
    },
    {
      path: "/posts/delete",
      name: "deletePost",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("./views/posts/DeletePost.vue"),
      props: true
    },
    {
      path: "/posts/add",
      name: "addPost",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("./views/posts/AddPost.vue"),
      props: true
    },
    {
      //register
      path: "/user/add",
      name: "addUser",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("./views/user/addUser.vue"),
      props: true
    },
    {
      //get all users
      path: "/user/getall",
      name: "allUser",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("./views/user/getAllUser.vue"),
      props: true
    },
    {
      path: "/posts/:id",
      name: "postDetail",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("./views/posts/PostDetail.vue"),
      props: true
    },
    {
      path: "/posts/detail/:post_id/project/:project_id",
      name: "project",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("./views/projects/AllProject.vue"),
      props:true
    },
    {
      path: "/user/:id",
      name: "user",
      component: () =>
      import("./views/user/getUser.vue"),
      props:true
    },
    {
      path: "/user/edit/:id",
      name: "editUser",
      component: () =>
      import("./views/user/editUser.vue"),
      props:true
    },
    {
      path: "/postSearch/:string",
      name: "postSearch",
      component: () =>
      import("./views/posts/postSearch.vue"),
      props:true
    },{
      path: "/message/conversation/:id",
      name: "conversation",
      component: () =>
      import("./views/messages/GetConversation.vue"),
      props:true
    },{
      path: "/message/add",
      name: "addMessage",
      component: () =>
        import("./views/messages/AddMessage.vue"),
      props: true     
    }
  ]
});
