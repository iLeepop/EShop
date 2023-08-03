import { 
  getBaseUrl,
  requestUtil,
  getUserProfile,
  getWxLogin, 
  requestPay} from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
// pages/cart/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: '',
    code: {},
    userInfo: {},
    cart: [],
    address: {},
    totalPrice: 0,
    totalNum: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const baseUrl = getBaseUrl()
    this.setData({
      baseUrl
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    const address = wx.getStorageSync('address')
    let cart = wx.getStorageSync('cart') || []
    cart = cart.filter(v => v.checked === true)
    let totalPrice = 0
    let totalNum = 0
    cart.forEach(v => {
      totalNum += v.num
      totalPrice += v.price * v.num
    })
    this.setData({
      cart,
      address,
      totalPrice,
      totalNum
    })
  },

  async handleOrderPay() {
    const token = wx.getStorageSync('token')
    if(!token){
      Promise.all([getWxLogin(),getUserProfile()]).then((r) => {
        this.setData({
          code: r[0].code,
          userInfo: r[1].userInfo
        })
        let loginParams = {
          code: r[0].code,
          nickName: r[1].userInfo.nickName,
          avatarUrl: r[1].userInfo.avatarUrl,
        }
        wx.setStorageSync('userInfo', r[1].userInfo)
        this.wxLogin(loginParams)
      })
    }else {
      this.createOrder()
    }
  },

  async wxLogin(loginParams) {
    const r = await requestUtil({
      url: "/user/wxlogin",
      data: loginParams,
      method: "post"
    })
    const token = r.data.token
    if(r.data.code === 0) {
      wx.setStorageSync('token', token)
      this.createOrder()
    }
  },

  async createOrder() {
    try{
      let totalPrice = this.data.totalPrice
      let address = this.data.address.provinceName 
        + this.data.address.cityName 
        + this.data.address.countyName
        + this.data.address.detailInfo
      let consignee = this.data.address.userName
      let telNumber = this.data.address.telNumber
      let goods = []
      this.data.cart.forEach(v => goods.push({
        goodsId: v.id,
        goodsNumber: v.num,
        goodsPrice: v.price,
        goodsName: v.name,
        goodsPic: v.proPic
      }))
      const orderParam = {
        totalPrice,
        address,
        consignee,
        telNumber,
        goods
      }
      const r = await requestUtil({
        url: "/my/order/create",
        method: "POST",
        data: orderParam
      })
      let orderNo = r.data.orderNo
      const preparePayResult = await requestUtil({
        url: "/my/order/preparePay",
        method: "POST",
        data: orderNo
      })
      let payResult = await requestPay(preparePayResult);

      let newCart = wx.getStorageSync('cart')
      newCart = newCart.filter(v => !v.checked)
      wx.setStorageSync('cart', newCart)

      wx.showToast({
        title: '支付成功',
        icon: 'none'
      })

      wx.navigateTo({
        url: '/pages/order/index?type=0'
      })
    }catch(error) {
      console.log(error);
      wx.showToast({
        title: '支付失败，稍后重试',
        icon: 'none'
      })
    }

    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})