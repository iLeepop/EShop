import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from '@/App.vue'
import router from '@/router/index'
import '@/router/permission'
import store from '@/store'
import './assets/styles/border.css'
import './assets/styles/reset.css'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

const app = createApp(App)
app.use(ElementPlus)
app.component('QuillEditor', QuillEditor)
app.use(store)
app.use(router)
app.mount('#app')
