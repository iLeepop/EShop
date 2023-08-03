import { 
  getBaseUrl,
  requestUtil,
  getUserProfile,
  getWxLogin, 
  requestPay} from '../../utils/requestUtil'
import regeneratorRuntime from '../../lib/runtime/runtime'
// pages/my/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    menu: [
      {
        id: 1,
        type: 1,
        name: '待付款',
        icon: 'icon-daizhifu'
      },
      {
        id: 2,
        type: 2,
        name: '待收货',
        icon: 'icon-daishouhuo'
      },
      {
        id: 3,
        type: 3,
        name: '退款/退货',
        icon: 'icon-tuikuantuihuo'
      },
      {
        id: 4,
        type: 0,
        name: '全部订单',
        icon: 'icon-quanbudingdan'
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const token = wx.getStorageSync('token')
    if(!token){
      wx.showModal({
        title: '提示',
        content: '微信授权登录后,才可进入个人中心',
        success: (res)  => {
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
            this.setData({
              userInfo: r[1].userInfo
            })
          })
        },
      })
    }else {
      const userInfo = wx.getStorageSync('userInfo')
      this.setData({
        userInfo
      })
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
    }
  },

  handleEditAddress() {
    wx.chooseAddress({
      success: (res) => {}
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