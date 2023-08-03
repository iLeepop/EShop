import { getBaseUrl,requestUtil } from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
// pages/product_detail/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeIndex: 0,
    baseUrl: '',
    productObj: {}
  },

  productInfo: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const baseUrl = getBaseUrl()
    this.setData({
      baseUrl
    })
    this.getProductDetail(options.id)
  },

  handleItemTap(e) {
    const {index} = e.currentTarget.dataset
    this.setData({
      activeIndex: index
    })
  },

  handleCartAdd() {
    this.setCartAdd()

    wx.showToast({
      title: '加入成功',
      icon: 'success',
      mask: true
    })
  },

  handleBuy() {
    this.setCartAdd()
    wx.switchTab({
      url: '/pages/cart/index',
    })
  },

  setCartAdd() {
    let cart = wx.getStorageSync('cart') || []
    let index = cart.findIndex(v => v.id === this.productInfo.id)
    if(index == -1) {
      this.productInfo.num = 1
      this.productInfo.checked = true
      cart.push(this.productInfo)
    }else{
      cart[index].num += 1
    }
    wx.setStorageSync('cart', cart)
  },

  async getProductDetail(id) {
    const r = await requestUtil({
      url: '/product/detail',
      data: {id},
      method: 'GET',
      dataType: 'json',
    })
    this.productInfo = r.data.msg
    this.setData({
      productObj: r.data.msg
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