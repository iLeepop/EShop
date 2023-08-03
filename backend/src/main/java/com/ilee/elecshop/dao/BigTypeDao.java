package com.ilee.elecshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilee.elecshop.pojo.BigType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BigTypeDao extends BaseMapper<BigType> {

    public BigType findById(Integer id);
}
