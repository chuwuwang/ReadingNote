import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Router);
Vue.use(ElementUI);

export default new Router(
    {
        routes: [
            { path: '/', name: 'HelloWorld', component: HelloWorld },
            { path: "/login", name: "loginLink", component: Login },
        ]
    }
)
