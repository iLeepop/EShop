package com.ilee.elecshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.Product;
import com.ilee.elecshop.pojo.SmallType;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.IProductService;
import com.ilee.elecshop.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bigtype")
public class BigTypeController {

    @Autowired
    private IBigTypeService iBigTypeService;

    @Autowired
    private ISmallTypeService iSmallTypeService;

    @Autowired
    private IProductService iProductService;

    @GetMapping("/findAll")
    public R findAll() {
        List<BigType> list = iBigTypeService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", list);
        return R.ok(map);
    }

    @GetMapping("/findCategories")
    public R findCategories() {
        List<BigType> list = iBigTypeService.list();
        for(BigType bigType : list) {
            List<SmallType> smallTypeList = iSmallTypeService.list(new QueryWrapper<SmallType>().eq("bigTypeId", bigType.getId()));
            bigType.setSmallTypeList(smallTypeList);
            for(SmallType smallType : smallTypeList) {
                List<Product> productList = iProductService.list(new QueryWrapper<Product>().eq("typeId", smallType.getId()));
                smallType.setProductList(productList);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", list);
        return R.ok(map);
    }

}
