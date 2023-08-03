package com.ilee.elecshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilee.elecshop.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDao extends BaseMapper<Product> {

    List<Product> getList(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    void add(Product product);

    void edit(Product product);

    Product one(Integer id);
}
