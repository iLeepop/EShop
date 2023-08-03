package com.ilee.elecshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.Order;
import com.ilee.elecshop.pojo.SmallType;

import java.util.List;
import java.util.Map;

public interface ISmallTypeService extends IService<SmallType> {

    List<Order> getList(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);
}
