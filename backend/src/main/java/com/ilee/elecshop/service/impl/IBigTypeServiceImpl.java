package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.BigTypeDao;
import com.ilee.elecshop.dao.ProductDao;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.Product;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bigtypeservice")
public class IBigTypeServiceImpl extends ServiceImpl<BigTypeDao, BigType> implements IBigTypeService {

    @Autowired
    private BigTypeDao bigTypeDao;


}
