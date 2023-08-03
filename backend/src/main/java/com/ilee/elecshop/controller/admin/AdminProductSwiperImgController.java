package com.ilee.elecshop.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ilee.elecshop.entity.PageBean;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.ProductSwiperImage;
import com.ilee.elecshop.pojo.SmallType;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.IProductSwiperImageService;
import com.ilee.elecshop.service.ISmallTypeService;
import com.ilee.elecshop.util.DateUtil;
import com.ilee.elecshop.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/productSwiperImg")
public class AdminProductSwiperImgController {

    @Autowired
    private IProductSwiperImageService productSwiperImageService;

    @Value("${productSwiperImagesFilePath}")
    private String productSwiperImagesFilePath;

    @GetMapping("/list/{id}")
    public R list(@PathVariable Integer id){
        List<ProductSwiperImage> list = productSwiperImageService.list(new QueryWrapper<ProductSwiperImage>().eq("productId", id));
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        return R.ok(map);
    }

    @PostMapping("/save")
    public R save(@RequestBody ProductSwiperImage productSwiperImage){
        System.out.println("product" + productSwiperImage);
        productSwiperImageService.saveOrUpdate(productSwiperImage);
        return R.ok();
    }

    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        productSwiperImageService.removeById(id);
        return R.ok();
    }

    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(MultipartFile file)throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if(!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(productSwiperImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "/image/productSwiperImgs/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }
}
