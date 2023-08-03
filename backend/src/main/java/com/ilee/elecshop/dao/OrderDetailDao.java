package com.ilee.elecshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetail> {

}
