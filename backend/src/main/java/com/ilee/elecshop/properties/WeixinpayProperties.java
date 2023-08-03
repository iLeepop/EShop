package com.ilee.elecshop.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weixinpayconfig")
@Data
public class WeixinpayProperties {

    private String appid; // 小程序 appId

    private String mch_id; // 商户号

    private String key; // 商户密钥

    private String url; // api请求地址

    private String notify_url; // 服务器异步通知页面路径


}
