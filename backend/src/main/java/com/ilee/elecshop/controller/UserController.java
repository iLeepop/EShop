package com.ilee.elecshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ilee.elecshop.constant.SystemConstant;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.WxUserInfo;
import com.ilee.elecshop.properties.WeixinProperties;
import com.ilee.elecshop.service.IWxUserInfoService;
import com.ilee.elecshop.util.HttpClientUtil;
import com.ilee.elecshop.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IWxUserInfoService wxUserInfoService;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @PostMapping("/wxlogin")
    public R wxlogin(@RequestBody WxUserInfo info) {
        String jscode2sessionUrl = weixinProperties.getJscode2sessionUrl()
                + "?appid=" + weixinProperties.getAppid()
                + "&secret=" + weixinProperties.getSecret()
                + "&js_code=" + info.getCode()
                + "&grant_type=authorization_code";
        String r = httpClientUtil.sendHttpGet(jscode2sessionUrl);
        JSONObject jsonObject = JSONObject.parseObject(r);
        String openid = jsonObject.getString("openid");
        WxUserInfo wxUserInfo = wxUserInfoService.getOne(new QueryWrapper<WxUserInfo>().eq("openid", openid));
        if(wxUserInfo==null) {
            info.setOpenid(openid);
            info.setRegisterDate(new Date());
            info.setLastLoginDate(new Date());
            wxUserInfoService.save(info);
        }else {
            wxUserInfo.setLastLoginDate(new Date());
            wxUserInfo.setNickName(info.getNickName());
            wxUserInfo.setAvatarUrl(info.getAvatarUrl());
            wxUserInfoService.updateById(wxUserInfo);
        }
        String token = JwtUtils.createJWT(openid, wxUserInfo.getNickName(), SystemConstant.JWT_TTL);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return R.ok(map);
    }
}
