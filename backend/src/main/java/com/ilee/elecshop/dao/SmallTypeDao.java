package com.ilee.elecshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.Order;
import com.ilee.elecshop.pojo.SmallType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SmallTypeDao extends BaseMapper<SmallType> {

    List<Order> getList(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    SmallType findById(Integer id);
}
