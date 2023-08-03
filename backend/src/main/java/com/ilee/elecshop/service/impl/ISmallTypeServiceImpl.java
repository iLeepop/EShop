package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.BigTypeDao;
import com.ilee.elecshop.dao.SmallTypeDao;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.Order;
import com.ilee.elecshop.pojo.SmallType;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("smalltypeservice")
public class ISmallTypeServiceImpl extends ServiceImpl<SmallTypeDao, SmallType> implements ISmallTypeService {

    @Autowired
    private SmallTypeDao smallTypeDao;

    @Override
    public List<Order> getList(Map<String, Object> map) {
        return smallTypeDao.getList(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return smallTypeDao.getTotal(map);
    }
}
