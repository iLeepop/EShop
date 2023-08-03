import { getBaseUrl,requestUtil } from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
// pages/category/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: '',
    currentIndex: 0,
    scrollTop: 0,
    leftMenuList:[],
    rightContext:[]
  },

  Cates:[], //所有商品数据

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const baseUrl = getBaseUrl()
    this.setData({
      baseUrl: baseUrl
    })
    this.getCates()

  },

  async getCates() {
    const r = await requestUtil({
        url: '/bigtype/findCategories',
        method: 'GET',
        dataType: 'json',
    })
    this.Cates = r.data.msg
    let leftMenuList = this.Cates.map(x => x.name)
    let rightContext = this.Cates[0].smallTypeList
    this.setData({
      leftMenuList: leftMenuList,
      rightContext: rightContext
    })
  },

  async getCates2(i) {
    const r = await requestUtil({
        url: '/bigtype/findCategories',
        method: 'GET',
        dataType: 'json',
    })
    let index = i
    this.Cates = r.data.msg
    let rightContext = this.Cates[index].smallTypeList
    this.setData({
      currentIndex: index,
      rightContext,
      scrollTop: 0
    })
  },

  //菜单切换事件
  handleMenuItemChange(e) {
    const {index} = e.currentTarget.dataset
    let rightContext = this.Cates[index].smallTypeList
    this.setData({
      rightContext: rightContext,
      currentIndex: index,
      scrollTop: 0
    })
    const app = getApp()
    app.globalData.index = index
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
    const app =getApp()
    const index = app.globalData.index
    if(index == -1) return
    this.getCates2(index)
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