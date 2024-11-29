import Vue from 'vue'
import App from './App.vue'
import router from './router/index.js'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import videojs from "video.js";
import "video.js/dist/video-js.css";
import videoplayer from 'vue-video-player';
import 'video.js/dist/video-js.css'; 
import 'vue-video-player/src/custom-theme.css';



//引入vuex
import Vuex from 'vuex'
import store from './store'
// import Cookie from 'vue-cookie'
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.$axios= axios
Vue.prototype.$url="http://localhost:8082"
Vue.prototype.$video = videojs;
Vue.use(videoplayer);

import qs from 'qs';
Vue.prototype.$qs = qs;
/* 向vue对象中添加全局对象 以后发送ajax请求使用$http对象 */
Vue.prototype.$http = axios
Vue.config.productionTip = false
// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(
  config => {
    if (localStorage.getItem('token')) {
      config.headers.token = localStorage.getItem('token');
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  });
new Vue({
  axios,
  store,
  router,
  render: h => h(App),
}).$mount('#app')
