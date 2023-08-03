package com.ilee.elecshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.Product;
import com.ilee.elecshop.pojo.ProductSwiperImage;
import com.ilee.elecshop.service.IProductService;
import com.ilee.elecshop.service.IProductSwiperImageService;
import com.ilee.elecshop.service.impl.IProductServiceImpl;
import com.ilee.elecshop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IProductSwiperImageService iProductSwiperImageService;

    @GetMapping("/findSwiper")
    public R findSwiper() {
        List<Product> swiperProductList = iProductService
                .list(new QueryWrapper<Product>()
                        .eq("isSwiper", true)
                        .orderByAsc("swiperSort"));
        Map<String,Object> map = new HashMap<>();
        map.put("msg", swiperProductList);
        return R.ok(map);
    }

    @GetMapping("/findHot")
    public R findHot() {
        Page<Product> page = new Page<>(0, 8);
        Page<Product> hotProductPage = iProductService.page(page, new QueryWrapper<Product>()
                .eq("isHot", true)
                .orderByAsc("hotDateTime"));
        List<Product> hotProductList = hotProductPage.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("msg", hotProductList);
        return R.ok(map);
    }

    @GetMapping("/detail")
    public R detail(@RequestParam Integer id) {
        if(id == null) {
            return R.error("id不能为空");
        }
        Product product = iProductService.getById(id);
        List<ProductSwiperImage> productSwiperImageList = iProductSwiperImageService
                .list(new QueryWrapper<ProductSwiperImage>()
                        .eq("productId", product.getId())
                        .orderByAsc("sort"));
        product.setProductSwiperImages(productSwiperImageList);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", product);
        return R.ok(map);
    }

    @GetMapping("/search")
    public R search(String q) {
        List<Product> list = iProductService.list(new QueryWrapper<Product>()
                .like("name", q));
        Map<String, Object> map = new HashMap<>();
        map.put("msg", list);
        return R.ok(map);
    }
}
