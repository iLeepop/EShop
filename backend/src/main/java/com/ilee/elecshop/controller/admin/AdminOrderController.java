package com.ilee.elecshop.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ilee.elecshop.entity.PageBean;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.Order;
import com.ilee.elecshop.pojo.OrderDetail;
import com.ilee.elecshop.service.IOrderDetailService;
import com.ilee.elecshop.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @PostMapping("/list")
    public R list(@RequestBody PageBean pageBean){
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", pageBean.getQuery().trim());
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Order> orderList =  orderService.getList(map);
        Long total = orderService.getTotal(map);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderList", orderList);
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

    @PostMapping("/updateStatus")
    public R updateStatus(@RequestBody Order order){
        Order resultOrder = orderService.getById(order.getId());
        resultOrder.setStatus(order.getStatus());
        orderService.saveOrUpdate(resultOrder);
        return R.ok();
    }

    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        orderService.removeById(id);
        orderDetailService.remove(new QueryWrapper<OrderDetail>().eq("mid", id));
        return R.ok();
    }
}
