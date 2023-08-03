package com.ilee.elecshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ilee.elecshop.pojo.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService extends IService<Order> {

    List<Order> getList(Map<String, Object> map);
    Long getTotal(Map<String, Object> map);
}
