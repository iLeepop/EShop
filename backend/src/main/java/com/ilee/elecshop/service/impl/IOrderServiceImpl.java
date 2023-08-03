package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.OrderDao;
import com.ilee.elecshop.pojo.Order;
import com.ilee.elecshop.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("orderdaoservice")
public class IOrderServiceImpl extends ServiceImpl<OrderDao, Order> implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getList(Map<String, Object> map) {
        return orderDao.getList(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return orderDao.getTotal(map);
    }
}
