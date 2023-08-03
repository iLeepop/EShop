package com.ilee.elecshop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@TableName("t_bigtype")
@Data
public class BigType {

    private Integer id; // 编号

    private String name; // 名称

    private String image="default.jpg"; // 商品图片

    private String remark; // 描述

    @TableField(select = false)
    private List<SmallType> smallTypeList; // 该大类下的所有小类

}
