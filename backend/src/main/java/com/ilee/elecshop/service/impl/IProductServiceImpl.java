package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.ProductDao;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.Product;
import com.ilee.elecshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productservice")
public class IProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getList(Map<String, Object> map) {
        return productDao.getList(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return productDao.getTotal(map);
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void edit(Product product) {
        productDao.edit(product);
    }

    @Override
    public Product one(Integer id) {
        return productDao.one(id);
    }
}

