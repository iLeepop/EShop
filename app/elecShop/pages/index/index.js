import { getBaseUrl,requestUtil } from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    swiperList:[],
    baseUrl: '',
    bigTypeList: [],
    bigTypeList_row1: [],
    bigTypeList_row2: [],
    hotProductList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.request({
    //   url: 'http://127.0.0.1:8080/product/findSwiper',
    //   method: 'GET',
    //   dataType: 'json',
    //   success: (r) => {
    //     this.setData({
    //       swiperList:r.data.msg
    //     })
    //   }
    // })
    const baseUrl = getBaseUrl();
    this.setData({
      baseUrl: baseUrl
    })
    // requestUtil({
    //   url: '/product/findSwiper',
    //   method: 'GET',
    //   dataType: 'json',
    // }).then(r => {
    //   this.setData({
    //     swiperList:r.data.msg,
    //     baseUrl:baseUrl
    //   })
    // })
    this.getSwiperList()
    this.getBigTypeList()
    this.getHotProductList()
  },
//商品分类页面跳转
  handleTypeJump(e) {
    const {index} = e.currentTarget.dataset
    const app = getApp()
    app.globalData.index = index

    wx.switchTab({
      url: '/pages/category/index',
    })
  },

  async getSwiperList() {
    const r = await requestUtil({
        url: '/product/findSwiper',
        method: 'GET',
        dataType: 'json',
    })
    this.setData({
      swiperList: r.data.msg
    })
  },

  async getBigTypeList() {
    const r = await requestUtil({
      url: '/bigtype/findAll',
      method: 'GET',
      dataType: 'json',
    })
    const bigTypeList = r.data.msg
    const bigTypeList_row1 = bigTypeList.filter((item, index) => {
      return index < 5
    })
    const bigTypeList_row2 = bigTypeList.filter((item, index) => {
      return index >= 5
    })
    this.setData({
      bigTypeList: bigTypeList,
      bigTypeList_row1: bigTypeList_row1,
      bigTypeList_row2: bigTypeList_row2
    })
  },

  async getHotProductList() {
    const r = await requestUtil({
      url: '/product/findHot',
      method: 'GET',
      dataType: 'json',
    })
    this.setData({
      hotProductList: r.data.msg
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})