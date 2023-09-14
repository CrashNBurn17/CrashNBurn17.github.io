import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import VCalendar from 'v-calendar'
import { Datetime } from 'vue-datetime';
import 'vue-datetime/dist/vue-datetime.css';

Vue.config.productionTip = false

Vue.use(VCalendar, {

});

Vue.component('datetime', Datetime);

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
