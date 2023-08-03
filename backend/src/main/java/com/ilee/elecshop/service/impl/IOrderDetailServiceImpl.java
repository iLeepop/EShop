package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.BigTypeDao;
import com.ilee.elecshop.dao.OrderDetailDao;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.OrderDetail;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderdetailservice")
public class IOrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetail> implements IOrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;


}
