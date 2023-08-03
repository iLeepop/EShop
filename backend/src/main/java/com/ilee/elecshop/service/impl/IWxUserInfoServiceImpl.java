package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.BigTypeDao;
import com.ilee.elecshop.dao.WxUserInfoDao;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.WxUserInfo;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.IWxUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wxuserinfoservice")
public class IWxUserInfoServiceImpl extends ServiceImpl<WxUserInfoDao, WxUserInfo> implements IWxUserInfoService {

    @Autowired
    private WxUserInfoDao wxUserInfoDao;


}
