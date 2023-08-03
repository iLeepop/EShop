import { 
  getBaseUrl,
  requestUtil} from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
// pages/order/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orders: [],
    tabs: [
      {
        id: 0,
        value: "全部订单",
        isActive: true
      },
      {
        id: 1,
        value: "待付款",
        isActive: false
      },
      {
        id: 2,
        value: "待收货",
        isActive: false
      },
      {
        id: 3,
        value: "退款/退货",
        isActive: false
      }
    ]
  },

  QueryParams:{
    type: 0,
    page: 1, //当前页
    pageSize: 10 //每页记录数
  },

  totalPage: 1,

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

  changeTitleByIndex(index) {
    let {tabs} = this.data
    tabs.forEach((v, i) => i == index?v.isActive = true:v.isActive = false)
    this.setData({
      tabs
    })
  },

  handleTabsItemChange(e) {
    const {index} = e.detail
    this.changeTitleByIndex(index)

    this.QueryParams.type = index
    this.QueryParams.page = 1
    this.setData({
      orders: []
    })
    this.getOrders()
  },

  async getOrders() {
    const r = await requestUtil({
      url: '/my/order/list',
      data: this.QueryParams,
      method: 'get'
    })
    this.totalPage = r.data.totalPage
    this.setData({
      orders: [...this.data.orders, ...r.data.orderList]
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    const pages = getCurrentPages()
    let currentPage = pages[pages.length - 1]
    const {type} = currentPage.options
    this.changeTitleByIndex(type)
    this.QueryParams.type = type
    this.QueryParams.page = 1
    this.setData({
      orders: []
    })
    this.getOrders()
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
    this.QueryParams.page = 1
    this.setData({
      orders: []
    })
    this.getOrders()
    wx.stopPullDownRefresh()
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    console.log("触底了");
    if(this.QueryParams.page >= this.totalPage){
      console.log("没有下一页");
      wx.showToast({
        title: '没有下一页了',
      })
    }else {
      console.log("有下一页");
      this.QueryParams.page++
      this.getOrders()
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})