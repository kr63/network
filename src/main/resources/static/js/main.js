import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'

Vue.use(VueResource);

new Vue({
  el: '#app',
  render: a => a(App)
});

/*


var messageApi = Vue.resource('message{/id}');

Vue.component('message-form', {
  props: ['messages', 'messageAttr'],
  data: function () {
    return {
      text: '',
      id: ''
    }
  },
  template:
  }
});

});*/
