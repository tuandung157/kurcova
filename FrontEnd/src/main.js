import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import VueSession from 'vue-session'
import VueCookies from 'vue-cookies'

Vue.prototype.$restBaseUrl = 'http://localhost:8080';

Vue.config.productionTip = false;
var options = {
    persist: true
}
Vue.use(VueSession,options);

Vue.use(VueCookies)
let app = new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
