package com.ilee.elecshop.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ilee.elecshop.constant.SystemConstant;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.Admin;
import com.ilee.elecshop.service.IAdminService;
import com.ilee.elecshop.util.JwtUtils;
import com.ilee.elecshop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/adminLogin")
    public R adminLogin(@RequestBody Admin admin) {
        System.out.println(admin);
        if(admin == null) {
            return R.error();
        }
        if(StringUtil.isEmpty(admin.getUsername())) {
            return R.error("用户名不能为空");
        }
        if(StringUtil.isEmpty(admin.getPassword())) {
            return R.error("密码不能为空");
        }
        Admin resultAdmin = adminService.getOne(new QueryWrapper<Admin>().eq("username", admin.getUsername()));
        if(resultAdmin == null) {
            return R.error("用户名不存在");
        }
        if(!resultAdmin.getPassword().trim().equals(admin.getPassword())) {
            return R.error("用户名或者密码错误");
        }
        String token = JwtUtils.createJWT("-1", "admin", SystemConstant.JWT_TTL);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        return R.ok(resultMap);
    }

    @PostMapping("/modifyPassword")
    public R modifyPassword(@RequestBody Admin admin) {
        System.out.println(admin);
        if(StringUtil.isEmpty(admin.getUsername())) {
            return R.error("用户名不能为空");
        }
        if(StringUtil.isEmpty(admin.getPassword())) {
            return R.error("密码不能为空");
        }
        if(StringUtil.isEmpty(admin.getNewPassword())) {
            return R.error("新密码不能为空");
        }
        adminService.update(new UpdateWrapper<Admin>()
                .set("password", admin.getNewPassword())
                .eq("username", admin.getUsername()));
        return R.ok("修改成功");
    }
}
