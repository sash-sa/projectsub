// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import Vuetify from 'vuetify';
import Sortable from 'vue-sortable'
import VeeValidate from 'vee-validate'
import 'vuetify/dist/vuetify.min.css';
import 'babel-polyfill';
import draggable from 'vuedraggable'

import App from './App'
import router from './router'
import store from './store'

Vue.config.productionTip = false

Vue.use(Vuetify);
Vue.use(VeeValidate);
Vue.use(Sortable);
Vue.use(draggable);

NProgress.configure({ showSpinner: false }) // eslint-disable-line

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store: store,
  components: { App },
  template: '<App/>'
})
