package com.ilee.elecshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilee.elecshop.pojo.WxUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxUserInfoDao extends BaseMapper<WxUserInfo> {

    public WxUserInfo findByOpenId(String openid);
}
