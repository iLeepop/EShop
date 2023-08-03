package com.ilee.elecshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.Order;
import com.ilee.elecshop.pojo.OrderDetail;
import com.ilee.elecshop.properties.WeixinpayProperties;
import com.ilee.elecshop.service.IOrderDetailService;
import com.ilee.elecshop.service.IOrderService;
import com.ilee.elecshop.util.*;
import io.jsonwebtoken.Claims;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.*;

@RestController
@RequestMapping("/my/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private WeixinpayProperties weixinpayProperties;

    //创建订单
    @PostMapping("/create")
    @Transactional
    public R create(@RequestBody Order order, @RequestHeader(value = "token") String token) {
        System.out.println("create");
        Claims claims = JwtUtils.validateJWT(token).getClaims();
        if(claims != null) {
            order.setUserId(claims.getId());
        }
        order.setOrderNo("ILEE" + DateUtil.getCurrentDateStr());
        order.setCreateDate(new Date());

        OrderDetail goods[] = order.getGoods();
        orderService.save(order);
        for(int i = 0; i < goods.length; i++) {
            OrderDetail orderDetail = goods[i];
            orderDetail.setMId(order.getId());
            orderDetailService.save(orderDetail);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderNo", order.getOrderNo());
        return R.ok(resultMap); //返回订单号
    }

    /**
     * 预支付
     * @param orderNo
     * @return
     * @throws Exception
     */
    @PostMapping("/preparePay")
    public R preparePay(@RequestBody String orderNo) throws Exception {
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("orderNo", orderNo));

        Map<String, Object> map = new HashMap<>();
        map.put("appid", weixinpayProperties.getAppid());
        map.put("mch_id", weixinpayProperties.getMch_id()); //商户号 我没有
        map.put("nonce_str", StringUtil.getRandomString(32));
        map.put("body", "ILEE电商测试");
        map.put("out_trade_no", orderNo);
        map.put("total_fee", order.getTotalPrice().movePointRight(2));
        map.put("spbill_create_ip", "127.0.0.1");
        map.put("notify_url", weixinpayProperties.getNotify_url()); //服务器回调通知的地址 我没有
        map.put("trade_type", "JSAPI");
        map.put("openid", order.getUserId());
        map.put("sign", getSign(map));

        String xml = XmlUtil.genXml(map);
        System.out.println(xml);

        HttpResponse httpResponse = HttpClientUtil.sendXMLDataByPost(weixinpayProperties.getUrl(), xml);
        String httpEntityContent = HttpClientUtil.getHttpEntityContent(httpResponse);
        System.out.println(httpEntityContent);

        Map resultMap = XmlUtil.doXMLParse(httpEntityContent);

        if(resultMap.get("result_code").equals("SUCCESS")) {
            Map<String, Object> payMap = new HashMap<>();
            payMap.put("appId", resultMap.get("appid"));
            payMap.put("timeStamp", System.currentTimeMillis() / 1000);
            payMap.put("nonceStr", StringUtil.getRandomString(32));
            payMap.put("package", "prepay_id=" + resultMap.get("prepay_id"));
            payMap.put("signType", "MD5");
            payMap.put("paySign", getSign(payMap));
            payMap.put("orderNo", orderNo);
            return R.ok(payMap);
        } else {
            return R.error(500, "支付失败");
        }
    }

    @GetMapping("/list")
    public R list(Integer type, Integer page, Integer pageSize) {
        System.out.println("type=" + type);
        List<Order> orderList = null;
        Map<String, Object> resultMap = new HashMap<>();

        Page<Order> pageOrder = new Page<>(page, pageSize);

        if(type == 0) {
//            orderList = orderService.list(new QueryWrapper<Order>().orderByDesc("id"));
            Page<Order> orderR = orderService.page(pageOrder, new QueryWrapper<Order>().orderByDesc("id"));
            System.out.println("总记录数" + orderR.getTotal());
            System.out.println("总页数" + orderR.getPages());
            System.out.println("当前页面数据" + orderR.getRecords());
            orderList = orderR.getRecords();
            resultMap.put("total", orderR.getTotal());
            resultMap.put("totalPage", orderR.getPages());
        }else {
//            orderList = orderService.list(new QueryWrapper<Order>().eq("status", type).orderByDesc("id"));
            Page<Order> orderR = orderService.page(pageOrder, new QueryWrapper<Order>().eq("status", type).orderByDesc("id"));
            System.out.println("总记录数" + orderR.getTotal());
            System.out.println("总页数" + orderR.getPages());
            System.out.println("当前页面数据" + orderR.getRecords());
            orderList = orderR.getRecords();
            resultMap.put("total", orderR.getTotal());
            resultMap.put("totalPage", orderR.getPages());
        }

        resultMap.put("orderList", orderList);
        return R.ok(resultMap);
    }

    /**
     * 微信支付签名算法sign
     */
    private String getSign(Map<String,Object> map) {
        StringBuffer sb = new StringBuffer();
        String[] keyArr = (String[]) map.keySet().toArray(new
                String[map.keySet().size()]);//获取map中的key转为array
        Arrays.sort(keyArr);//对array排序
        for (int i = 0, size = keyArr.length; i < size; ++i) {
            if ("sign".equals(keyArr[i])) {
                continue;
            }
            sb.append(keyArr[i] + "=" + map.get(keyArr[i]) + "&");
        }
        sb.append("key=" + weixinpayProperties.getKey());
        String sign = string2MD5(sb.toString());
        return sign;
    }
    /***
     * MD5加码 生成32位md5码
     */
    private String string2MD5(String str){
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

}
