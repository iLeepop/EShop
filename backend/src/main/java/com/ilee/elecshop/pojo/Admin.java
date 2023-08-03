package com.ilee.elecshop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_admin")
@Data
public class Admin {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    @TableField(select = false)
    private String newPassword;
}
