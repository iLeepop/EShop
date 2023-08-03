# 前言

小程序涵盖axios异步请求，Promise的应用，自定义组件；应用小程序提供的登录以及支付，地址管理，下拉刷新，滑动分页，结合SpringBoot后端实现JSAPI支付流程。

使用JWT技术实现前后端分离。

后台使用Vue3+ElementPlus组件实现，同样涵盖axios以及Promise工具类封装，Vuex应用，Vue-router及路由守卫，使用大量第三方组件，例如Vue-Quill实现富文本框。


## 技术栈

Vue3 + Vuex + Vue-router + Vue-Cli + ElementUI +  less + Java + SpringBoot + MybatisPlus等


## 项目运行

```
cd frontend\

npm install

npm run serve

---------

cd app\elecShop

//微信开发者工具

---------

cd backend

//下载pom依赖

//后台Idea Intellij
```


# 说明

>  没啥好说明的，部分功能还可以抽离为单独的组件，有兴趣自己改。
>
>  总的架构还可以优化，继续...

# 目标功能

**小程序APP**

- [x] 首页轮播图 -- 完成
- [x] 首页热门 -- 完成
- [x] 首页分类 -- 完成
- [x] 搜索组件 -- 完成
- [x] 搜索功能 -- 完成
- [x] 商品分类 -- 完成
- [x] 商品详情 -- 完成
- [x] 购物车页面 -- 完成
- [x] 购物车支付跳转 -- 完成
- [x] 支付页面 -- 完成
- [x] 支付生成预订单 -- 完成
- [x] 支付功能（需注册商户 -- 完成
- [x] 订单列表 -- 完成
- [x] 订单分类 -- 完成
- [x] 微信登录 -- 完成
- [x] 获取微信用户信息 -- 完成
- [x] 操作以及存储用户Token -- 完成
- [x] 个人中心 -- 完成

**后台管理页面**

- [x] 管理员登录 -- 完成
- [x] 用户管理 -- 完成
- [x] 商品类别管理 -- 完成
- [x] 商品信息管理页面 -- 完成
- [x] 商品图片及热门轮播图修改 -- 完成
- [x] 商品详情幻灯图修改 -- 完成
- [x] 商品详情信息修改 -- 完成
- [x] 商品增删査 -- 完成
- [x] 订单管理页面 -- 完成
- [x] 订单增删改查 -- 完成
- [x] 管理员修改密码 -- 完成
- [x] 安全退出 -- 完成

**优化项目架构**

- [ ] 后端拆分
- [ ] 使用微服务架构
- [ ] 使用RocketMQ，防止堵塞
- [ ] Redis缓存热门数据
- [ ] 统一鉴权
- [ ] 做Nginx代理


# 部分截图

<img src="https://github.com/iLeepop/EShop/blob/master/screenshots/index.jpg" width="365" height="619"/> <img src="https://github.com/iLeepop/EShop/blob/master/screenshots/type.jpg" width="365" height="619"/>

<img src="https://github.com/iLeepop/EShop/blob/master/screenshots/cart.jpg" width="365" height="619"/> <img src="https://github.com/iLeepop/EShop/blob/master/screenshots/myy.jpg" width="365" height="619"/>

<img src="https://github.com/iLeepop/EShop/blob/master/screenshots/backend.jpg" width="100%"/> 
