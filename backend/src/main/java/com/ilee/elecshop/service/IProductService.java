package com.ilee.elecshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ilee.elecshop.pojo.Product;

import java.util.List;
import java.util.Map;

public interface IProductService extends IService<Product> {

    List<Product> getList(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    void add(Product product);

    void edit(Product product);

    Product one(Integer id);
}
