const baseUrl="http://localhost:8080"

let ajaxTimes = 0

export const getBaseUrl = () => {
  return baseUrl
}

export const requestUtil = (params) => {
    
    let header = {...params.header}
    if(params.url.includes("/my/")) {
      header["token"] = wx.getStorageSync('token')
    }

    ajaxTimes++;

    wx.showLoading({
      title: '正在加载',
      mask: true
    })
  return new Promise((resolve, reject) => {
    wx.request({
      ...params,
      header,
      url: baseUrl + params.url,
      success:(result) => {
        resolve(result)
      },
      fail:(err) => {
        reject(err)
      },
      complete: () => {
        ajaxTimes--
        if(ajaxTimes == 0) {
          wx.hideLoading()
        }
      }
    })
  });
}

export const getWxLogin = () => {
  return new Promise((resolve, reject) => {
    wx.login({
      timeout: 5000,
      success: (res) => {
        resolve(res)
      },
      fail: (err) => {
        reject(err)
      }
    })
  })
}

export const getUserProfile = () => {
  return new Promise((resolve, reject) => {
    wx.getUserProfile({
      desc: '获取用户信息',
      success: (res) => {
        resolve(res)
      },
      fail: (err) => {
        reject(err)
      }
    })
  })
}

export const requestPay = (pay) => {
  return new Promise((resolve, reject) => {
    wx.requestPayment({
      ...pay,
      success: (res) => {
        resolve(res)
      },
      fail: (err) => {
        reject(err)
      }
    })
  })
}