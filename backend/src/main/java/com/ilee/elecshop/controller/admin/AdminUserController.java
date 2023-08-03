package com.ilee.elecshop.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ilee.elecshop.entity.PageBean;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.Admin;
import com.ilee.elecshop.pojo.WxUserInfo;
import com.ilee.elecshop.service.IWxUserInfoService;
import com.ilee.elecshop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private IWxUserInfoService wxUserInfoService;

    @PostMapping("list")
    public R list(@RequestBody PageBean pageBean) {
        System.out.println(pageBean);
        String query = pageBean.getQuery().trim();
        Page<WxUserInfo> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        Page<WxUserInfo> resultPage = wxUserInfoService.page(page, new QueryWrapper<WxUserInfo>()
                .like(StringUtil.isNotEmpty(query), "nickName", query));
        Map<String, Object> map = new HashMap<>();
        map.put("userList", resultPage.getRecords());
        map.put("total", resultPage.getTotal());
        return R.ok(map);
    }

}
