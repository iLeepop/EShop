import router from '@/router/index'

router.beforeEach((to, from, next) => {
    let token = window.sessionStorage.getItem('token')
    const whiteList = ['/login']
    if(token) {
        if(to.path === '/login') {
            next({path: '/'})
        }else {
            next();
        }
    }else {
        if(whiteList.includes(to.path)) {
            next();
        }else {
            next({path: '/login'})
        }
    }
})
