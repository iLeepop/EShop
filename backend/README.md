```
elecShop
├─ .gitignore
├─ pom.xml
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ ilee
   │  │        └─ elecshop
   │  │           ├─ config
   │  │           │  ├─ MybatisPlusConfig.java
   │  │           │  └─ WebAppConfiguration.java
   │  │           ├─ constant
   │  │           │  └─ SystemConstant.java
   │  │           ├─ controller
   │  │           │  ├─ admin
   │  │           │  │  ├─ AdminBigTypeController.java
   │  │           │  │  ├─ AdminController.java
   │  │           │  │  ├─ AdminOrderController.java
   │  │           │  │  ├─ AdminOrderDetailController.java
   │  │           │  │  ├─ AdminProductController.java
   │  │           │  │  ├─ AdminProductSwiperImgController.java
   │  │           │  │  ├─ AdminSmallTypeController.java
   │  │           │  │  └─ AdminUserController.java
   │  │           │  ├─ BigTypeController.java
   │  │           │  ├─ OrderController.java
   │  │           │  ├─ ProductController.java
   │  │           │  ├─ TestController.java
   │  │           │  ├─ UserController.java
   │  │           │  └─ WeixinpayController.java
   │  │           ├─ dao
   │  │           │  ├─ AdminDao.java
   │  │           │  ├─ BigTypeDao.java
   │  │           │  ├─ OrderDao.java
   │  │           │  ├─ OrderDetailDao.java
   │  │           │  ├─ ProductDao.java
   │  │           │  ├─ ProductSwiperImageDao.java
   │  │           │  ├─ SmallTypeDao.java
   │  │           │  └─ WxUserInfoDao.java
   │  │           ├─ demos
   │  │           │  └─ web
   │  │           │     ├─ BasicController.java
   │  │           │     ├─ PathVariableController.java
   │  │           │     └─ User.java
   │  │           ├─ ElecShopApplication.java
   │  │           ├─ entity
   │  │           │  ├─ CheckResult.java
   │  │           │  ├─ PageBean.java
   │  │           │  └─ R.java
   │  │           ├─ exception
   │  │           │  └─ GloBalExceptionHandler.java
   │  │           ├─ interceptor
   │  │           │  └─ SysInterceptor.java
   │  │           ├─ pojo
   │  │           │  ├─ Admin.java
   │  │           │  ├─ BigType.java
   │  │           │  ├─ CustomDateTimeSerializer.java
   │  │           │  ├─ Order.java
   │  │           │  ├─ OrderDetail.java
   │  │           │  ├─ Product.java
   │  │           │  ├─ ProductSwiperImage.java
   │  │           │  ├─ SmallType.java
   │  │           │  └─ WxUserInfo.java
   │  │           ├─ properties
   │  │           │  ├─ WeixinpayProperties.java
   │  │           │  └─ WeixinProperties.java
   │  │           ├─ service
   │  │           │  ├─ IAdminService.java
   │  │           │  ├─ IBigTypeService.java
   │  │           │  ├─ impl
   │  │           │  │  ├─ IAdminServiceImpl.java
   │  │           │  │  ├─ IBigTypeServiceImpl.java
   │  │           │  │  ├─ IOrderDetailServiceImpl.java
   │  │           │  │  ├─ IOrderServiceImpl.java
   │  │           │  │  ├─ IProductServiceImpl.java
   │  │           │  │  ├─ IProductSwiperImageServiceImpl.java
   │  │           │  │  ├─ ISmallTypeServiceImpl.java
   │  │           │  │  └─ IWxUserInfoServiceImpl.java
   │  │           │  ├─ IOrderDetailService.java
   │  │           │  ├─ IOrderService.java
   │  │           │  ├─ IProductService.java
   │  │           │  ├─ IProductSwiperImageService.java
   │  │           │  ├─ ISmallTypeService.java
   │  │           │  └─ IWxUserInfoService.java
   │  │           └─ util
   │  │              ├─ DateUtil.java
   │  │              ├─ HttpClientUtil.java
   │  │              ├─ JwtUtils.java
   │  │              ├─ MD5Util.java
   │  │              ├─ StringUtil.java
   │  │              └─ XmlUtil.java
   │  └─ resources
   │     ├─ application.yml
   │     ├─ mybatis
   │     │  └─ dao
   │     │     ├─ AdminDao.xml
   │     │     ├─ BigTypeDao.xml
   │     │     ├─ OrderDao.xml
   │     │     ├─ OrderDetailDao.xml
   │     │     ├─ ProductDao.xml
   │     │     ├─ ProductSwiperImageDao.xml
   │     │     ├─ SmallTypeDao.xml
   │     │     └─ WxUserInfoDao.xml
   │     └─ static
   │        └─ index.html
   └─ test
      └─ java
         └─ com
            └─ ilee
               └─ elecshop
                  └─ ElecShopApplicationTests.java

```
