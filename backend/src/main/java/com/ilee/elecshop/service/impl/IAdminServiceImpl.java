package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.AdminDao;
import com.ilee.elecshop.dao.BigTypeDao;
import com.ilee.elecshop.pojo.Admin;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.service.IAdminService;
import com.ilee.elecshop.service.IBigTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminservice")
public class IAdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements IAdminService {

    @Autowired
    private AdminDao adminDao;


}
