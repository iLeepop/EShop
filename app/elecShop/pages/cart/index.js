import { getBaseUrl,requestUtil } from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
// pages/cart/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: '',
    address: {},
    cart: [],
    allChecked: false,
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

  handleChooseAddress() {
    wx.chooseAddress({
      success: (r) => {
        wx.setStorageSync('address', r)
      }
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
    const cart = wx.getStorageSync('cart') || []
    this.setData({
      address,
    })
    this.setCart(cart)
  },

  handleItemChange(e) {
    const {id} = e.currentTarget.dataset
    let {cart} = this.data
    let index = cart.findIndex(v => v.id === id)
    cart[index].checked = !cart[index].checked
    this.setCart(cart)
  },

  handleItemChecked() {
    let {cart, allChecked} = this.data
    allChecked = !allChecked
    cart.forEach(v => v.checked = allChecked)
    this.setCart(cart)
  },

  handleNumEdit(e) {
    const {id, operation} = e.currentTarget.dataset
    let {cart} = this.data
    let index = cart.findIndex(v => v.id === id)
    if(cart[index].num === 1 && operation === -1) {
      wx.showModal({
        title: '提示',
        content: '是否删除商品',
        cancelColor: 'cancelColor',
        success: (res) => {
          if (res.confirm) {
            cart.splice(index, 1)
            this.setCart(cart)
            return
          }
        }
      })
      return
    }
    cart[index].num += operation
    this.setCart(cart)
  },

  setCart(cart) {
    let allChecked = true
    let totalPrice = 0
    let totalNum = 0
    cart.forEach(v => {
      if(v.checked) {
      totalNum += v.num
      totalPrice += v.price * v.num
      }else {
        allChecked = false
      }
    })
    allChecked = cart.length != 0 ? allChecked:false
    this.setData({
      cart,
      allChecked,
      totalPrice,
      totalNum
    })

    wx.setStorageSync('cart', cart)
  },

  handlePay() {
    const {address, totalNum} = this.data
    if(!address) {
      wx.showToast({
        title: '还未选择收货地址',
        icon: 'none'
      })
      return
    }
    if(totalNum === 0) {
      wx.showToast({
        title: '还未选择商品',
        icon: 'none'
      })
      return
    }
    wx.navigateTo({
      url: '/pages/pay/index',
    })
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