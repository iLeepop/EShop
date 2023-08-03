package com.ilee.elecshop.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.OrderDetail;
import com.ilee.elecshop.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/orderDetail")
public class AdminOrderDetailController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("/listByOrderId/{id}")
    public R listByOrderId(@PathVariable(value = "id") Integer id){
        List<OrderDetail> orderDetails = orderDetailService.list(new QueryWrapper<OrderDetail>().eq("mid", id));
        Map<String, Object> map = new HashMap<>();
        map.put("orderDetailList", orderDetails);
        return R.ok(map);
    }
}
