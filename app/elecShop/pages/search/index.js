import { getBaseUrl,requestUtil } from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
// pages/search/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputValue: "",
    isFocus: false,
    productList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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

  setTimeoutId: -1,

  handleInput(e) {
    const {value} = e.detail
    if(!value.trim()) {
      this.setData({
        productList: [],
        isFocus: false
      })
      return;
    }
    this.setData({
      isFocus: true
    })
    clearTimeout(this.setTimeoutId)
    this.setTimeoutId = setTimeout(() => {
      this.search(value)
    }, 1*1000)
  },

  async search(q) {
    const r = await requestUtil({
      url: '/product/search',
      data: {q}
    })
    this.setData({
      productList: r.data.msg
    })
  },

  handleCancel() {
    this.setData({
      inputValue: "",
      productList: [],
      isFocus: false
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